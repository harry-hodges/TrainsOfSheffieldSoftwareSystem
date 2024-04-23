package com.sheffield.views;
import com.sheffield.model.Address;
/**
 * @author afiq_ismail
 */
import com.sheffield.model.DatabaseConnectionHandler;
import com.sheffield.model.DatabaseOperations;
import com.sheffield.model.Role;
import com.sheffield.model.User;

import com.sheffield.util.EmailValidator;
import com.sheffield.util.InputSanitizer;

import java.awt.*;
import java.sql.Connection;
import javax.swing.*;

public class RegisterScreen extends JFrame {
    /**
     * Needed for serialisation
     */
    private static final long serialVersionUID = 1L;
    // Create an instance of DatabaseConnectionHandler for managing database connections
    DatabaseOperations databaseOperations = new DatabaseOperations();

    // Variables declaration
    private javax.swing.JLabel alreadymemberLabel;
    private javax.swing.JTextField citynameField;
    private javax.swing.JLabel citynameLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField forenameField;
    private javax.swing.JLabel forenameLabel;
    private javax.swing.JLabel gotologinLabel;
    private javax.swing.JTextField housenumberField;
    private javax.swing.JLabel housenumberLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JTextField postcodeField;
    private javax.swing.JLabel postcodeLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JTextField roadnameField;
    private javax.swing.JLabel roadnameLabel;
    private javax.swing.JTextField surnameField;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration

    /**
     * Creates RegisterScreen constructor
     */
    public RegisterScreen(Connection connection) {
        super();

        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Dimension screenSize = toolkit.getScreenSize();

        setSize(screenSize.width/2, screenSize.height/2);
        setLocation(screenSize.width/4, screenSize.height/4);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // initialise widgets and other components
        initComponents(connection);

        setVisible(true);
    }

