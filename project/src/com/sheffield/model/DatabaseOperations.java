package com.sheffield.model;

import com.sheffield.util.HashedPasswordGenerator;

import javax.swing.table.DefaultTableModel;

import java.math.BigDecimal;
import java.sql.*;
import java.text.Bidi;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {

    // ======GENERAL DATABASE OPERATION=====

    // Get all products from the database
    public ResultSet getAllTableData(Connection connection, String tableName) throws SQLException {
        try {
            if (tableName == "Users") {
                String selectSQL = "SELECT email, forename, surname, accountLocked FROM " + tableName;
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            } else {
                String selectSQL = "SELECT * FROM " + tableName;
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                // System.out.println("<=================== GET ALL PRODUCTS
                // ====================>");
                // while (resultSet.next()) {
                // // Print each product's information in the specified format
                // System.out.println("{" +
                // "productCode='" + resultSet.getString("productCode") + "'" +
                // ", name='" + resultSet.getString("name") + "'" +
                // ", brandName='" + resultSet.getString("brandName") + "'" +
                // ", quantity='" + resultSet.getInt("quantity") + "'" +
                // ", price='" + resultSet.getDouble("price") + "'" +
                // ", gaugeScale='" + resultSet.getString("gaugeScale") + "'" +
                // "}");
                // }
                // System.out.println("<======================================================>");
                return resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }

    // Get all subproducts from the database
    public ResultSet getAllAggregatedTable(Connection connection, String originTable, String extendedTable,
            String extendedColumns) throws SQLException {
        ResultSet resultSet = null;
        try {
            if (originTable == "Users") {
                String selectSQL = "SELECT u.email, u.forename, u.surname, u.accountLocked " + extendedColumns
                        + " FROM " + originTable + " u JOIN " + extendedTable
                        + " a ON u.userID = a.userID";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                resultSet = preparedStatement.executeQuery();

            } else if (originTable == "Products") {
                String selectSQL = "SELECT u.* " + extendedColumns + " FROM " + originTable + " u JOIN " + extendedTable
                        + " a ON u.productCode = a.productCode";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                resultSet = preparedStatement.executeQuery();
<<<<<<< HEAD
                // System.out.println("<=================== GET ALL PRODUCTS
                // ====================>");
                // while (resultSet.next()) {
                // // Print each product's information in the specified format
                // System.out.println("{" +
                // "productCode='" + resultSet.getString("productCode") + "'" +
                // ", name='" + resultSet.getString("name") + "'" +
                // ", brandName='" + resultSet.getString("brandName") + "'" +
                // ", quantity='" + resultSet.getInt("quantity") + "'" +
                // ", price='" + resultSet.getDouble("price") + "'" +
                // ", gaugeScale='" + resultSet.getString("gaugeScale") + "'" +
                // "}");
                // }
                // System.out.println("<======================================================>");
            }
=======
                }
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }

        return resultSet;
    }

    // =====USER OPERATIONS=====

    /**
     * Promotes the selected user to the role of Staff.
     *
     * @param connection The database connection.
     * @param forename   The forename of the user to be promoted.
     */
    public void promoteToStaff(Connection connection, String forename) {
        PreparedStatement preparedStatement = null;

        try {
            // Get the userID based on the forename
            String userID = getuserIDByForename(connection, forename);

            // Prepare the SQL statement to update the user's role to "Staff"
            String sql = "UPDATE Roles SET role = 'STAFF' WHERE userID = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, userID);

            // Execute the update
            preparedStatement.executeUpdate();

            // Additional actions may be performed here if needed after the user is promoted
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        } finally {
            // Close the prepared statement in a finally block to ensure it's closed even if
            // an exception occurs
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your application's needs
            }
        }
    }

    /**
     * Demotes the selected staff to the role of user.
     *
     * @param connection The database connection.
     * @param forename   The forename of the user to be promoted.
     */
    public void demoteStaff(Connection connection, String forename) {
        PreparedStatement preparedStatement = null;

        try {
            // Get the userID based on the forename
            String userID = getuserIDByForename(connection, forename);

            // Prepare the SQL statement to update the staff's role to "User"
            String sql = "UPDATE Roles SET role = 'USER' WHERE userID = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, userID);

            // Execute the update
            preparedStatement.executeUpdate();

            // Additional actions may be performed here if needed after the user is demoted
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        } finally {
            // Close the prepared statement in a finally block to ensure it's closed even if
            // an exception occurs
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your application's needs
            }
        }
    }

    /**
     * Gets the userID based on the forename from the 'Users' table.
     *
     * @param connection The database connection.
     * @param forename   The forename for which to retrieve the userID.
     * @return The userID corresponding to the given forename.
     */
    public String getuserIDByForename(Connection connection, String forename) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Prepare the SQL statement to select the userID based on the forename
            String sql = "SELECT userID FROM Users WHERE forename = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter for the prepared statement
            preparedStatement.setString(1, forename);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check if a result is found
            if (resultSet.next()) {
                return resultSet.getString("userID");
            } else {
                System.out.println("User with forename " + forename + " not found.");
                return null; // Or throw an exception or handle the case as appropriate for your application
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null;
        } finally {
            // Close the resources in a finally block to ensure they're closed even if an
            // exception occurs
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your application's needs
            }
        }
    }

    /**
     * Gets the userId based on the email from the 'Users' table.
     *
     * @param connection The database connection.
     * @param email      The email for which to retrieve the userId.
     * @return The userId corresponding to the given email.
     */
    public String getUserIdByEmail(Connection connection, String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Prepare the SQL statement to select the userId based on the forename
            String sql = "SELECT userId FROM Users WHERE forename = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameter for the prepared statement
            preparedStatement.setString(1, email);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check if a result is found
            if (resultSet.next()) {
                return resultSet.getString("userId");
            } else {
                System.out.println("User with forename " + email + " not found.");
                return null; // Or throw an exception or handle the case as appropriate for your application
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
            return null;
        } finally {
            // Close the resources in a finally block to ensure they're closed even if an
            // exception occurs
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your application's needs
            }
        }
    }

    /**
     * Gets the user roles based on the userId.
     *
     * @param connection The database connection.
     * @param userId     The userId for which to retrieve roles.
     * @return The user's roles.
     */
    private Role getRolesForUserId(Connection connection, String userId) {
        Role role;
        try {
            String sql = "SELECT role FROM Roles WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            role = Role.valueOf(resultSet.getString("role"));
            return role;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves a result set containing all forenames from the 'Users' table.
     *
     * @param connection The database connection.
     * @return A result set containing all forenames.
     */
    public ResultSet getAllUsers(Connection connection) {
        ResultSet resultSet = null;
        PreparedStatement statement = null;

        try {
            // Execute the query to select all forenames from the 'Users' table
            String query = "SELECT u.userId, u.forename, r.role FROM Users u, Roles r WHERE u.userId=r.userId";

            // Create a statement
            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }
        return null;
    }

    public boolean verifyLogin(Connection connection, String email, char[] enteredPassword) {

        try {
            // Fetch user information from database
            String sql = "SELECT userID, password, accountLocked " + "FROM Users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userID = resultSet.getString("userID");
                String storedPasswordHash = resultSet.getString("password");
                boolean accountLocked = resultSet.getBoolean("accountLocked");

                // Check if account is locked
                if (accountLocked) {
                    System.out.println("Account is locked. Please contact support");
                    return false;
                } else {
                    // Verify entered password against stored hash
                    if (verifyPassword(enteredPassword, storedPasswordHash)) {
                        User user = getUserModel(userID, connection);
                        CurrentUserManager.setCurrentUser(user);
                        System.out.println("Login successful for user: " + user);
                        return true;
                    } else {
                        // Incorrect password
                        System.out.println("Incorrect password");
                        return false;
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User not found.");
        return false;

    }

    /**
     * Verifies a password against stored hash
     *
     * @param enteredPassword    The entered password.
     * @param storedPasswordHash The stored password hash.
     * @return True if the password is verified, false otherwise
     */
    private static boolean verifyPassword(char[] enteredPassword, String storedPasswordHash) {
        try {
            String hashedEnteredPassword = HashedPasswordGenerator.hashPassword(enteredPassword);
            return hashedEnteredPassword.equals(storedPasswordHash);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // =========== USER OPERATIONS ===========
    public void registerNewUser(Connection connection, User newUser) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Users (userID, forename, surname, email, password, postcode, houseNumber)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, newUser.getUserID());
            preparedStatement.setString(2, newUser.getForename());
            preparedStatement.setString(3, newUser.getSurname());
            preparedStatement.setString(4, newUser.getEmail());
            preparedStatement.setString(5, newUser.getPassword());
            preparedStatement.setString(6, newUser.getPostcode());
            preparedStatement.setString(7, newUser.getHouseNumber());

            int rowAffected = preparedStatement.executeUpdate();

<<<<<<< HEAD
            // By default, registered account are set to 'User' role
            String insertSQL2 = "INSERT INTO Roles (userID,Role) VALUES (?,'User')";
            PreparedStatement preparedStatement2 = connection.prepareStatement(insertSQL2);
            preparedStatement2.setString(1, newUser.getUserID());
            preparedStatement2.executeUpdate();
=======
            //By default, registered account are set to 'User' role
            insertSQL = "INSERT INTO Roles (userID, Role) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, newUser.getUserID());
            preparedStatement.setString(2, newUser.getRole().name());
            preparedStatement.executeUpdate();
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55

            System.out.println(rowAffected + "row(s) inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception to signal an error.
        }
    }

    public boolean verifyEmailIsUsed(Connection connection, String email) throws SQLException {
        try {
            String query = "SELECT COUNT(*) AS count FROM Users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("count");

            if (count > 0) {
                return true;
            } else
                return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    // ========= ADDRESS OPERATIONS =========
    public void addNewAddress(Connection connection, Address newAddress) throws SQLException {
        try {
            String insertSQL = "INSERT INTO Addresses (postcode, houseNumber, roadName, cityName)" +
                    " VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, newAddress.getPostcode());
            preparedStatement.setString(2, newAddress.getHouseNumber());
            preparedStatement.setString(3, newAddress.getroadName());
            preparedStatement.setString(4, newAddress.getCityName());

            int rowAffected = preparedStatement.executeUpdate();
            System.out.println(rowAffected + " row(s) inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception to signal an error.
        }
    }

    public boolean verifyAddressIsUsed(Connection connection, String houseNumber, String postcode) throws SQLException {
        try {
            String query = "SELECT COUNT(*) AS count FROM Addresses WHERE houseNumber = ? AND postcode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, houseNumber);
            preparedStatement.setString(2, postcode);


            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt("count");

            if (count > 0) {
                return true;
            } else
                return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public int countUser(Connection connection) throws SQLException {
        try {
            String query = "SELECT COUNT(*) AS rowCount FROM Users";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int rowCount = resultSet.getInt("rowCount");
            System.out.println("Number of rows in the table: " + rowCount);

            return rowCount;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public boolean changeEmail(Connection connection, User user, String newEmail) throws SQLException {
        try {
            String sql = "UPDATE Users SET email = ? WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newEmail);
            String currentUserID = CurrentUserManager.getCurrentUser().getUserID();
            statement.setString(2, currentUserID);

            int rowsAffected = statement.executeUpdate();
            user.setEmail(newEmail);
            if (rowsAffected >= 1) {
                return true;
            } else {
                System.out.println(currentUserID);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public User getUserModel(String userID, Connection connection) throws SQLException {
        try {
            String selectSQL = "SELECT u.*, a.role FROM Users u JOIN Roles a ON u.userID = a.userID WHERE u.userID =?";
            ;
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new User(
<<<<<<< HEAD
                    userID,
                    resultSet.getString("forename"),
                    resultSet.getString("surname"),
                    resultSet.getString("email"),
                    resultSet.getString("role"));
=======
                userID, 
                resultSet.getString("forename"), 
                resultSet.getString("surname"), 
                resultSet.getString("email"), 
                Role.valueOf(resultSet.getString("role").toUpperCase())
            );
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // =========== PRODUCTS TABLE OPERATIONS ===========

    // get product model
    public Product getProductModel(String productCode, Connection connection) throws SQLException, ParseException {
        try {
            String selectSQL = "SELECT * FROM Products WHERE productCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new Product(
                    productCode,
                    resultSet.getString("name"),
                    resultSet.getString("brandName"),
                    Integer.parseInt(resultSet.getString("quantity")),
                    new BigDecimal(Double.parseDouble(resultSet.getString("price"))),
                    resultSet.getString("gaugeScale"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Insert a new product into the database
    public void insertProduct(Product newProduct, Connection connection) throws SQLException {
        try {
            // Create an SQL INSERT statement
            String insertSQL = "INSERT INTO Products (productCode, name, brandName," +
                    "price, quantity, gaugeScale) VALUES (?, ?, ?, ?, ?, ?)";

            // Prepare and execute the INSERT statement
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, newProduct.getProductCode());
            preparedStatement.setString(2, newProduct.getName());
            preparedStatement.setString(3, newProduct.getBrandName());
            preparedStatement.setBigDecimal(4, newProduct.getPrice());
            preparedStatement.setInt(5, newProduct.getQuantity());
            preparedStatement.setString(6, newProduct.getGaugeScale().name());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception to signal an error.
        }
    }

    // insert the extened values to the foreign product table
    public void insertForeignKey(int selectedIndex, Product newProduct, Connection connection) throws SQLException {
        try {
            // Create an SQL INSERT statement
            String insertSQL = "";
            PreparedStatement preparedStatement;
            int rowsAffected = 0;
            switch (selectedIndex) {
                case 0:
                    TrainSet newTrainSet = (TrainSet) newProduct;
                    insertSQL = "INSERT INTO TrainSets (productCode, era) VALUES (?, ?)";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(insertSQL);
                    preparedStatement.setString(1, newTrainSet.getProductCode());
                    preparedStatement.setString(2, newTrainSet.getEra());

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted successfully.");
                    break;
                case 1:
                    TrackPack newTrackPack = (TrackPack) newProduct;
                    insertSQL = "INSERT INTO TrackPacks (productCode) VALUES (?)";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(insertSQL);
                    preparedStatement.setString(1, newTrackPack.getProductCode());

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted successfully.");
                    break;
                case 2:
                    Track newTrack = (Track) newProduct;
                    insertSQL = "INSERT INTO Tracks (productCode) VALUES (?)";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(insertSQL);
                    preparedStatement.setString(1, newTrack.getProductCode());

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted successfully.");
                    break;
                case 3:
                    Locomotive newLocomotive = (Locomotive) newProduct;
                    insertSQL = "INSERT INTO Locomotives (productCode, era, dcc) VALUES (?, ?, ?)";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(insertSQL);
                    preparedStatement.setString(1, newLocomotive.getProductCode());
                    preparedStatement.setString(2, newLocomotive.getEra());
<<<<<<< HEAD
                    preparedStatement.setString(3, newLocomotive.getLocomotiveType());
=======
                    preparedStatement.setString(3, newLocomotive.getLocomotiveType().name());
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted successfully.");

                    break;
                case 4:
                    RollingStock newRollingStock = (RollingStock) newProduct;
                    insertSQL = "INSERT INTO RollingStocks (productCode, era, rollingStockType) VALUES (?, ?, ?)";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(insertSQL);
                    preparedStatement.setString(1, newRollingStock.getProductCode());
                    preparedStatement.setString(2, newRollingStock.getEra());
                    preparedStatement.setString(3, newRollingStock.getRollingStockType().name());

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted successfully.");
                    break;
                case 5:
                    Controller newController = (Controller) newProduct;
                    insertSQL = "INSERT INTO Controllers (productCode, dcc) VALUES (?, ?)";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(insertSQL);
                    preparedStatement.setString(1, newController.getProductCode());
                    preparedStatement.setString(2, newController.getControllerType().name());

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) inserted successfully.");
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception to signal an error.
        }
    }

    // count the number of product in the table
    public int countProduct(String tableName, Connection connection) throws SQLException {
        try {
            String query = "SELECT COUNT(*) AS rowCount FROM " + tableName;
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int rowCount = resultSet.getInt("rowCount");

            return rowCount;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    // Get all products from the database
    public ResultSet getAllProducts(Connection connection, String tableName) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM " + tableName;
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            // System.out.println("<=================== GET ALL PRODUCTS
            // ====================>");
            // while (resultSet.next()) {
            // // Print each product's information in the specified format
            // System.out.println("{" +
            // "productCode='" + resultSet.getString("productCode") + "'" +
            // ", name='" + resultSet.getString("name") + "'" +
            // ", brandName='" + resultSet.getString("brandName") + "'" +
            // ", quantity='" + resultSet.getInt("quantity") + "'" +
            // ", price='" + resultSet.getDouble("price") + "'" +
            // ", gaugeScale='" + resultSet.getString("gaugeScale") + "'" +
            // "}");
            // }
            // System.out.println("<======================================================>");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }

    // Get all subproducts from the database
    public ResultSet getAllAggregatedProducts(Connection connection, String originTable, String foreignTable,
            String aggregatedColumns) throws SQLException {
        ResultSet resultSet = null;
        try {
            String selectSQL = "SELECT u.* " + aggregatedColumns + " FROM " + originTable + " u JOIN " + foreignTable
                    + " a ON u.productCode = a.productCode";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            resultSet = preparedStatement.executeQuery();
            // System.out.println("<=================== GET ALL PRODUCTS
            // ====================>");
            // while (resultSet.next()) {
            // // Print each product's information in the specified format
            // System.out.println("{" +
            // "productCode='" + resultSet.getString("productCode") + "'" +
            // ", name='" + resultSet.getString("name") + "'" +
            // ", brandName='" + resultSet.getString("brandName") + "'" +
            // ", quantity='" + resultSet.getInt("quantity") + "'" +
            // ", price='" + resultSet.getDouble("price") + "'" +
            // ", gaugeScale='" + resultSet.getString("gaugeScale") + "'" +
            // "}");
            // }
            // System.out.println("<======================================================>");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }

        return resultSet;
    }

    // Get a product by it's productCode
    public ResultSet getProductByCode(String productCode, Connection connection) throws SQLException {
        try {
            String selectSQL = "SELECT * FROM Products WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            // System.out.println("<==================== PRODUCT BY CODE
            // =====================>");
            // if (resultSet.next()) {
            // System.out.println("{" +
            // "productCode='" + resultSet.getString("productCode") + "'" +
            // ", name='" + resultSet.getString("name") + "'" +
            // ", brandName='" + resultSet.getString("brand_name") + "'" +
            // ", quantity='" + resultSet.getInt("quantity") + "'" +
            // ", price='" + resultSet.getDouble("price") + "'" +
            // "}");
            // } else {
            // System.out.println("Product with productCode " + productCode + " not
            // found.");
            // }
            // System.out.println("<=======================================================>");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }

    public ResultSet getSelectedAggregatedProducts(String productCode, Connection connection, String originTable,
            String foreignTable, String aggregatedColumns) throws SQLException {
        try {
            String selectSQL = "SELECT u.* " + aggregatedColumns + " FROM " + originTable + " u JOIN " + foreignTable
                    + " a ON u.productCode = a.productCode WHERE u.productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, productCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            // System.out.println("<=================== GET ALL PRODUCTS
            // ====================>");
            // while (resultSet.next()) {
            // // Print each product's information in the specified format
            // System.out.println("{" +
            // "productCode='" + resultSet.getString("productCode") + "'" +
            // ", name='" + resultSet.getString("name") + "'" +
            // ", brandName='" + resultSet.getString("brandName") + "'" +
            // ", quantity='" + resultSet.getInt("quantity") + "'" +
            // ", price='" + resultSet.getDouble("price") + "'" +
            // ", gaugeScale='" + resultSet.getString("gaugeScale") + "'" +
            // "}");
            // }
            // System.out.println("<======================================================>");
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }

    // Update an existing product in the database
    public void updateProduct(Product newProduct, Connection connection) throws SQLException {
        try {
            String updateSQL = "UPDATE Products SET name=?, brandName=?," +
                    "quantity=?, price=?, gaugeScale=? WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, newProduct.getName());
            preparedStatement.setString(2, newProduct.getBrandName());
            preparedStatement.setInt(3, newProduct.getQuantity());
            preparedStatement.setBigDecimal(4, newProduct.getPrice());
            preparedStatement.setString(5, newProduct.getGaugeScale().name());
            preparedStatement.setString(6, newProduct.getProductCode());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) updated successfully.");
            } else {
                System.out.println("No rows were updated for productCode: " + newProduct.getProductCode());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }

    // insert the extened values to the foreign product table
    public void updateForeignKey(int selectedIndex, Product newProduct, Connection connection) throws SQLException {
        try {
            // Create an SQL INSERT statement
            String updateSQL = "";
            PreparedStatement preparedStatement;
            int rowsAffected = 0;
            switch (selectedIndex) {
                case 0:
                    TrainSet newTrainSet = (TrainSet) newProduct;
                    updateSQL = "UPDATE TrainSets SET era=? WHERE productCode=?";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(updateSQL);
                    preparedStatement.setString(1, newTrainSet.getEra());
                    preparedStatement.setString(2, newTrainSet.getProductCode());

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) updated successfully.");
                    break;
                case 3:
                    Locomotive newLocomotive = (Locomotive) newProduct;
                    updateSQL = "UPDATE Locomotives SET era=?, dcc=? WHERE productCode=?";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(updateSQL);
                    preparedStatement.setString(1, newLocomotive.getEra());
<<<<<<< HEAD
                    preparedStatement.setString(2, newLocomotive.getLocomotiveType());
=======
                    preparedStatement.setString(2, newLocomotive.getLocomotiveType().name());
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55
                    preparedStatement.setString(3, newLocomotive.getProductCode());

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) updated successfully.");
                    break;
                case 4:
                    RollingStock newRollingStock = (RollingStock) newProduct;
                    updateSQL = "UPDATE RollingStocks SET era=?,  rollingStockType=? WHERE productCode=?";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(updateSQL);
                    preparedStatement.setString(1, newRollingStock.getEra());
                    preparedStatement.setString(2, newRollingStock.getRollingStockType().name());
                    preparedStatement.setString(3, newRollingStock.getProductCode());

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) updated successfully.");
                    break;
                case 5:
                    Controller newController = (Controller) newProduct;
                    updateSQL = "UPDATE Controllers SET dcc=? WHERE productCode=?";
                    // Prepare and execute the INSERT statement
                    preparedStatement = connection.prepareStatement(updateSQL);
                    preparedStatement.setString(1, newController.getControllerType().name());
                    preparedStatement.setString(2, newController.getProductCode());

                    rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row(s) updated successfully.");
                    break;
                default:
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Re-throw the exception to signal an error.
        }
    }

    // Delete a product from the database by productCode
    public void deleteProduct(String tableName, String productCode, Connection connection) throws SQLException {
        try {
            String deleteSQL = "DELETE FROM " + tableName + " WHERE productCode=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, productCode);
            preparedStatement.executeUpdate();

            deleteSQL = "DELETE FROM Products WHERE productCode=?";
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, productCode);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(rowsAffected + " row(s) deleted successfully.");
            } else {
                System.out.println("No rows were deleted for the product code: " + productCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }

    // Get list columnName from tableName
    public List<Object> getListFromTable(Connection connection, String tableName, String columnName)
            throws SQLException {
        Statement st = connection.createStatement();
        String query = "SELECT " + columnName + " FROM team060." + tableName;

        List<Object> columnList = new ArrayList<>();

        ResultSet rs = st.executeQuery(query); // Execute query

        while (rs.next()) { // Check if result set has data
            String result = rs.getString(columnName);
            columnList.add(result);
        }

        rs.close(); // Close result set
        st.close(); // Close statement

        return columnList;
    }

    // Get record columnName with userID from tableName
    public String getRecordFromTable(Connection connection, String columnName, String tableName, String id)
            throws SQLException {

        String result = "";
        String query = "SELECT " + columnName + " FROM " + tableName + " WHERE userID='" + id + "'";

        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                result = rs.getString(columnName);
            }
        }

        return result;
    }

    // Get UserID as token (from email) when logging in
    public String getUserID(Connection connection, String email) throws SQLException {

        String userID = "";
        String query = "SELECT userID FROM Users WHERE email=" + "'" + email + "'";

        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                userID = rs.getString("userID");
            }
        }

        return userID;
    }

    // Update user details in ProfileScreen (ONLY FOR Users TABLE)
    public void updateUserDetails(Connection connection, String columnName, String value, String id) {
        String query = "UPDATE Users SET " + columnName + " = ? WHERE userID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, value); // Set the new value for the specified column
            statement.setString(2, id); // Set the user ID for the WHERE clause

            int rowsAffected = statement.executeUpdate(); // Execute the update statement
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserPassword(Connection connection, String newPassword, String id) {
        char[] charPassword = newPassword.toCharArray();
        String hashedPassword = HashedPasswordGenerator.hashPassword(charPassword);
        String query = "UPDATE Users SET password= ? WHERE userID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, hashedPassword);
            statement.setString(2, id);

            int rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ========== ORDERLINE TABLE OPERATIONS

    public void insertOrderLine(Connection connection, OrderLine orderLine) {

        String sql = "INSERT INTO OrderLines (orderLineNumber, productCode, orderID, productQuantity, orderLineCost)"
                + " VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, orderLine.getOrderLineNumber());
            preparedStatement.setString(2, orderLine.getProductCode());
            preparedStatement.setString(3, orderLine.getOrderID());
            preparedStatement.setInt(4, orderLine.getQuantity());
            preparedStatement.setBigDecimal(5, orderLine.getOrderLineCost());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteOrderLine(Connection connection, String orderLineNumber) {
        try {
            String sql = "DELETE FROM OrderLines WHERE orderLineNumber = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, orderLineNumber);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // ========== ORDERS TABLE OPERATIONS ===========

<<<<<<< HEAD
    public void insertOrder(Connection connection, Order order) {
=======
    public ResultSet getAllOrdersDataWhere(Connection connection, Status status) throws SQLException {
        try {
            if (status.equals(Status.CONFIRMED) || status.equals(Status.FULFILLED) || status.equals(Status.DECLINED)) {
                String selectSQL = "SELECT * FROM Orders WHERE status = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, status.name());
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            } else {
                String selectSQL = "SELECT * FROM Orders WHERE status = ? OR status = ? OR status = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, Status.CONFIRMED.name());
                preparedStatement.setString(2, Status.FULFILLED.name());
                preparedStatement.setString(3, Status.DECLINED.name());
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }

    // count the number of product in the table
    public int countOrderStatus(Status status, Connection connection) throws SQLException {
        try {
            if (status.equals(Status.CONFIRMED) || status.equals(Status.FULFILLED) || status.equals(Status.DECLINED)) {
                String query = "SELECT COUNT(*) AS rowCount FROM Orders WHERE status = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, status.name());
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int rowCount = resultSet.getInt("rowCount");
                return rowCount;
            } else {
                String query = "SELECT COUNT(*) AS rowCount FROM Orders WHERE status = ? OR status = ? OR status = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, Status.CONFIRMED.name());
                statement.setString(2, Status.FULFILLED.name());
                statement.setString(3, Status.DECLINED.name());
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int rowCount = resultSet.getInt("rowCount");
                return rowCount;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public ResultSet getAllOrdersDataWhereUserID(Connection connection, Status status, String userID) throws SQLException {
        try {
            if (status.equals(Status.CONFIRMED) || status.equals(Status.FULFILLED) || status.equals(Status.DECLINED)) {
                String selectSQL = "SELECT * FROM Orders WHERE status = ? AND userID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, status.name());
                preparedStatement.setString(2, userID);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            } else {
                String selectSQL = "SELECT * FROM Orders WHERE (status = ? AND userID = ?) OR (status = ?  AND userID = ?) OR (status = ? AND userID = ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
                preparedStatement.setString(1, Status.CONFIRMED.name());
                preparedStatement.setString(2, userID);
                preparedStatement.setString(3, Status.FULFILLED.name());
                preparedStatement.setString(4, userID);
                preparedStatement.setString(5, Status.DECLINED.name());
                preparedStatement.setString(6, userID);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }

    // count the number of product in the table
    public int countOrderStatusWhereUserID(Status status, Connection connection, String userID) throws SQLException {
        try {
            if (status.equals(Status.CONFIRMED) || status.equals(Status.FULFILLED) || status.equals(Status.DECLINED)) {
                String query = "SELECT COUNT(*) AS rowCount FROM Orders WHERE status = ? AND userID = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, status.name());
                statement.setString(2, userID);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int rowCount = resultSet.getInt("rowCount");
                return rowCount;
            } else {
                String query = "SELECT COUNT(*) AS rowCount FROM Orders WHERE (status = ? AND userID = ?) OR (status = ?  AND userID = ?) OR (status = ? AND userID = ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, Status.CONFIRMED.name());
                statement.setString(2, userID);
                statement.setString(3, Status.FULFILLED.name());
                statement.setString(4, userID);
                statement.setString(5, Status.DECLINED.name());
                statement.setString(6, userID);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                int rowCount = resultSet.getInt("rowCount");
                return rowCount;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }




    public void insertOrder(Connection connection, Order order){
>>>>>>> 31ad3d7a5a159d732d88ec83d28639d4e0d2ea55

        String sql = "INSERT INTO Orders(orderID, userID, totalCost, status, date) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, order.getOrderID());
            preparedStatement.setString(2, order.getUserID());
            preparedStatement.setBigDecimal(3, order.getTotalCost());
            preparedStatement.setString(4, order.getStatus().name());
            preparedStatement.setDate(5, order.getDate());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // count the number of product in the table
    public int countUserOrder(String userID, Connection connection) throws SQLException {
        try {
            String query = "SELECT COUNT(*) AS rowCount FROM Orders WHERE userID =?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int rowCount = resultSet.getInt("rowCount");

            return rowCount;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public Order getOrderModel(String orderID, Connection connection) throws SQLException, ParseException {
        try {
            String selectSQL = "SELECT * FROM Orders WHERE orderID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, orderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate;
            utilDate = dateFormat.parse(resultSet.getString("date"));

            return new Order(
                    orderID,
                    resultSet.getString("userID"),
                    new BigDecimal(Double.parseDouble(resultSet.getString("totalCost"))),
                    Status.valueOf(resultSet.getString("status").toUpperCase()),
                    new Date(utilDate.getTime()));

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Demotes the selected staff to the role of user.
     *
     * @param connection   The database connection.
     * @param forename The forename of the user to be promoted.
     */
    public void updateOrderStatus(Connection connection, String orderID, Status status) {
        PreparedStatement preparedStatement = null;

        try {

            // Prepare the SQL statement to update the staff's role to "User"
            String sql = "UPDATE Orders SET status = ? WHERE orderID = ?";
            preparedStatement = connection.prepareStatement(sql);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, status.name());
            preparedStatement.setString(2, orderID);

            // Execute the update
            preparedStatement.executeUpdate();

            // Additional actions may be performed here if needed after the user is demoted
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        } finally {
            // Close the prepared statement in a finally block to ensure it's closed even if
            // an exception occurs
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception according to your application's needs
            }
        }
    }

    public void updateOrderIDTotalCost(Connection connection, String orderID, BigDecimal totalCost) {
        try {
            String updateSQL = "UPDATE Orders SET totalCost = ? WHERE orderID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setBigDecimal(1, totalCost);
            preparedStatement.setString(2, orderID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public ResultSet getRecent10Orders (Connection connection) throws SQLException {
        try {
            String query = "SELECT * FROM Orders WHERE status='CONFIRMED' ORDER BY date DESC LIMIT 10";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;// Re-throw the exception to signal an error.
        }
    }

    public int countRecent10Orders (Connection connection) throws SQLException {
        try {
            String query = "SELECT *, (SELECT COUNT(*) FROM Orders WHERE status='CONFIRMED') AS total_confirmed_orders FROM Orders WHERE status='CONFIRMED' ORDER BY date DESC LIMIT 10";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Integer.parseInt(resultSet.getString("total_confirmed_orders"));
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;// Re-throw the exception to signal an error.
        }
    }

    // ====== ORDERLINE OPERATION======
    // count the number of product in the table
    public int countOrderIDOrderLine(String orderID, Connection connection) throws SQLException {
        try {
            String query = "SELECT COUNT(*) AS rowCount FROM OrderLines WHERE orderID =?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, orderID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int rowCount = resultSet.getInt("rowCount");

            return rowCount;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int countUserOrderLine(String userIDLast2Char, Connection connection) throws SQLException {
        try {
            String query = "SELECT COUNT(*) AS rowCount FROM OrderLines WHERE orderLineNumber LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userIDLast2Char + "%");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int rowCount = resultSet.getInt("rowCount");

            return rowCount;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    // Get all orderLine from the database based on orderID
    public ResultSet getAllOrderIDOrderLineData(Connection connection, String orderID) throws SQLException {
        try {
            String selectSQL = "SELECT orderLineNumber, productCode, productQuantity, orderLineCost FROM OrderLines WHERE orderID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, orderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }

    public ResultSet getAllOrderIDOrderLineTotalPrice(Connection connection, String orderID) throws SQLException {
        try {
            String selectSQL = "SELECT SUM(orderLineCost) AS total_price FROM OrderLines WHERE orderID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, orderID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString("total_price"));
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;// Re-throw the exception to signal an error.
        }
    }


}