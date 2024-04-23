package com.sheffield.views;
import com.sheffield.model.CurrentUserManager;
/**
 * @author afiq_ismail
 */
import com.sheffield.model.DatabaseConnectionHandler;
import com.sheffield.model.DatabaseOperations;
import com.sheffield.util.EmailValidator;
import com.sheffield.util.InputSanitizer;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class ProfileScreen extends JFrame {
    /**
     * Needed for serialisation
     */
    private static final long serialVersionUID = 1L;

    // Variables declaration                 
    private javax.swing.JButton updateDetailsButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;

    // Create an instance of DatabaseOperation to interact with database
    DatabaseOperations databaseOperations = new DatabaseOperations();
    // End of variables declaration
    
    /**
     * Creates ProfileScreen constructor
     */
    public ProfileScreen(Connection connection, String id) {
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
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        updateDetailsButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Profile Screen");
        setMinimumSize(new java.awt.Dimension(623, 574));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("PROFILE");

        jButton3.setText("Main Screen");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToMainScreen(connection, id, evt);
            }
        });

        jButton4.setText("Logout");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToLoginScreen(connection, evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(151, 151, 151)
                .addComponent(jButton3)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jLabel1))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabel2.setText("Forename:");

        jLabel3.setText("Surname:");

        emailLabel.setText("Email:");

        jLabel5.setText("Password:");

        updateDetailsButton.setText("UPDATE");

        //Display name, email, etc
        try {
            jTextField1.setText(databaseOperations.getRecordFromTable(connection,"forename", "Users", id));
            jTextField2.setText(databaseOperations.getRecordFromTable(connection,"surname", "Users", id));
            jTextField3.setText(databaseOperations.getRecordFromTable(connection,"email", "Users", id));
            jTextField4.setText(null);
            jTextField5.setText(databaseOperations.getRecordFromTable(connection,"bankCardName", "Users", id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        final Boolean[] bankValid = {false};

        updateDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                //Initialize
                try {
                    JFrame frame = new JFrame();

                    String oldForename = databaseOperations.getRecordFromTable(connection,"forename", "Users", id);
                    String oldSurname = databaseOperations.getRecordFromTable(connection,"surname", "Users", id);
                    String oldEmail = databaseOperations.getRecordFromTable(connection,"email", "Users", id);
                    String oldBankName = databaseOperations.getRecordFromTable(connection,"bankCardName", "Users", id);
                    if (oldBankName == null) {
                        oldBankName="";
                    }

                    String enteredForename = InputSanitizer.trimMiddleWhitespaces(jTextField1.getText().trim());
                    String enteredSurname = InputSanitizer.trimMiddleWhitespaces(jTextField2.getText().trim());
                    String enteredEmail = jTextField3.getText().trim();
                    String enteredPassword = jTextField4.getText();
                    String enteredBankName = InputSanitizer.trimMiddleWhitespaces(jTextField5.getText().trim());

                    //Check if X is changed, update X
                    if (!oldForename.equals(enteredForename)) {
                        if (!enteredForename.isEmpty()) {
                            databaseOperations.updateUserDetails(connection, "forename", enteredForename, id);
                            System.out.println("Forename updated");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Forename cannot be empty");
                        }
                    }

                    if (!oldSurname.equals(enteredSurname)) {
                        if (!enteredSurname.isEmpty()) {
                            databaseOperations.updateUserDetails(connection, "surname", enteredSurname, id);
                            System.out.println("Surname updated");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Surname cannot be empty");
                        }
                    }

                    if (!oldBankName.equals(enteredBankName)) {
                        if (bankValid[0]) {
                            databaseOperations.updateUserDetails(connection, "bankCardName", enteredBankName, id);
                            System.out.println("Bank name updated");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Seems you have changed your Bank name. Please verify it before update");
                        }
                    }

                    if (!enteredEmail.equals(oldEmail)) {
                        //Email is changed
                        if (EmailValidator.isValidEmail(enteredEmail)) {
                            //Email entered is valid format
                            if (!databaseOperations.verifyEmailIsUsed(connection, enteredEmail)) {
                                //Email entered is available
                                databaseOperations.updateUserDetails(connection, "email", enteredEmail, id);
                                System.out.println("Email address updated");
                            } else { //Email entered is not available
                                JOptionPane.showMessageDialog(frame, "Email already used. Email is not updated");
                            }
                        } else { //Email entered is invalid format
                            JOptionPane.showMessageDialog(frame, "Email is invalid. Email is not updated");
                        }
                    }

                    if (enteredPassword != null) { //Check if password is NOT in its initialized value
                        if (!(enteredPassword.isEmpty())) { //Check if password is NOT empty
                            //Both of these if statement will assume User wanted to change his/her password
                            //Password is changed
                            databaseOperations.updateUserPassword(connection, enteredPassword, id);
                            System.out.println("Password updated");
                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                goToProfile(connection, id, evt);
            }
        });

        jLabel6.setText("Payment Method:");

        jButton2.setText("VERIFY PAYMENT METHOD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JFrame frame = new JFrame();
                String enteredBankName = jTextField5.getText();
                if (InputSanitizer.isLettersOnly(enteredBankName) && !(enteredBankName.trim().isEmpty())) {
                    bankValid[0] = true;
                    JOptionPane.showMessageDialog(frame, "Payment method is accepted. You can update your payment method now");
                } else { // Contains non-letters (symbol,numbers,etc)
                    JOptionPane.showMessageDialog(frame, "Payment method cannot be used");
                }
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addGap(50, 50, 50)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(emailLabel)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField3)
                                .addComponent(jTextField4)
                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(updateDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(updateDetailsButton)
                .addContainerGap(81, Short.MAX_VALUE))
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
    private void updateDetails(Connection connection, java.awt.event.ActionEvent evt) {
        try {
            System.out.println(databaseOperations.changeEmail(connection, CurrentUserManager.getCurrentUser(), emailLabel.getText()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void goToMainScreen(Connection connection, String id, java.awt.event.ActionEvent evt) {
        dispose();
        new ProductListingScreen(connection, id);
    }                                        

    private void goToLoginScreen(Connection connection, java.awt.event.ActionEvent evt) {
        dispose();
        new LoginScreen(connection);
    }

    private void goToProfile(Connection connection, String id, java.awt.event.ActionEvent evt) {
        dispose();
        new ProfileScreen(connection, id);
    }
}
