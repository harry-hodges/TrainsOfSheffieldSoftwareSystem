package com.sheffield.util;

import java.awt.Component;
import java.awt.Window;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.sheffield.views.ItemFormScreen;
import com.sheffield.views.OrderLineScreen;
import com.sheffield.views.ProductListingScreen;
import com.sheffield.views.UserScreen;
import com.sheffield.model.Product;
import com.sheffield.model.Role;
import com.sheffield.model.OrderLine;
import com.sheffield.model.CurrentUserManager;
import com.sheffield.model.DatabaseOperations;
import com.sheffield.model.Order;
import com.sheffield.model.Status;

// Custom cell editor to handle button clicks
public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;
    private Connection connection;
    // Product related private data
    private String storedProductCode;
    private String storedProductName;
    private String storedBrandName;
    private BigDecimal storedPrice;
    private String storedGauge;
    private int storedQuantity;
    // User related private data
    private String forename;
    private Role role;
    // OrderLine related private data
    private String storedOrderLine;
    // Orders related private data
    private String storedOrderID;
    private Status storedStatus;

    DatabaseOperations databaseOperations = new DatabaseOperations();
    java.util.Date utilDate = new java.util.Date();

    public ButtonEditor(JTextField textField, JTable table, Connection connection) {
        super(textField);
        this.table = table;
        this.connection = connection;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            int row = this.table.getEditingRow(); // Get the row index of the clicked button
            int column = 1; // Get the column index of the clicked button
            
            if (this.label.equals("Update | Delete")) {
                storedProductCode = (String) table.getValueAt(row, column);
            } else if (this.label.equals("Promote | Demote")) {
                forename = (String) table.getValueAt(row, column+1);
                String roleString = (String) table.getValueAt(row, column + 4);
                if (Role.valueOf(roleString.toUpperCase()) == Role.USER) {
                    this.role = Role.USER;
                } else if (Role.valueOf(roleString.toUpperCase()) == Role.STAFF) {
                    this.role = Role.STAFF;
                } else if (Role.valueOf(roleString.toUpperCase()) == Role.MANAGER) {
                    this.role = Role.MANAGER;
                }
            } else if (this.label.equals("Add to OrderLine")) {
                storedProductCode = (String) table.getValueAt(row, column);
                storedProductName = (String) table.getValueAt(row, column + 1);
                storedBrandName = (String) table.getValueAt(row, column + 2);
                storedPrice = (BigDecimal) table.getValueAt(row, column + 3);
                storedQuantity = (int) table.getValueAt(row, column + 4);
                storedGauge = (String) table.getValueAt(row, column + 5);
            } else if (this.label.equals("Remove")) {
                storedOrderLine = (String) table.getValueAt(row, column);
            } else if (this.label.equals("View")) {
                storedOrderID = (String) table.getValueAt(row, column);
                storedStatus = Status.valueOf((String) table.getValueAt(row, column + 3).toString().toUpperCase());
            } else if (this.label.equals("View ")) {
                storedOrderID = (String) table.getValueAt(row, column);
                storedStatus = Status.valueOf((String) table.getValueAt(row, column + 3).toString().toUpperCase());
            }
            // Perform different actions based on row and column indices
            fireEditingStopped();
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            // Perform the action when the button is clicked
            if (this.label.equals("Update | Delete")) {
                JOptionPane.showMessageDialog(button, "Editing: " + storedProductCode);
                // Close the current screen (frame)
                Window window = SwingUtilities.windowForComponent(button);
                if (window instanceof JFrame) {
                    window.dispose();
                }

                // Open another screen (frame)
                JFrame newFrame = new ItemFormScreen(connection, storedProductCode, CurrentUserManager.getCurrentUser().getUserID());
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Change to your desired close operation
                // Configure the new frame: set size, add components, etc.
                newFrame.setVisible(true);
            } else if (this.label.equals("Promote | Demote")) {
                String message = "";

                if (this.role == Role.USER) {
                    message = "Promote to Staff?";
                } else if (this.role == Role.STAFF) {
                    message = "Demote from Staff?";
                } else {
                    message = "You're a manager.";
                }

                int choice = JOptionPane.showConfirmDialog(null, "[" + this.forename + " | " + this.role + "] " + message , "Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (this.role == Role.USER) {
                        databaseOperations.promoteToStaff(connection, this.forename);
                        JOptionPane.showMessageDialog(null, this.forename + " has been promoted to Staff.");
                    } else if (this.role == Role.STAFF) {
                        databaseOperations.demoteStaff(connection, this.forename);
                        JOptionPane.showMessageDialog(null, this.forename + " has been demoted to User.");
                    }

                    // Close the current screen (frame)
                    Window window = SwingUtilities.windowForComponent(button);
                    if (window instanceof JFrame) {
                        window.dispose();
                    }

                    // Open another screen (frame)
                    JFrame newFrame = new UserScreen(connection, CurrentUserManager.getCurrentUser().getUserID());
                    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Change to your desired close operation
                    newFrame.setVisible(true);
                } else {
                    System.out.println("User clicked No or closed the dialog.");
                    // Perform actions for 'No' selection or dialog close
                }

            } else if (this.label.equals("Add to OrderLine")) {
                JFrame frame = new JFrame();
                String inputQuantity = JOptionPane.showInputDialog(frame,
                        "Enter quantity for " + this.storedProductName + " :");
                if (inputQuantity != null) {
                    int inpQ = Integer.parseInt(inputQuantity);
                    if (inpQ > storedQuantity) {
                        JOptionPane.showMessageDialog(button, "Input too large");
                    } else {
                        // creating order if there is no order created from the user
                        String userIDFirst2Char = CurrentUserManager.getCurrentUser().getUserID();
                        String orderID = userIDFirst2Char.substring(0, Math.min(userIDFirst2Char.length(), 2)).toUpperCase();
                        try {
                            int userOrderCount = databaseOperations.countUserOrder(CurrentUserManager.getCurrentUser().getUserID(), connection);
                            if (userOrderCount == 0) {

                                orderID = orderID + (userOrderCount + 1);
                                String userID = CurrentUserManager.getCurrentUser().getUserID();
                                BigDecimal totalCost = new BigDecimal(Double.parseDouble("0"));
                                Date date = new Date(utilDate.getTime());

                                Order order = new Order(orderID, userID, totalCost, Status.PENDING,  date);
                                databaseOperations.insertOrder(connection, order);

                            } else {
                                userOrderCount = databaseOperations.countUserOrder(CurrentUserManager.getCurrentUser().getUserID(), connection);
                                orderID = orderID + userOrderCount;
                                Order order = databaseOperations.getOrderModel(orderID, connection);
                                if(order.getStatus() != Status.PENDING) {
                                    orderID = orderID.substring(0, Math.min(orderID.length(), 2));
                                    orderID = orderID + (userOrderCount + 1);
                                    String userID = CurrentUserManager.getCurrentUser().getUserID();
                                    BigDecimal totalCost = new BigDecimal(Double.parseDouble("0"));
                                    Date date = new Date(utilDate.getTime());
                                    order = new Order(orderID, userID, totalCost, Status.PENDING,  date);
                                    databaseOperations.insertOrder(connection, order);

                                }
                            }

                            // orderLine insert
                            Product product = databaseOperations.getProductModel(this.storedProductCode, connection);
                            String userIDLast2Char = CurrentUserManager.getCurrentUser().getUserID();
                            userIDLast2Char = userIDLast2Char.substring(userIDLast2Char.length() - 2).toUpperCase();
                            int orderLineCount = databaseOperations.countUserOrderLine((orderID + userIDLast2Char), connection);
                            String orderLineNumber = orderID + userIDLast2Char + (orderLineCount + 1);
                            
                            // System.out.println("ORDERLINE: " + orderLineNumber);
                            // System.out.println("PRODUCTCODE: " + product.getProductCode());
                            // System.out.println("ORDERID: " + orderID);
                            // System.out.println("QUANTITY: " + inpQ);
                            // System.out.println("ORDERLINECOST: " + product.getPrice());
                            BigDecimal orderLineCost = product.getPrice().multiply(new BigDecimal(Double.parseDouble(inputQuantity)));
                            OrderLine orderLine = new OrderLine(orderLineNumber, product.getProductCode(), orderID, inpQ, orderLineCost);
                            databaseOperations.insertOrderLine(connection, orderLine);

                        } catch(SQLException e) {
                            e.getStackTrace();
                        } catch(ParseException e) {
                            e.getStackTrace();
                        }
                        JOptionPane.showMessageDialog(button, "Product added to OrderLine");
                    }
                }
            } else if (this.label.equals("Remove")) {
                databaseOperations.deleteOrderLine(connection, storedOrderLine);
                JOptionPane.showMessageDialog(button, "Product deleted from OrderLine");
                // Close the current screen (frame)
                    Window window = SwingUtilities.windowForComponent(button);
                    if (window instanceof JFrame) {
                        window.dispose();
                    }

                    // Open another screen (frame)
                    JFrame newFrame = new OrderLineScreen(connection, CurrentUserManager.getCurrentUser().getUserID());
                    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Change to your desired close operation
                    newFrame.setVisible(true);
            } else if (this.label.equals("View")) {
                JOptionPane.showMessageDialog(button, "Viewing: " + this.storedOrderID);
                // Close the current screen (frame)
                Window window = SwingUtilities.windowForComponent(button);
                if (window instanceof JFrame) {
                    window.dispose();
                }

                // Open another screen (frame)
                JFrame newFrame = new OrderLineScreen(connection, CurrentUserManager.getCurrentUser().getUserID(), this.storedOrderID, this.storedStatus);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Change to your desired close operation
                // Configure the new frame: set size, add components, etc.
                newFrame.setVisible(true);
            } else if (this.label.equals("View ")) {
                JOptionPane.showMessageDialog(button, "Viewing: " + this.storedOrderID);
                // Close the current screen (frame)
                Window window = SwingUtilities.windowForComponent(button);
                if (window instanceof JFrame) {
                    window.dispose();
                }

                // Open another screen (frame)
                JFrame newFrame = new OrderLineScreen(connection, CurrentUserManager.getCurrentUser().getUserID(), this.storedOrderID, Status.PENDING);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Change to your desired close operation
                // Configure the new frame: set size, add components, etc.
                newFrame.setVisible(true);
            }
        }
        isPushed = false;
        return label;
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}