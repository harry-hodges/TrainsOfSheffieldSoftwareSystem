package com.sheffield.views;
import com.sheffield.model.CurrentUserManager;
/**
 * @author afiq_ismail
 */
import com.sheffield.model.DatabaseOperations;
import com.sheffield.model.Order;
import com.sheffield.model.Status;
import com.sheffield.util.ButtonEditor;
import com.sheffield.util.ButtonRenderer;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;

public class CheckoutScreen extends JFrame {
    /**
     * Needed for serialisation
     */
    private static final long serialVersionUID = 1L;
    DatabaseOperations databaseOperations = new DatabaseOperations();

    // Variables declaration                 
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel orderLinePanel;
    private javax.swing.JTabbedPane orderLineTab;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
    
    /**
     * Creates CheckoutScreen constructor
     */
    public CheckoutScreen(Connection connection, String id, String orderID) {
        super();

        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/4, screenSize.height/4);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialise widgets and other components
        initComponents(connection, id, orderID);

        setVisible(true);
    }

    /**
     * Initialise widgets and other components
     */
    private void initComponents(Connection connection, String id, String orderID) {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        orderLineTab = new javax.swing.JTabbedPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        orderLinePanel = createPanel(connection);
        jTextField1 = new javax.swing.JTextField();
        double total = 0;

        try {
            total = Double.parseDouble(databaseOperations.getAllOrderIDOrderLineTotalPrice(connection, orderID).getString("total_price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        orderLineTab.addTab("Order Line", orderLinePanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Order Checkout Screen");
        setMinimumSize(new java.awt.Dimension(1216, 636));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Order Summary");

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToOrderLine(connection, id, evt);
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

        jButton1.setText("Place Order");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    JFrame frame = new JFrame();
                    String bankName = databaseOperations.getRecordFromTable(connection, "bankCardName", "Users", id);
                    if (bankName == null || bankName.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "No payment method detected. Please insert payment method");
                        System.out.println("No payment method detected. Redirecting to Profile");
                        goToProfile(connection, id, evt);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Your order has been confirmed!");
                        System.out.println("Payment method: " + bankName);
                        System.out.println("Order confirmed. Redirecting to my Order");
                        updateCurrentStock(connection, orderID);
                        databaseOperations.updateOrderStatus(connection, orderID, Status.CONFIRMED);
                        String totalString = databaseOperations.getAllOrderIDOrderLineTotalPrice(connection, orderID).getString("total_price");
                        databaseOperations.updateOrderIDTotalCost(connection, orderID, new BigDecimal(totalString));
                        goToMyOrderScreen(connection, id, evt);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jLabel2.setText("Messages: ");
        jLabel2.setVisible(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        // Order order = databaseOperations.getOrderModel(orderID, connection);
        if (total != 0) {
            jLabel3.setText("Total: £ " + String.valueOf(total));
        } else {
            jLabel3.setText("Total: £ XXXX");
        }

        jTextField1.setEditable(false);
        jTextField1.setText("Your order line is empty. Add item now.");
        jTextField1.setVisible(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(245, 1000, 1000)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(245, 500, 500)
                        .addComponent(jButton1))
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
                .addComponent(jLabel3)
                .addComponent(jButton1)
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

        pack();
    }
    
    /**
     * Action-button || other functions | listeners
     */
    private void goToMyOrderScreen(Connection connection, String id, java.awt.event.ActionEvent evt) {
        dispose();
        new MyOrderScreen(connection, id);
    }                                        

    private void goToOrderLine(Connection connection, String id, java.awt.event.ActionEvent evt) {
        dispose();
        new OrderLineScreen(connection, id);
    }

    private void goToProfile(Connection connection, String id, java.awt.event.ActionEvent evt) {
        dispose();
        new ProfileScreen(connection, id);
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

            // constructing defined orderID
            String userIDFirst2Char = CurrentUserManager.getCurrentUser().getUserID();
            String orderID = userIDFirst2Char.substring(0, Math.min(userIDFirst2Char.length(), 2)).toUpperCase();
            int userOrderCount = databaseOperations.countUserOrder(CurrentUserManager.getCurrentUser().getUserID(), connection);
            orderID = orderID + userOrderCount;

            Order order = databaseOperations.getOrderModel(orderID, connection);
            
            if (order.getStatus().equals(Status.PENDING) && (databaseOperations.countOrderIDOrderLine(orderID, connection)) != 0) {
                orderIDOrderLineResultSet = databaseOperations.getAllOrderIDOrderLineData(connection, orderID);
                userModel = buildTableModel(orderIDOrderLineResultSet);
                for (int i = 0; i < databaseOperations.countOrderIDOrderLine(orderID, connection); i++) {
                    countModel.addRow(new Object[]{i+1});
                }
                combinedTableModel = countModel;
                combinedTableModel = combineTableModels(countModel, userModel);
                userTable.setModel(combinedTableModel);
                userTable.setColumnSelectionAllowed(true);
                jScrollPane2.setViewportView(userTable);
            } 
        } catch(SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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

    private void updateCurrentStock(Connection connection, String orderID) throws SQLException {

        // Get each orderLineNumber from orderID
        ArrayList<String> orderLineNumberList = new ArrayList<>();

        String query = "SELECT * FROM OrderLines WHERE orderID=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, orderID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String result = resultSet.getString("orderLineNumber");
            orderLineNumberList.add(result);
        }

        for (String orderLineNumberX : orderLineNumberList) {
            String query2 = "SELECT * FROM OrderLines WHERE orderLineNumber=?";
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, orderLineNumberX);
            ResultSet resultSet2 = statement2.executeQuery();

            while (resultSet2.next()) {
                String resultProductCode = resultSet2.getString("productCode");
                Integer resultProductQuantity = resultSet2.getInt("productQuantity");

                // get Current stock
                String query3 = "SELECT quantity FROM Products WHERE productCode=?";
                PreparedStatement statement3 = connection.prepareStatement(query3);
                statement3.setString(1, resultProductCode);
                ResultSet resultSet3 = statement3.executeQuery();

                if (resultSet3.next()) {
                    Integer currentStock = resultSet3.getInt("quantity");
                    Integer newStockQuantity = currentStock - resultProductQuantity;

                    String query4 = "UPDATE Products SET quantity = ? WHERE productCode = ?";
                    PreparedStatement statement4 = connection.prepareStatement(query4);
                    statement4.setInt(1, newStockQuantity);
                    statement4.setString(2, resultProductCode);
                    int rowsUpdated = statement4.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Quantity updated successfully for ProductCode: " + resultProductCode);
                    } else {
                        System.out.println("No rows updated for ProductCode: " + resultProductCode);
                    }
                    statement4.close();
                }
                resultSet3.close();
                statement3.close();
            }
            resultSet2.close();
            statement2.close();
        }
        resultSet.close();
        statement.close();
    }
}
