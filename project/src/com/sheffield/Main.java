// Import necessary libraries and classes
// minimum size 1216, 636
package com.sheffield;

import com.sheffield.model.DatabaseConnectionHandler;
import com.sheffield.views.LoginScreen;

import java.sql.Connection;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Create an instance of DatabaseConnectionHandler for managing database connections
        DatabaseConnectionHandler databaseConnectionHandler = new DatabaseConnectionHandler();

        // Execute the Swing GUI application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            JFrame initialScreen = null;
            try {
                // Open a database connection
                databaseConnectionHandler.openConnection();
                Connection connection = databaseConnectionHandler.getConnection();

                // Create and initialize the LoanTableDisplay view using the database connection
                initialScreen = new LoginScreen(connection);
                initialScreen.setVisible(true);

            } catch (Throwable t) {
                // Close connection if database crashes.
                databaseConnectionHandler.closeConnection();
                throw new RuntimeException(t);
            }
        });

    }
}