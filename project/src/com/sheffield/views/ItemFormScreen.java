package com.sheffield.views;
/**
 * @author afiq_ismail
 */
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sheffield.model.Controller;
import com.sheffield.model.CurrentUserManager;
import com.sheffield.model.DatabaseConnectionHandler;
import com.sheffield.model.DatabaseOperations;
import com.sheffield.model.Locomotive;
import com.sheffield.model.Product;
import com.sheffield.model.RollingStock;
import com.sheffield.model.Track;
import com.sheffield.model.TrackPack;
import com.sheffield.model.TrainSet;

public class ItemFormScreen extends JFrame {
    /**
     * Needed for serialisation
     */
    private static final long serialVersionUID = 1L;
    private int trainSetCount = 0;
    private int trackPackCount = 0;
    private int trackCount = 0;
    private int locomotiveCount = 0;
    private int rollingStockCount = 0;
    private int controllerCount = 0;
    private String currentProductCode = "";
    private ResultSet currentProductResultSet;
    DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();
    DatabaseOperations databaseOperations = new DatabaseOperations();

    // Variables declaration                 
    private javax.swing.JButton jButton16;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JTabbedPane addProductTab;
    // End of variables declaration
    
    /**
     * Creates ItemFormScreen constructor
     */
    public ItemFormScreen(Connection connection, String id) {
        super();

        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/4, screenSize.height/4);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialise widgets and other components
        try {        
            trainSetCount = databaseOperations.countProduct("TrainSets", connection) + 1;
            trackPackCount = databaseOperations.countProduct("TrackPacks", connection) + 1;
            trackCount = databaseOperations.countProduct("Tracks", connection) + 1;
            locomotiveCount = databaseOperations.countProduct("Locomotives", connection) + 1;
            rollingStockCount = databaseOperations.countProduct("RollingStocks", connection) + 1;
            controllerCount = databaseOperations.countProduct("Controllers", connection) + 1;
            initComponents(connection, id);
            setVisible(true);
        } catch (Throwable t) {
            // Close connection if database crashes.
            databaseConnectionHandler.closeConnection();
            throw new RuntimeException(t);
        }

