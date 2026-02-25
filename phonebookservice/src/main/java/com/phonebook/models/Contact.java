package com.phonebook.models;

public class Contact {
    private String name, phoneNumber, email;

    // empty constructor
    public Contact() {
    }

    // constructor with all fields as paremeters
    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // getter methods
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // method that returns a String formatted for a CSV
    public String toCsvString() {
        return name + "," + phoneNumber + "," + email;
    }
}