    /**
     * Initialise widgets and other components
     */
    private void initComponents(Connection connection) {

        jPanel1 = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        registerButton = new javax.swing.JButton();
        alreadymemberLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        passwordField = new javax.swing.JTextField();
        surnameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        forenameField = new javax.swing.JTextField();
        forenameLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        surnameLabel = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        housenumberLabel = new javax.swing.JLabel();
        housenumberField = new javax.swing.JTextField();
        roadnameLabel = new javax.swing.JLabel();
        roadnameField = new javax.swing.JTextField();
        citynameField = new javax.swing.JTextField();
        citynameLabel = new javax.swing.JLabel();
        postcodeLabel = new javax.swing.JLabel();
        postcodeField = new javax.swing.JTextField();
        gotologinLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register Screen");
        setMaximumSize(new java.awt.Dimension(574, 620));
        setMinimumSize(new java.awt.Dimension(574, 620));
        setPreferredSize(new java.awt.Dimension(574, 620));
        setResizable(false);

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        titleLabel.setText("REGISTER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(titleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titleLabel))
        );

        jPanel2.setMaximumSize(new java.awt.Dimension(562, 527));
        jPanel2.setMinimumSize(new java.awt.Dimension(562, 527));

        registerButton.setText("REGISTER");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                register(evt, connection);
            }
        });

        alreadymemberLabel.setText("Already a member? Login");

        passwordLabel.setText("Password:");

        emailLabel.setText("Email:");

        forenameLabel.setText("Forename:");

        surnameLabel.setText("Surname:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(emailLabel)
                            .addComponent(surnameLabel)
                            .addComponent(passwordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(forenameLabel)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(forenameField)
                    .addComponent(emailField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(surnameField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(forenameLabel)
                    .addComponent(forenameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(surnameLabel)
                    .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Personal Record", jPanel3);

        housenumberLabel.setText("House number:");

        roadnameLabel.setText("Road name:");

        citynameLabel.setText("City name:");

        postcodeLabel.setText("Postcode:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(postcodeLabel)
                    .addComponent(roadnameLabel)
                    .addComponent(housenumberLabel)
                    .addComponent(citynameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(citynameField)
                    .addComponent(housenumberField)
                    .addComponent(roadnameField)
                    .addComponent(postcodeField, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(housenumberLabel)
                    .addComponent(housenumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roadnameLabel)
                    .addComponent(roadnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(citynameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(citynameLabel))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(postcodeLabel)
                    .addComponent(postcodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Address Record", jPanel4);

        gotologinLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        gotologinLabel.setForeground(new java.awt.Color(0, 204, 204));
        gotologinLabel.setText("here.");
        gotologinLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToLoginScreen(evt, connection);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(alreadymemberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gotologinLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(registerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alreadymemberLabel)
                    .addComponent(gotologinLabel))
                .addGap(20, 20, 20))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    /**
     * Action-button || other functions | listeners
     */
   private void goToLoginScreen(java.awt.event.MouseEvent  evt, Connection connection) {
        dispose();
        new LoginScreen(connection);
    }

    private void register(java.awt.event.ActionEvent evt, Connection connection) {
        forenameLabel.setForeground(Color.BLACK);
        surnameLabel.setForeground(Color.BLACK);
        emailLabel.setForeground(Color.BLACK);
        passwordLabel.setForeground(Color.BLACK);
        housenumberLabel.setForeground(Color.BLACK);
        roadnameLabel.setForeground(Color.BLACK);
        citynameLabel.setForeground(Color.BLACK);
        postcodeLabel.setForeground(Color.BLACK);

        JFrame frame = new JFrame();
        String forename = InputSanitizer.trimMiddleWhitespaces(forenameField.getText().trim());
        String surname = InputSanitizer.trimMiddleWhitespaces(surnameField.getText().trim());
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String houseNumber = InputSanitizer.trimMiddleWhitespaces(housenumberField.getText().trim());
        String roadName = InputSanitizer.trimMiddleWhitespaces(roadnameField.getText().trim());
        String cityName = InputSanitizer.trimMiddleWhitespaces(citynameField.getText().trim());
        String postcode = InputSanitizer.trimMiddleWhitespaces(postcodeField.getText().trim());

        if (forename.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || houseNumber.isEmpty() || roadName.isEmpty() || cityName.isEmpty() || postcode.isEmpty()) {
            if (forename.isEmpty()) {
                forenameLabel.setForeground(Color.RED);
            }
            if (surname.isEmpty()) {
                surnameLabel.setForeground(Color.RED);
            }
            if (email.isEmpty()) {
                emailLabel.setForeground(Color.RED);
            }
            if (password.isEmpty()) {
                passwordLabel.setForeground(Color.RED);
            }
            if (houseNumber.isEmpty()) {
                housenumberLabel.setForeground(Color.RED);
            }
            if (roadName.isEmpty()) {
                roadnameLabel.setForeground(Color.RED);
            }
            if (cityName.isEmpty()) {
                citynameLabel.setForeground(Color.RED);
            }
            if (postcode.isEmpty()) {
                postcodeLabel.setForeground(Color.RED);
            }

            JOptionPane.showMessageDialog(frame, "Text fields cannot be empty.");

        } else if (!(EmailValidator.isValidEmail(email))) {
            JOptionPane.showMessageDialog(frame, "Email address is invalid.");
            emailLabel.setForeground(Color.RED);
        } else {
            try {
                if (databaseOperations.verifyEmailIsUsed(connection, email) == false && databaseOperations.verifyAddressIsUsed(connection, houseNumber, postcode) == false) {
                    char[] charPassword = password.toCharArray();
                    Address newAddress = new Address(houseNumber, roadName, cityName, postcode);
                    databaseOperations.addNewAddress(connection, newAddress);
                    User newUser = new User(forename, surname, email, charPassword, Role.USER, postcode, houseNumber);
                    databaseOperations.registerNewUser(connection, newUser);

                    JOptionPane.showMessageDialog(frame, "Register success. Redirecting to login screen");
                    dispose();
                    new LoginScreen(connection);
                } else {
                    if (databaseOperations.verifyEmailIsUsed(connection, email) == true) {
                        JOptionPane.showMessageDialog(frame, "Email already used.");
                        emailLabel.setForeground(Color.RED);
                    } else if (databaseOperations.verifyAddressIsUsed(connection, houseNumber, postcode) == true) {
                        JOptionPane.showMessageDialog(frame, "Address already used.");
                        postcodeLabel.setForeground(Color.RED);
                        housenumberLabel.setForeground(Color.RED);
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

}
