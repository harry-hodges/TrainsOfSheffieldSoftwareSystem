package com.sheffield.model;

/**
 * The CurrentUserManager class manages the current user of the application.
 * It follows the singleton pattern, ensuring that there is only one instance
 * of the current user throughout the application.
 */
public class CurrentUserManager {
    // Static field to hold the current user
    private static User currentUser;

    // Private constructor to enforce singleton pattern
    private CurrentUserManager() {
        // No instances of this class can be created externally
    }

    /**
     * Gets the current user of the application.
     *
     * @return The current user.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user of the application.
     *
     * @param user The user to set as the current user.
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
}
