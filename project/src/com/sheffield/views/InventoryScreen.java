package com.sheffield.views;
import com.sheffield.model.CurrentUserManager;
/**
 * @author afiq_ismail
 */
import com.sheffield.model.DatabaseConnectionHandler;
import com.sheffield.model.DatabaseOperations;
import com.sheffield.util.ButtonRenderer;
import com.sheffield.util.ButtonEditor;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InventoryScreen extends JFrame {
    /**
     * Needed for serialisation
     */
    private static final long serialVersionUID = 1L;
    DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();
    DatabaseOperations databaseOperations = new DatabaseOperations();

    // Variables declaration                 
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton addnewproductButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel productPanel;
    private javax.swing.JPanel trainSetPanel;
    private javax.swing.JPanel trackPackPanel;
    private javax.swing.JPanel trackPanel;
    private javax.swing.JPanel locomotivePanel;
    private javax.swing.JPanel rollingStockPanel;
    private javax.swing.JPanel controllerPanel;
    private javax.swing.JTabbedPane productTab;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;

    // End of variables declaration
    
    /**
     * Creates InventoryScreen constructor
     */
    public InventoryScreen(Connection connection, String id) {
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

    /**
     * Initialise widgets and other components
     */
    private void initComponents(Connection connection, String id) {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        addnewproductButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        productTab = new javax.swing.JTabbedPane();
        productPanel = createPanel(0, connection);
        trainSetPanel = createPanel(1, connection);
        trackPackPanel = createPanel(2, connection);
        trackPanel = createPanel(3, connection);
        locomotivePanel = createPanel(4, connection);
        rollingStockPanel = createPanel(5, connection);
        controllerPanel = createPanel(6, connection);

        productTab.addTab("All Product", productPanel);
        productTab.addTab("Train Set", trainSetPanel);
        productTab.addTab("Track Pack", trackPackPanel);
        productTab.addTab("Track", trackPanel);
        productTab.addTab("Locomotive", locomotivePanel);
        productTab.addTab("Rolling Stock", rollingStockPanel);
        productTab.addTab("Controller", controllerPanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1216, 636));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("INVENTORY SCREEN");

        jButton1.setText("Staff Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToStaffDashboard(evt, id, connection);
            }
        });

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToLoginScreen(evt, connection);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(302, 302, 302)
                .addComponent(jButton1)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        addnewproductButton.setText("Add New Item");
        addnewproductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToAddItemForm(evt, connection, id);
            }
        });

        jLabel2.setText("Order Queue:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Product Low on Stock");

        jLabel4.setText("Product Out of Stock");

        //Display count value OrderQueue, ProductLowOnStock, ProductOutOfStock
        String query = "SELECT COUNT(*) AS orderQueue FROM Orders WHERE status =?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1,"CONFIRMED");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int orderQueue = resultSet.getInt("orderQueue");
                jTextField1.setText(String.valueOf(orderQueue));
            }

            query = "SELECT COUNT(*) AS lowStock FROM Products WHERE quantity > ? AND quantity < ?";
            statement = connection.prepareStatement(query);
            statement.setString(1,"0");
            statement.setString(2,"10");
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int lowStock = resultSet.getInt("lowStock");
                jTextField2.setText(String.valueOf(lowStock));
            }

            query = "SELECT COUNT(*) AS outOfStock FROM Products WHERE quantity < ?";
            statement = connection.prepareStatement(query);
            statement.setString(1,"1");
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int outOfStock = resultSet.getInt("outOfStock");
                jTextField3.setText(String.valueOf(outOfStock));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addnewproductButton)
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addnewproductButton)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productTab))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(productTab)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }
    
    /**
     * Action-button || other functions | listeners
     */
    private void goToLoginScreen(java.awt.event.ActionEvent evt, Connection connection) {                                         
        dispose();
        new LoginScreen(connection);
    }                                        

    private void goToStaffDashboard(java.awt.event.ActionEvent evt, String id, Connection connection) {                                         
        dispose();
        new StaffDashboardScreen(connection, id);
    }                                        

    private void goToAddItemForm(java.awt.event.ActionEvent evt, Connection connection, String id) {                                         
        dispose();
        new ItemFormScreen(connection, id);
    }                                        

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    } 

    private JPanel createPanel(int selectedIndex, Connection connection) {
        JPanel productPanel = new javax.swing.JPanel();
        JTable productTable = new javax.swing.JTable();
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
            ResultSet productResultSet;
            ResultSet foreignProductResultSet;
            DefaultTableModel countModel = new DefaultTableModel();
            countModel.addColumn("No.");
            DefaultTableModel productModel = new DefaultTableModel();
            DefaultTableModel foreignProductModel = new DefaultTableModel();
            DefaultTableModel combinedTableModel = new DefaultTableModel();
            // // Create a DefaultTableModel with a JButton column
            DefaultTableModel actionModel = new DefaultTableModel() {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnIndex == 2 ? JButton.class : Object.class; // Column index 2 contains buttons
                }
            };
            
            switch (selectedIndex) {
                case 0:
                    productResultSet = databaseOperations.getAllTableData(connection, "Products");
                    productModel = buildTableModel(productResultSet);
                    for (int i = 0; i < databaseOperations.countProduct("Products", connection); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, productModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countProduct("Products", connection); i++) {
                        actionModel.addRow(new Object[]{ "Update | Delete"});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                case 1:
                    foreignProductResultSet = databaseOperations.getAllAggregatedTable(connection, "Products", "TrainSets", ", a.era");
                    foreignProductModel = buildTableModel(foreignProductResultSet);
                    for (int i = 0; i < databaseOperations.countProduct("TrainSets", connection); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, foreignProductModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countProduct("TrainSets", connection); i++) {
                        actionModel.addRow(new Object[]{"Update | Delete"});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                case 2:
                    foreignProductResultSet = databaseOperations.getAllAggregatedTable(connection, "Products", "TrackPacks", "");
                    foreignProductModel = buildTableModel(foreignProductResultSet);
                    for (int i = 0; i < databaseOperations.countProduct("TrackPacks", connection); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, foreignProductModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countProduct("TrackPacks", connection); i++) {
                        actionModel.addRow(new Object[]{"Update | Delete"});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                case 3:
                    foreignProductResultSet = databaseOperations.getAllAggregatedTable(connection, "Products", "Tracks", "");
                    foreignProductModel = buildTableModel(foreignProductResultSet);
                    for (int i = 0; i < databaseOperations.countProduct("Tracks", connection); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, foreignProductModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countProduct("Tracks", connection); i++) {
                        actionModel.addRow(new Object[]{"Update | Delete"});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                case 4:
                    foreignProductResultSet = databaseOperations.getAllAggregatedTable(connection, "Products", "Locomotives", ", a.era, a.dcc");
                    foreignProductModel = buildTableModel(foreignProductResultSet);
                    for (int i = 0; i < databaseOperations.countProduct("Locomotives", connection); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, foreignProductModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countProduct("Locomotives", connection); i++) {
                        actionModel.addRow(new Object[]{"Update | Delete"});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                case 5:
                    foreignProductResultSet = databaseOperations.getAllAggregatedTable(connection, "Products", "RollingStocks", ", a.era, a.rollingStockType");
                    foreignProductModel = buildTableModel(foreignProductResultSet);
                    for (int i = 0; i < databaseOperations.countProduct("RollingStocks", connection); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, foreignProductModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countProduct("RollingStocks", connection); i++) {
                        actionModel.addRow(new Object[]{"Update | Delete"});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                case 6:
                    foreignProductResultSet = databaseOperations.getAllAggregatedTable(connection, "Products", "Controllers", ", a.dcc");
                    foreignProductModel = buildTableModel(foreignProductResultSet);
                    for (int i = 0; i < databaseOperations.countProduct("Controllers", connection); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, foreignProductModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countProduct("Controllers", connection); i++) {
                        actionModel.addRow(new Object[]{"Update | Delete"});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                default:
                    break;
            }

        } catch(SQLException e) {
            productTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null}
                },
                new String [] {
                    "No.", "Product Code", "Product Name", "Brand Name", "Gauge Scale", "Era", "Locomotive Type", "Rolling Stock Type", "Controller Type", "Remark", "Action"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
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
