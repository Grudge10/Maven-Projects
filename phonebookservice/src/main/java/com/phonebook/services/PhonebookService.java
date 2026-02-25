package com.phonebook.services;

import java.io.*;
import java.util.*;

import com.phonebook.models.Contact;

public class PhonebookService {
    private Map<String, Contact> contacts = new HashMap<>();

    // method that returns an arraylist of all contact objects
    public ArrayList<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }

    // method that adds the contact object into the map
    public void addContact(Contact c) {
        contacts.put(c.getName().toLowerCase(), c);
    }

    // method that searches for the key and returns the searched contact object
    public Contact searchContact(String name) {
        // check if key exists. if yes, return contact value
        if (contacts.containsKey(name)) {
            return contacts.get(name);
        } else {
            return null;
        }
    }

    // method that searches for the key then removes it, and returns boolean value
    public boolean removeContact(String name) {
        // check if the key exists
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            return true;
        } else {
            return false;
        }
    }

    // method that saves the hashmap into a csv
    public void saveToCSV(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // loop through all objects
            for (Map.Entry<String, Contact> contact : contacts.entrySet()) {
                // get the value of the object from the hashmap and put it into a new instance
                Contact saveContact = contact.getValue();

                // save it into the csv and make a new line for the next one
                writer.write(saveContact.toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method that loads the data from the csv into the hashmap
    public void loadFromCSV(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            // loop through all lines until theres nothing to read
            while ((line = reader.readLine()) != null) {
                // split the line
                String[] data = line.split(",");

                // check if the data length is the right length. skip if not
                if (data.length == 3) {
                    // create a contact object and add in the values
                    Contact loadContact = new Contact(data[0], data[1], data[2]);
                    // add the contact into the hashmap
                    contacts.put(data[0], loadContact);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing phonebook found. Starting fresh!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}