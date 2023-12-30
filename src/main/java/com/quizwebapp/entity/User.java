package com.quizwebapp.entity;

import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean isActive;
    private boolean isAdmin;
    private int id;
    private static final AES256TextEncryptor aes256TextEncryptor = new AES256TextEncryptor();
    static {
        aes256TextEncryptor.setPassword("123mySe3cre8tEncr2yption6Key");
    }
    public User() {}

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = encryptedPassword(password);
        this.email = email;
        this.isActive = true;
        this.isAdmin = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public static String encryptedPassword(String password) {
        return aes256TextEncryptor.encrypt(password);
    }

    public static boolean checkPassword(String userPassword, String encryptedPassword) {
        return userPassword.equals(aes256TextEncryptor.decrypt(encryptedPassword));
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User" + this.hashCode() + " ->" + "{"
                + " name: " + this.getName()
                + ", email: " + this.email
                + ", password: " + this.password + " }";
    }

}
