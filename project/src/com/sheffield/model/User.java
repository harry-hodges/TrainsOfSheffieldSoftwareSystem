package com.sheffield.model;

import java.util.List;

import com.sheffield.util.HashedPasswordGenerator;
import com.sheffield.util.UniqueUserIDGenerator;

public class User {
    private String userID;
    private String forename;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private String postcode;
    private String houseNumber;

    /**
     * Constructor for Registration.
     *
     * @param userId   The unique identifier for the user.
     * @param email    The email address of the user.
     * @param roles     The role assigned to the user.
     */

    public User(String forename, String surname, String email, char[] password, Role role, String postcode, String houseNumber) {
        this.setUserID();
        this.setForename(forename);
        this.setSurname(surname);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
        this.setPostcode(postcode);
        this.setHouseNumber(houseNumber);
    }

    public User(String forename, String surname, String email, char[] password) {
        this.setForename(forename);
        this.setSurname(surname);
        this.setEmail(email);
        this.setPassword(password);
        this.setUserID();
    }

    public User(String userID, String forename, String surname, String email, Role role) {
        this.setForename(forename);
        this.setSurname(surname);
        this.setEmail(email);
        this.userID = userID;
        this.setRole(role);
    }

    public User(String email, String password, Role role){
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //Getter and setter methods for userID 
    public String getUserID() {
        return userID;
    }

    public void setUserID(){
        this.userID = UniqueUserIDGenerator.generateUniqueUserID();
    }

    // Getter and setter methods for forename
    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    // Getter and setter methods for surname
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    //Getter and setter methods for email 
    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    // Getter and setter methods for password
    public String getPassword() {
        return password;
    }
    
    public void setPassword(char[] password) {
        this.password = HashedPasswordGenerator.hashPassword(password);
    }

    /**
     * Gets the list of roles associated with the user.
     *
     * @return The list of roles.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the list of roles associated with the user.
     *
     * @param roles The list of roles to be set.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }


    @Override
    public String toString() {
        return "{ " + " userID = " + getUserID() + 
            ", email = " + getEmail() + ", role= "
            + getRole() + "}";
    }
}