        setVisible(true);
    }

    public ItemFormScreen(Connection connection, String productCode, String id) {
        super();

        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/4, screenSize.height/4);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialise widgets and other components
        try {        
            currentProductCode = productCode;
            initComponents(connection, id);
            setVisible(true);
        } catch (Throwable t) {
            // Close connection if database crashes.
            databaseConnectionHandler.closeConnection();
            throw new RuntimeException(t);
        }

        setVisible(true);
    }

    /**
     * Initialise widgets and other components
     */
    private void initComponents(Connection connection, String id) {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        addProductTab = new javax.swing.JTabbedPane();
        formPanel = createPanel(0, connection, id);
        jPanel27 = createPanel(1, connection, id);
        jPanel28 = createPanel(2, connection, id);
        jPanel29 = createPanel(3, connection, id);
        jPanel30 = createPanel(4, connection, id);
        jPanel31 = createPanel(5, connection, id);


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Add Product Form");
        setMinimumSize(new java.awt.Dimension(623, 574));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        
        if (currentProductCode == "") {
            jLabel1.setText("ADD PRODUCT FORM");
            addProductTab.addTab("Train Set", formPanel);
            addProductTab.addTab("Track Pack", jPanel27);
            addProductTab.addTab("Track", jPanel28);
            addProductTab.addTab("Locomotive", jPanel29);
            addProductTab.addTab("Rolling Stock", jPanel30);
            addProductTab.addTab("Controller", jPanel31);
        } else {
            jLabel1.setText("PRODUCT FORM");
            switch (currentProductCode.charAt(0)) {
                case 'M':
                    addProductTab.addTab("Train Set", formPanel);
                    break;
                case 'P':
                    addProductTab.addTab("Track Pack", jPanel27);
                    break;
                case 'R':
                    addProductTab.addTab("Track", jPanel28);
                    break;
                case 'L':
                    addProductTab.addTab("Locomotive", jPanel29);
                    break;
                case 'S':
                    addProductTab.addTab("Rolling Stock", jPanel30);
                    break;
                case 'C':
                    addProductTab.addTab("Controller", jPanel31);
                    break;
                default:
                    break;
            }
        }

        jButton16.setText("Back");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToInventoryScreen(evt, connection, id);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(124, 124, 124)
                .addComponent(jButton16)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton16))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addProductTab))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addProductTab)
                .addContainerGap())
        );

        pack();
    } 
    
    /**
     * Action-button || other functions | listeners
     */

    private void goToInventoryScreen(java.awt.event.ActionEvent evt, Connection connection, String id) {                                          
        dispose();
        new InventoryScreen(connection, id);
    }                                          

    private void deleteProduct(String id, int selectedIndex, String productCode, Connection connection) {   
        try { 
            switch (selectedIndex) {
                case 0:
                    databaseOperations.deleteProduct("TrainSets", productCode, connection);
                    break;
                case 1:
                    databaseOperations.deleteProduct("TrackPacks", productCode, connection);
                    break;
                case 2:
                    databaseOperations.deleteProduct("Tracks", productCode, connection);
                    break;
                case 3:
                    databaseOperations.deleteProduct("Locomotives", productCode, connection);
                    break;
                case 4:
                    databaseOperations.deleteProduct("RollingStocks", productCode, connection);
                    break;
                case 5:
                    databaseOperations.deleteProduct("Controllers", productCode, connection);
                    break;
                default:
                    break;
            }
            
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame,  productCode + " deleted successfully.");
            dispose();
            new InventoryScreen(connection, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }                                  
    }                                        
                                      
    
    private JPanel createPanel(int selectedIndex, Connection connection, String id) {
        JPanel formPanel = new javax.swing.JPanel();
        JLabel productcodeLabel = new javax.swing.JLabel();
        JTextField productcodeField = new javax.swing.JTextField();
        JLabel productnameLabel = new javax.swing.JLabel();
        JTextField productnameField = new javax.swing.JTextField();
        JLabel brandnameLabel = new javax.swing.JLabel();
        JTextField brandnameField = new javax.swing.JTextField();
        JLabel retailpriceLabel = new javax.swing.JLabel();
        JTextField retailpriceField = new javax.swing.JTextField();
        JLabel quantityLabel = new javax.swing.JLabel();
        JTextField quantityField = new javax.swing.JTextField();
        JButton addproductButton = new javax.swing.JButton();
        JLabel gaugeScaleLabel = new javax.swing.JLabel();
        JLabel eraLabel = new javax.swing.JLabel();
        JTextField eraField = new javax.swing.JTextField();
        JComboBox typeComboBox = new javax.swing.JComboBox<>();
        JComboBox gaugeScaleComboBox = new javax.swing.JComboBox<>();
        JLabel typeLabel = new javax.swing.JLabel();
        JButton deleteButton = new javax.swing.JButton();
        JButton cancelButton = new javax.swing.JButton();

        GroupLayout formPanelLayout = new GroupLayout(formPanel);
        int gapHeight = 18;
        String typeLabelString = "";
        DefaultComboBoxModel<String> typeValues = new DefaultComboBoxModel<>(new String[] { "" });
        
        gaugeScaleLabel.setText("Gauge type:");

        eraLabel.setText("Era:");

        gaugeScaleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select gauge scale", "OO-Gauge", "TT-Gauge", "N-GAUGE"}));
        
        typeComboBox.setModel(typeValues);

        typeLabel.setText(typeLabelString);


        if (selectedIndex == 0) {
            if (currentProductCode == "") {
                productcodeField.setText("M" + trainSetCount);
                addproductButton.setText("Add Train Set");
                deleteButton.setVisible(false);
            } else {
                productcodeField.setText(currentProductCode);
                try {
                    currentProductResultSet = databaseOperations.getSelectedAggregatedProducts(currentProductCode, connection, "Products", "TrainSets", ", a.era");
                    while (currentProductResultSet.next()) {
                        productnameField.setText(currentProductResultSet.getString("name"));
                        brandnameField.setText(currentProductResultSet.getString("brandName"));
                        retailpriceField.setText(currentProductResultSet.getString("price"));
                        quantityField.setText(currentProductResultSet.getString("quantity"));
                        char gScale = currentProductResultSet.getString("gaugeScale").charAt(0);
                        if (gScale == 'O') {
                            gaugeScaleComboBox.setSelectedIndex(1);;
                        } else if (gScale == 'T') {
                            gaugeScaleComboBox.setSelectedIndex(2);
                        } else if (gScale == 'N') {
                            gaugeScaleComboBox.setSelectedIndex(3);
                        } else {
                            gaugeScaleComboBox.setSelectedIndex(0);
                        }
                        eraField.setText(currentProductResultSet.getString("era"));
                    };
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addproductButton.setText("Update Train Set");
            }
            typeLabel.setVisible(false);
            typeComboBox.setVisible(false);
        } else if (selectedIndex == 1) {
            if (currentProductCode == "") {
                productcodeField.setText("P" + trackPackCount);
                addproductButton.setText("Add Track Set");
                deleteButton.setVisible(false);
            } else {
                productcodeField.setText(currentProductCode);
                try {
                    currentProductResultSet = databaseOperations.getSelectedAggregatedProducts(currentProductCode, connection, "Products", "TrackPacks", "");
                    while (currentProductResultSet.next()) {
                        productnameField.setText(currentProductResultSet.getString("name"));
                        brandnameField.setText(currentProductResultSet.getString("brandName"));
                        retailpriceField.setText(currentProductResultSet.getString("price"));
                        quantityField.setText(currentProductResultSet.getString("quantity"));
                        char gScale = currentProductResultSet.getString("gaugeScale").charAt(0);
                        if (gScale == 'O') {
                            gaugeScaleComboBox.setSelectedIndex(1);;
                        } else if (gScale == 'T') {
                            gaugeScaleComboBox.setSelectedIndex(2);
                        } else if (gScale == 'N') {
                            gaugeScaleComboBox.setSelectedIndex(3);
                        } else {
                            gaugeScaleComboBox.setSelectedIndex(0);
                        }
                    };
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addproductButton.setText("Update Track Set");
            }
            eraLabel.setVisible(false);
            eraField.setVisible(false);
            typeLabel.setVisible(false);
            typeComboBox.setVisible(false);
        }else if (selectedIndex == 2) {
            if (currentProductCode == "") {
                productcodeField.setText("R" + trackCount);
                addproductButton.setText("Add Track");
                deleteButton.setVisible(false);
            } else {
                productcodeField.setText(currentProductCode);
                try {
                    currentProductResultSet = databaseOperations.getSelectedAggregatedProducts(currentProductCode, connection, "Products", "Tracks", "");
                    while (currentProductResultSet.next()) {
                        productnameField.setText(currentProductResultSet.getString("name"));
                        brandnameField.setText(currentProductResultSet.getString("brandName"));
                        retailpriceField.setText(currentProductResultSet.getString("price"));
                        quantityField.setText(currentProductResultSet.getString("quantity"));
                        char gScale = currentProductResultSet.getString("gaugeScale").charAt(0);
                        if (gScale == 'O') {
                            gaugeScaleComboBox.setSelectedIndex(1);;
                        } else if (gScale == 'T') {
                            gaugeScaleComboBox.setSelectedIndex(2);
                        } else if (gScale == 'N') {
                            gaugeScaleComboBox.setSelectedIndex(3);
                        } else {
                            gaugeScaleComboBox.setSelectedIndex(0);
                        }
                    };
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addproductButton.setText("Update Track");
            }
            eraLabel.setVisible(false);
            eraField.setVisible(false);
            typeLabel.setVisible(false);
            typeComboBox.setVisible(false);
        }else if (selectedIndex == 3) {
            typeLabelString = "Locomotive type:";
            typeValues = new DefaultComboBoxModel<>(new String[] { "select locomotive type", "Analogue", "DCC-Ready", "DCC-Fitted", "DCC-Sound" });
            typeComboBox.setModel(typeValues);
            typeLabel.setText(typeLabelString);

            if (currentProductCode == "") {
                productcodeField.setText("L" + locomotiveCount);
                addproductButton.setText("Add Locomotive");
                deleteButton.setVisible(false);
            } else {
                productcodeField.setText(currentProductCode);
                try {
                    currentProductResultSet = databaseOperations.getSelectedAggregatedProducts(currentProductCode, connection, "Products", "Locomotives", ", a.era, a.dcc");
                    while (currentProductResultSet.next()) {
                        productnameField.setText(currentProductResultSet.getString("name"));
                        brandnameField.setText(currentProductResultSet.getString("brandName"));
                        retailpriceField.setText(currentProductResultSet.getString("price"));
                        quantityField.setText(currentProductResultSet.getString("quantity"));
                        char gScale = currentProductResultSet.getString("gaugeScale").charAt(0);
                        if (gScale == 'O') {
                            gaugeScaleComboBox.setSelectedIndex(1);;
                        } else if (gScale == 'T') {
                            gaugeScaleComboBox.setSelectedIndex(2);
                        } else if (gScale == 'N') {
                            gaugeScaleComboBox.setSelectedIndex(3);
                        } else {
                            gaugeScaleComboBox.setSelectedIndex(0);
                        }
                        eraField.setText(currentProductResultSet.getString("era"));
                        char dcc = currentProductResultSet.getString("dcc").charAt(4);
                        if (dcc == 'O') {
                            typeComboBox.setSelectedIndex(1);;
                        } else if (dcc == 'R') {
                            typeComboBox.setSelectedIndex(2);
                        } else if (dcc == 'F') {
                            typeComboBox.setSelectedIndex(3);
                        } else if (dcc == 'S') {
                            typeComboBox.setSelectedIndex(4);
                        } else {
                            typeComboBox.setSelectedIndex(0);
                        }
                    };
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addproductButton.setText("Update Locomotive");
            }
        }else if (selectedIndex == 4) {
            typeLabelString = "Rolling stock type:";
            typeValues = new DefaultComboBoxModel<>(new String[] { "select rolling stock type", "Carriage", "Wagon" });
            typeComboBox.setModel(typeValues);
            typeLabel.setText(typeLabelString);
            if (currentProductCode == "") {
                productcodeField.setText("S" + rollingStockCount);
                addproductButton.setText("Add Rolling Stock");
                deleteButton.setVisible(false);
            } else {
                productcodeField.setText(currentProductCode);
                try {
                    currentProductResultSet = databaseOperations.getSelectedAggregatedProducts(currentProductCode, connection, "Products", "RollingStocks", ", a.era, a.rollingStockType");
                    while (currentProductResultSet.next()) {
                        productnameField.setText(currentProductResultSet.getString("name"));
                        brandnameField.setText(currentProductResultSet.getString("brandName"));
                        retailpriceField.setText(currentProductResultSet.getString("price"));
                        quantityField.setText(currentProductResultSet.getString("quantity"));
                        char gScale = currentProductResultSet.getString("gaugeScale").charAt(0);
                        if (gScale == 'O') {
                            gaugeScaleComboBox.setSelectedIndex(1);;
                        } else if (gScale == 'T') {
                            gaugeScaleComboBox.setSelectedIndex(2);
                        } else if (gScale == 'N') {
                            gaugeScaleComboBox.setSelectedIndex(3);
                        } else {
                            gaugeScaleComboBox.setSelectedIndex(0);
                        }
                        eraField.setText(currentProductResultSet.getString("era"));
                        char rsType = currentProductResultSet.getString("rollingStockType").charAt(0);
                        if (rsType == 'C') {
                            typeComboBox.setSelectedIndex(1);;
                        } else if (rsType == 'W') {
                            typeComboBox.setSelectedIndex(2);
                        }  else {
                            typeComboBox.setSelectedIndex(0);
                        }
                    };
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addproductButton.setText("Update Rolling Stock");
            }
        }else if (selectedIndex == 5) {
            typeLabelString = "Controller type:";
            typeValues = new DefaultComboBoxModel<>(new String[] { "select controller type", "Analogue", "Digital" });
            typeLabel.setText(typeLabelString);
            typeComboBox.setModel(typeValues);
            if (currentProductCode == "") {
                productcodeField.setText("C" + controllerCount);
                addproductButton.setText("Add Controller");
                deleteButton.setVisible(false);
            } else {
                productcodeField.setText(currentProductCode);
                try {
                    currentProductResultSet = databaseOperations.getSelectedAggregatedProducts(currentProductCode, connection, "Products", "Controllers", ", a.dcc");
                    while (currentProductResultSet.next()) {
                        productnameField.setText(currentProductResultSet.getString("name"));
                        brandnameField.setText(currentProductResultSet.getString("brandName"));
                        retailpriceField.setText(currentProductResultSet.getString("price"));
                        quantityField.setText(currentProductResultSet.getString("quantity"));
                        char gScale = currentProductResultSet.getString("gaugeScale").charAt(0);
                        if (gScale == 'O') {
                            gaugeScaleComboBox.setSelectedIndex(1);;
                        } else if (gScale == 'T') {
                            gaugeScaleComboBox.setSelectedIndex(2);
                        } else if (gScale == 'N') {
                            gaugeScaleComboBox.setSelectedIndex(3);
                        } else {
                            gaugeScaleComboBox.setSelectedIndex(0);
                        }
                        char rsType = currentProductResultSet.getString("dcc").charAt(0);
                        if (rsType == 'A') {
                            typeComboBox.setSelectedIndex(1);;
                        } else if (rsType == 'D') {
                            typeComboBox.setSelectedIndex(2);
                        }  else {
                            typeComboBox.setSelectedIndex(0);
                        }
                    };
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addproductButton.setText("Update Controller");
            }
            eraLabel.setVisible(false);
            eraField.setVisible(false);
            gapHeight = 0;
        } 

        productcodeLabel.setText("Product code:");

        productcodeField.setEditable(false);

        productnameLabel.setText("Product name:");

        brandnameLabel.setText("Brand name:");

        retailpriceLabel.setText("Retail price:");

        quantityLabel.setText("Quantity:");

        addproductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productcodeLabel.setForeground(Color.BLACK);
                productnameLabel.setForeground(Color.BLACK);
                brandnameLabel.setForeground(Color.BLACK);
                retailpriceLabel.setForeground(Color.BLACK);
                quantityLabel.setForeground(Color.BLACK);
                gaugeScaleLabel.setForeground(Color.BLACK);
                eraLabel.setForeground(Color.BLACK);
                typeLabel.setForeground(Color.BLACK);

                JFrame frame = new JFrame();
                String productCode = productcodeField.getText();
                String productName = productnameField.getText();
                String brandName = brandnameField.getText();
                String retailPrice = retailpriceField.getText();
                String quantity = quantityField.getText();
                String gaugeScale = (String) gaugeScaleComboBox.getSelectedItem();
                gaugeScale = gaugeScale.replace("-", "_");
                String era = eraField.getText();
                String typeValue = (String) typeComboBox.getSelectedItem();

                if (productCode.isEmpty() || productName.isEmpty() || brandName.isEmpty() || retailPrice.isEmpty() || quantity.isEmpty() || gaugeScale == "select gauge scale" ||
                    ((selectedIndex == 0 || selectedIndex == 3 || selectedIndex == 4) && era.isEmpty()) ||
                    ((selectedIndex == 3) && typeValue == "select locomotive type") ||
                    ((selectedIndex == 4) && typeValue == "select rolling stock type") ||
                    ((selectedIndex == 5) && typeValue == "select controller type")) {
                    if (productCode.isEmpty()) {
                        productcodeLabel.setForeground(Color.RED);
                    }
                    if (productName.isEmpty()) {
                        productnameLabel.setForeground(Color.RED);
                    }
                    if (brandName.isEmpty()) {
                        brandnameLabel.setForeground(Color.RED);
                    }
                    if (retailPrice.isEmpty()) {
                        retailpriceLabel.setForeground(Color.RED);
                    }
                    if (quantity.isEmpty()) {
                        quantityLabel.setForeground(Color.RED);
                    }
                    if (gaugeScale == "select gauge scale") {
                        gaugeScaleLabel.setForeground(Color.RED);
                    }
                    if ((selectedIndex == 0 || selectedIndex == 3 || selectedIndex == 4) && era.isEmpty()) {
                        eraLabel.setForeground(Color.RED);
                    } 
                    if ((selectedIndex == 3) && typeValue == "select locomotive type") {
                        typeLabel.setForeground(Color.RED);
                    }
                    if ((selectedIndex == 4) && typeValue == "select rolling stock type") {
                        typeLabel.setForeground(Color.RED);
                    }
                    if ((selectedIndex == 3 || selectedIndex == 5) && typeValue == "select controller type") {
                        typeLabel.setForeground(Color.RED);
                    }

                    JOptionPane.showMessageDialog(frame, "Text field cannot be empty");
                } else {
                    if (addproductButton.getText().charAt(0) == 'A') {
                        try {
                            BigDecimal  retailPriceBD = new BigDecimal(Double.parseDouble(retailPrice));
                            int quantityInt = Integer.parseInt(quantity);
                            switch (selectedIndex) {
                                case 0:
                                    TrainSet newTrainSet = new TrainSet(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale, era);
                                    databaseOperations.insertProduct(newTrainSet, connection);
                                    databaseOperations.insertForeignKey(selectedIndex, newTrainSet,  connection);
                                    break;
                                case 1:
                                    TrackPack newTrackPack = new TrackPack(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale);
                                    databaseOperations.insertProduct(newTrackPack, connection);
                                    databaseOperations.insertForeignKey(selectedIndex, newTrackPack,  connection);
                                    break;
                                case 2: 
                                    Track newTrack = new Track(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale);
                                    databaseOperations.insertProduct(newTrack, connection);
                                    databaseOperations.insertForeignKey(selectedIndex, newTrack,  connection);
                                    break;
                                case 3:
                                    if (typeValue.contains("-")) {
                                        typeValue = typeValue.replace("-", "_");
                                    } 
                                    Locomotive newLocomotive = new Locomotive(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale, era, typeValue);
                                    databaseOperations.insertProduct(newLocomotive, connection);
                                    databaseOperations.insertForeignKey(selectedIndex, newLocomotive,  connection);
                                    break;
                                case 4:
                                    RollingStock newRollingStock = new RollingStock(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale, era, typeValue);
                                    databaseOperations.insertProduct(newRollingStock, connection);
                                    databaseOperations.insertForeignKey(selectedIndex, newRollingStock,  connection);
                                    break;
                                case 5: 
                                    Controller newController = new Controller(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale, typeValue);
                                    databaseOperations.insertProduct(newController, connection);
                                    databaseOperations.insertForeignKey(selectedIndex, newController,  connection);
                                    break;
                                default:
                                    break;
                            }
                            dispose();
                            new InventoryScreen(connection, id);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }  else if (addproductButton.getText().charAt(0) == 'U') {
                        try {
                            BigDecimal  retailPriceBD = new BigDecimal(Double.parseDouble(retailPrice));
                            int quantityInt = Integer.parseInt(quantity);
                            switch (selectedIndex) {
                                case 0:
                                    TrainSet newTrainSet = new TrainSet(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale, era);
                                    databaseOperations.updateProduct(newTrainSet, connection);
                                    databaseOperations.updateForeignKey(selectedIndex, newTrainSet,  connection);
                                    break;
                                case 1:
                                    TrackPack newTrackPack = new TrackPack(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale);
                                    databaseOperations.updateProduct(newTrackPack, connection);
                                    break;
                                case 2: 
                                    Track newTrack = new Track(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale);
                                    databaseOperations.updateProduct(newTrack, connection);
                                    break;
                                case 3:
                                    if (typeValue.contains("-")) {
                                        typeValue = typeValue.replace("-", "_");
                                    }
                                    Locomotive newLocomotive = new Locomotive(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale, era, typeValue);
                                    databaseOperations.updateProduct(newLocomotive, connection);
                                    databaseOperations.updateForeignKey(selectedIndex, newLocomotive,  connection);
                                    break;
                                case 4:
                                    RollingStock newRollingStock = new RollingStock(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale, era, typeValue);
                                    databaseOperations.updateProduct(newRollingStock, connection);
                                    databaseOperations.updateForeignKey(selectedIndex, newRollingStock,  connection);
                                    break;
                                case 5: 
                                    Controller newController = new Controller(productCode, productName, brandName, quantityInt, retailPriceBD, gaugeScale, typeValue);
                                    databaseOperations.updateProduct(newController, connection);
                                    databaseOperations.updateForeignKey(selectedIndex, newController,  connection);
                                    break;
                                default:
                                    break;
                            }
                            dispose();
                            new InventoryScreen(connection, id);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProduct(id, selectedIndex, currentProductCode, connection);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToInventoryScreen(evt, connection, id);
            }
        });

        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(deleteButton)
                .addGap(18, 18, 18)
                .addComponent(addproductButton)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addGap(148, 148, 148))
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(retailpriceLabel)
                            .addComponent(brandnameLabel)
                            .addComponent(productcodeLabel)
                            .addComponent(productnameLabel)
                            .addComponent(quantityLabel)
                            .addComponent(gaugeScaleLabel)
                            .addComponent(eraLabel))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, formPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(typeLabel)
                        .addGap(18, 18, 18)))
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(typeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quantityField, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addComponent(productcodeField, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addComponent(productnameField)
                    .addComponent(brandnameField)
                    .addComponent(retailpriceField)
                    .addComponent(eraField)
                    .addComponent(gaugeScaleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productcodeLabel)
                    .addComponent(productcodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productnameLabel)
                    .addComponent(productnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brandnameLabel)
                    .addComponent(brandnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(retailpriceLabel)
                    .addComponent(retailpriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityLabel))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gaugeScaleLabel)
                    .addComponent(gaugeScaleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eraField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eraLabel))
                .addGap(gapHeight, gapHeight, gapHeight)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addproductButton)
                    .addComponent(deleteButton)
                    .addComponent(cancelButton))
                .addGap(29, 29, 29))
        );
        return formPanel;
    }
}
