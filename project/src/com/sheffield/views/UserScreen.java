package com.sheffield.views;
/**
 * @author afiq_ismail
 */
import com.sheffield.model.DatabaseConnectionHandler;
import com.sheffield.model.DatabaseOperations;
import com.sheffield.util.ButtonEditor;
import com.sheffield.util.ButtonRenderer;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserScreen extends JFrame {
    /**
     * Needed for serialisation
     */
    private static final long serialVersionUID = 1L;
    DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();
    DatabaseOperations databaseOperations = new DatabaseOperations();

    // Variables declaration                 
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane productTab;
    private javax.swing.JScrollPane jScrollPane1;
    // private javax.swing.JTable jTable1;
    // End of variables declaration
    
    /**
     * Creates UserScreen constructor
     */
    public UserScreen(Connection connection, String id) {
        super();

        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/4, screenSize.height/4);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialise widgets and other components
        initComponents(connection, id);

        setVisible(true);

        // displayListRecords(connection, "Users", "userID",0);
        // displayListRecords(connection, "Users", "forename",1);
        // displayListRecords(connection, "Users", "email",2);
        // displayListRecords(connection, "Roles", "role",3);
    }

    /**
     * Initialise widgets and other components
     */
    private void initComponents(Connection connection, String id) {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        productTab = new javax.swing.JTabbedPane();
        jPanel2 = createPanel(connection);
        jScrollPane1 = new javax.swing.JScrollPane();
        // jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        productTab.addTab("All Users", jPanel2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Screen");
        setMinimumSize(new java.awt.Dimension(1216, 636));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("USER SCREEN");

        jButton1.setText("Staff Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToStaffDashboard(connection, id, evt);
            }
        });

        jButton2.setText("Logout");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToLogout(connection, evt);
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
                .addGap(345, 345, 345)
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

        // jTable1.setModel(new javax.swing.table.DefaultTableModel(
        //     new Object [][] {
        //         {null, null, null, null},
        //     },
        //     new String [] {
        //         "User ID", "Name", "Email Address", "Role"
        //     }
        // ));
        // jScrollPane1.setViewportView(jTable1);

        jButton3.setText("View User");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToUserFormScreen(connection, id,evt);
            }
        });

        // javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        // jPanel2.setLayout(jPanel2Layout);
        // jPanel2Layout.setHorizontalGroup(
        //     jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGroup(jPanel2Layout.createSequentialGroup()
        //         .addGap(15, 15, 15)
        //         .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1172, javax.swing.GroupLayout.PREFERRED_SIZE)
        //         .addContainerGap(17, Short.MAX_VALUE))
        //     .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        //         .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        //         .addComponent(jButton3)
        //         .addGap(36, 36, 36))
        // );
        // jPanel2Layout.setVerticalGroup(
        //     jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGroup(jPanel2Layout.createSequentialGroup()
        //         .addGap(17, 17, 17)
        //         .addComponent(jButton3)
        //         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        //         .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
        //         .addContainerGap(18, Short.MAX_VALUE))
        // );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productTab)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(productTab)
                .addContainerGap())
        );

        pack();
    }
    
    /**
     * Action-button || other functions | listeners
     */
    private void goToLogout(Connection connection, java.awt.event.ActionEvent evt) {
        dispose();
        new LoginScreen(connection);
    }                                        

    private void goToStaffDashboard(Connection connection, String id, java.awt.event.ActionEvent evt) {
        dispose();
        new StaffDashboardScreen(connection, id);
    }   

    private void goToUserFormScreen(Connection connection, String id, java.awt.event.ActionEvent evt) {
        dispose();
        new UserFormScreen(connection, id);
    }

    // public void displayListRecords(Connection connection, String tableName, String columnName, int colNum) {
    //     try {
    //         List<Object> userIDList = databaseOperations.getListFromTable(connection, tableName, columnName);
    //         DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    //         if (userIDList.size() > jTable1.getRowCount()) {
    //             // Add new rows to table
    //             int rowsToAdd = userIDList.size() - jTable1.getRowCount();
    //             for (int i = 0; i < rowsToAdd; i++) {
    //                 model.addRow(new Object[model.getColumnCount()]);
    //             }
    //         }

    //         for (int i = 0; i  <  userIDList.size(); i++) {
    //             model.setValueAt(userIDList.get(i), i, colNum);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

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
            ResultSet userResultSet;
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
            
            userResultSet = databaseOperations.getAllAggregatedTable(connection, "Users", "Roles", ", a.role");
            userModel = buildTableModel(userResultSet);
            for (int i = 0; i < databaseOperations.countUser(connection); i++) {
                countModel.addRow(new Object[]{i+1});
            }
            combinedTableModel = countModel;
            combinedTableModel = combineTableModels(countModel, userModel);
            actionModel.addColumn("Action");
            for (int i = 0; i < databaseOperations.countProduct("Users", connection); i++) {
                actionModel.addRow(new Object[]{ "Promote | Demote"});
            }
            combinedTableModel = combineTableModels(combinedTableModel, actionModel);
            userTable.setModel(combinedTableModel);
            userTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellRenderer(new ButtonRenderer());
            userTable.getColumnModel().getColumn(combinedTableModel.getColumnCount() - 1).setCellEditor(new ButtonEditor(new JTextField(), userTable, connection));
            userTable.setColumnSelectionAllowed(true);
            jScrollPane2.setViewportView(userTable);

        } catch(SQLException e) {
            userTable.setModel(new javax.swing.table.DefaultTableModel(
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
