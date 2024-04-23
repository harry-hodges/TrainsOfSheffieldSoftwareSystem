package com.sheffield.views;
import com.sheffield.model.CurrentUserManager;
/**
 * @author afiq_ismail
 */
import com.sheffield.model.DatabaseConnectionHandler;
import com.sheffield.model.DatabaseOperations;
import com.sheffield.model.Status;
import com.sheffield.util.ButtonRenderer;
import com.sheffield.util.ButtonEditor;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyOrderScreen extends JFrame {
    /**
     * Needed for serialisation
     */
    private static final long serialVersionUID = 1L;
    DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();
    DatabaseOperations databaseOperations = new DatabaseOperations();

    // Variables declaration                 
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JTabbedPane productTab;

    // End of variables declaration
    
    /**
     * Creates OrderScreen constructor
     */
    public MyOrderScreen(Connection connection, String id) {
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        productTab = new javax.swing.JTabbedPane();
        productPanel = createPanel(0, connection);
        trainSetPanel = createPanel(1, connection);
        trackPackPanel = createPanel(2, connection);
        trackPanel = createPanel(3, connection);

        productTab.addTab("All", productPanel);
        productTab.addTab("Confirmed", trainSetPanel);
        productTab.addTab("Fulfilled", trackPackPanel);
        productTab.addTab("Declined", trackPanel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1216, 636));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MY ORDER SCREEN");

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToMainScreen(evt, connection, id);
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

        jLabel2.setText("");

        jLabel3.setText("");

        jLabel4.setText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGap(123, 123, 123)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGap(89, 89, 89)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
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

    private void goToMainScreen(java.awt.event.ActionEvent evt, Connection connection, String id) {                                         
        dispose();
        new ProductListingScreen(connection, id);
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

            String currentUserID = CurrentUserManager.getCurrentUser().getUserID();
            switch (selectedIndex) {
                case 0:
                    productResultSet = databaseOperations.getAllOrdersDataWhereUserID(connection, Status.PENDING, currentUserID);
                    productModel = buildTableModel(productResultSet);
                    for (int i = 0; i < databaseOperations.countOrderStatusWhereUserID(Status.PENDING, connection, currentUserID); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, productModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countOrderStatusWhereUserID(Status.PENDING, connection, currentUserID); i++) {
                        actionModel.addRow(new Object[]{"View "});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                case 1:
                    foreignProductResultSet = databaseOperations.getAllOrdersDataWhereUserID(connection, Status.CONFIRMED, currentUserID);
                    foreignProductModel = buildTableModel(foreignProductResultSet);
                    for (int i = 0; i < databaseOperations.countOrderStatusWhereUserID(Status.CONFIRMED, connection, currentUserID); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, foreignProductModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countOrderStatusWhereUserID(Status.CONFIRMED, connection, currentUserID); i++) {
                        actionModel.addRow(new Object[]{"View "});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                case 2:
                    foreignProductResultSet = databaseOperations.getAllOrdersDataWhereUserID(connection, Status.FULFILLED, currentUserID);
                    foreignProductModel = buildTableModel(foreignProductResultSet);
                    for (int i = 0; i < databaseOperations.countOrderStatusWhereUserID(Status.FULFILLED, connection, currentUserID); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, foreignProductModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countOrderStatusWhereUserID(Status.FULFILLED, connection, currentUserID); i++) {
                        actionModel.addRow(new Object[]{"View "});
                    }
                    combinedTableModel = combineTableModels(combinedTableModel, actionModel);
                    productTable.setModel(combinedTableModel);
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
                    productTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), productTable, connection));
                    productTable.setColumnSelectionAllowed(true);
                    jScrollPane2.setViewportView(productTable);
                    break;
                case 3:
                    foreignProductResultSet = databaseOperations.getAllOrdersDataWhereUserID(connection, Status.DECLINED, currentUserID);
                    foreignProductModel = buildTableModel(foreignProductResultSet);
                    for (int i = 0; i < databaseOperations.countOrderStatusWhereUserID(Status.DECLINED, connection, currentUserID); i++) {
                        countModel.addRow(new Object[]{i+1});
                    }
                    combinedTableModel = combineTableModels(countModel, foreignProductModel);
                    actionModel.addColumn("Action");
                    for (int i = 0; i < databaseOperations.countOrderStatusWhereUserID(Status.DECLINED, connection, currentUserID); i++) {
                        actionModel.addRow(new Object[]{"View "});
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
