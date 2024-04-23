package com.sheffield.views;
/**
 * @author afiq_ismail
 */
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.sheffield.model.CurrentUserManager;
import com.sheffield.model.DatabaseOperations;
import com.sheffield.model.Order;
import com.sheffield.model.Status;
import com.sheffield.util.ButtonEditor;
import com.sheffield.util.ButtonRenderer;

public class OrderLineScreen extends JFrame {
    /**
     * Needed for serialisation
     */
    private static final long serialVersionUID = 1L;
    DatabaseOperations databaseOperations = new DatabaseOperations();
    private String orderID = "";
    private Status status = Status.PENDING;

    // Variables declaration                 
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel orderLinePanel;
    private javax.swing.JTabbedPane orderLineTab;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration

    /**
     * Creates OrderLineScreen constructor
     */
    public OrderLineScreen(Connection connection, String id) {
        super();

        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/4, screenSize.height/4);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialise widgets and other components
        initComponents(connection, id);

        setVisible(true);
    }
    
    public OrderLineScreen(Connection connection, String id, String orderID, Status status) {
        super();
        this.orderID = orderID;
        this.status = status;

        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/4, screenSize.height/4);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialise widgets and other components
        initComponents(connection, id);

        setVisible(true);
    }
    
    private void initComponents(Connection connection, String id) {

        
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        orderLineTab = new javax.swing.JTabbedPane();
        jLabel2 = new javax.swing.JLabel();
        orderLinePanel = createPanel(connection);
        jTextField1 = new javax.swing.JTextField();

        orderLineTab.addTab("Order Line", orderLinePanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Order Line Screen");
        setMinimumSize(new java.awt.Dimension(1216, 636));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Order Line");

        jButton2.setText("Main Screen");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (jButton2.getText().equals("Back")) {
                    goToOrderScreen(connection, id, evt);
                } else {
                    goToMainScreen(connection, id, evt);
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(137, 400, 400)
                .addComponent(jButton2)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jButton1.setText(" Checkout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (jButton1.getText().equals("Fulfill Order")) {
                    fulfillOrder(connection, evt, id);
                } else {
                    placeOrder(connection, id, evt);
                }
            }
        });

        jButton3.setText("Decline Order");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineOrder(connection, evt, id);
            }
        });

        jLabel2.setText("Messages: ");

        jTextField1.setEditable(false);
        jTextField1.setText("Your order line is empty. Add item now.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(245, 500, 500)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(orderLineTab, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(orderLineTab, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addGap(18, 18, 18)
                    .addGap(29, 29, 29)
                    .addComponent(jButton3))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        // change what to see here
        if (this.orderID.equals("") && this.status.equals(Status.PENDING)) {
            jButton3.setVisible(false);
            jButton1.setVisible(true);
            jTextField1.setText("This order is pending or empty. Go checkout now.");
        } else if (!this.orderID.equals("") && this.status.equals(Status.PENDING)) {
            jButton3.setVisible(false);
            jButton1.setVisible(false);
            jTextField1.setText("View of your order.");
        } else if (this.status.equals(Status.CONFIRMED)) {
            jButton3.setVisible(true);
            jButton1.setText("Fulfill Order");
            jButton2.setText("Back");
            jTextField1.setText("[Staff] This order has been confirmed. Choose your action.");
        } else if (this.status.equals(Status.FULFILLED)) {
            jButton3.setVisible(false);
            jButton1.setVisible(false);
            jButton2.setText("Back");
            jTextField1.setText("[OrderID: " + this.orderID + "] This order has been fulfilled.");
        } else if (this.status.equals(Status.DECLINED)) {
            jButton3.setVisible(false);
            jButton1.setVisible(false);
            jButton2.setText("Back");
            jTextField1.setText("[OrderID: " + this.orderID + "] This order has been declined.");
        }

        pack();
    }
    
    /**
     * Action-button || other functions | listeners
     */
    private void goToCheckoutScreen(Connection connection, String id, String orderID, java.awt.event.ActionEvent evt) {
        dispose();
        new CheckoutScreen(connection, id, orderID);
    }  

    private void goToMainScreen(Connection connection, String id, java.awt.event.ActionEvent evt) {
        dispose();
        new ProductListingScreen(connection, id);
    }   

    private void goToOrderScreen(Connection connection, String id, java.awt.event.ActionEvent evt) {
        dispose();
        new OrderScreen(connection, id);
    }

    private void placeOrder(Connection connection, String id, java.awt.event.ActionEvent evt) {
        try {
            // constructing defined orderID
            String userIDFirst2Char = CurrentUserManager.getCurrentUser().getUserID();
            String orderID = userIDFirst2Char.substring(0, Math.min(userIDFirst2Char.length(), 2)).toUpperCase();
            int userOrderCount = databaseOperations.countUserOrder(CurrentUserManager.getCurrentUser().getUserID(), connection);
            if (userOrderCount == 0) {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Your order line is empty. Add items to checkout!");
            } else {
                orderID = orderID + userOrderCount;
                Order order = databaseOperations.getOrderModel(orderID, connection);
                if (order.getStatus() != Status.PENDING) {
                    orderID = userIDFirst2Char.substring(0, Math.min(userIDFirst2Char.length(), 2)).toUpperCase();
                    userOrderCount = userOrderCount + 1;
                    orderID = orderID + userOrderCount;
                }
                String userIDLast2Char = id.substring(id.length() - 2).toUpperCase();
                int itemInOrderLineCount = databaseOperations.countUserOrderLine(orderID + userIDLast2Char, connection);

                if (itemInOrderLineCount>0) { // proceed to checkout if cart is not empty
                    goToCheckoutScreen(connection, id, orderID, evt);
                } else { // stay if cart is empty
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Your order line is empty. Add items to checkout!");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch(ParseException e) {
            e.printStackTrace();
        }

    }

    private void fulfillOrder(Connection connection,java.awt.event.ActionEvent evt, String id) {
        databaseOperations.updateOrderStatus(connection, this.orderID, Status.FULFILLED);
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,   "[" + this.orderID + "] The order has been fulfilled.");
        dispose();
        new OrderScreen(connection, id);
    }

    private void declineOrder(Connection connection,java.awt.event.ActionEvent evt, String id) {
        databaseOperations.updateOrderStatus(connection, this.orderID, Status.DECLINED);
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,   "[" + this.orderID + "] The order has been declined.");
        dispose();
        new OrderScreen(connection, id);
    } 

    private JPanel createPanel(Connection connection) {
        JPanel productPanel = new javax.swing.JPanel();
        JTable userTable = new javax.swing.JTable();
        JScrollPane jScrollPane2 = new javax.swing.JScrollPane();

        GroupLayout productPanelLayout = new GroupLayout(productPanel);
        
        productPanel.setLayout(productPanelLayout);
        productPanelLayout.setHorizontalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
                .addContainerGap())
        );
        productPanelLayout.setVerticalGroup(
            productPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
        );

        // try

        try {
            ResultSet orderIDOrderLineResultSet;
            DefaultTableModel countModel = new DefaultTableModel();
            countModel.addColumn("No.");
            DefaultTableModel userModel = new DefaultTableModel();
            DefaultTableModel combinedTableModel = new DefaultTableModel();
            // // Create a DefaultTableModel with a JButton column
            DefaultTableModel actionModel = new DefaultTableModel() {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnIndex == 2 ? JButton.class : Object.class; // Column index 2 contains buttons
                }
            };

            // constructing defined orderID
            String userIDFirst2Char = CurrentUserManager.getCurrentUser().getUserID();
            String orderID = userIDFirst2Char.substring(0, Math.min(userIDFirst2Char.length(), 2)).toUpperCase();
            int userOrderCount = databaseOperations.countUserOrder(CurrentUserManager.getCurrentUser().getUserID(), connection);
            orderID = orderID + userOrderCount;

            if (!(this.orderID.equals(""))) {
                orderID = this.orderID;
            } else {
                if (userOrderCount != 0) {
                    Order order = databaseOperations.getOrderModel(orderID, connection);
                    if (order.getStatus() != Status.PENDING) {
                        orderID = userIDFirst2Char.substring(0, Math.min(userIDFirst2Char.length(), 2)).toUpperCase();
                        int userOrderCountTemp = userOrderCount + 1;
                        orderID = orderID + userOrderCountTemp;
                    }
                }
            }

            orderIDOrderLineResultSet = databaseOperations.getAllOrderIDOrderLineData(connection, orderID);
            userModel = buildTableModel(orderIDOrderLineResultSet);
            for (int i = 0; i < databaseOperations.countOrderIDOrderLine(orderID, connection); i++) {
                countModel.addRow(new Object[]{i+1});
            }
            combinedTableModel = countModel;
            combinedTableModel = combineTableModels(countModel, userModel);
            actionModel.addColumn("Action");
            for (int i = 0; i < databaseOperations.countOrderIDOrderLine(orderID, connection); i++) {
                actionModel.addRow(new Object[]{ "Remove"});
            }
            
            if (!(this.orderID.equals(""))) {
                userTable.setModel(combinedTableModel);
                userTable.setColumnSelectionAllowed(true);
                jScrollPane2.setViewportView(userTable);
            } else {
                combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                userTable.setModel(combinedTableModel);
                userTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                userTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), userTable, connection));
                userTable.setColumnSelectionAllowed(true);
                jScrollPane2.setViewportView(userTable);
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(ParseException e) {
            e.printStackTrace();
        }

        return productPanel;
    }

    public DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        java.sql.ResultSetMetaData metaData = resultSet.getMetaData();

        // Get column count
        int columnCount = metaData.getColumnCount();

        // Create a DefaultTableModel to hold the data
        DefaultTableModel tableModel = new DefaultTableModel();

        // Add column names to the table model
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        // Add row data to the table model
        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
            }
            tableModel.addRow(rowData);
        }

        return tableModel;
    }

    // Method to combine two DefaultTableModel instances
    private static DefaultTableModel combineTableModels(DefaultTableModel model1, DefaultTableModel model2) {
        DefaultTableModel combinedModel = new DefaultTableModel();

        // Add columns from model1 to combinedModel
        for (int i = 0; i < model1.getColumnCount(); i++) {
            combinedModel.addColumn(model1.getColumnName(i));
        }

        // Add columns from model2 to combinedModel (ignoring columns present in model1)
        for (int i = 0; i < model2.getColumnCount(); i++) {
            String columnName = model2.getColumnName(i);
            if (!containsColumn(combinedModel, columnName)) {
                combinedModel.addColumn(columnName);
            }
        }

        // Add rows from model1 and model2 to combinedModel
        int rowCount = Math.max(model1.getRowCount(), model2.getRowCount());
        for (int i = 0; i < rowCount; i++) {
            Vector<Object> rowData = new Vector<>();
            for (int j = 0; j < combinedModel.getColumnCount(); j++) {
                if (j < model1.getColumnCount()) {
                    rowData.add(model1.getValueAt(i, j));
                } else {
                    rowData.add(model2.getValueAt(i, j - model1.getColumnCount()));
                }
            }
            combinedModel.addRow(rowData);
        }

        return combinedModel;
    }

    // Method to check if a column exists in the DefaultTableModel
    private static boolean containsColumn(DefaultTableModel model, String columnName) {
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (model.getColumnName(i).equals(columnName)) {
                return true;
            }
        }
        return false;
    }
}
