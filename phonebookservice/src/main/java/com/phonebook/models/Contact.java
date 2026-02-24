package com.phonebook.models;

public class Contact {
    private String name, phoneNumber, email;

    // getter methods
    public String getName() {
        return name;
    }

    public String toCsvString() {
        return name + "," + phoneNumber + "," + email;
    }
}