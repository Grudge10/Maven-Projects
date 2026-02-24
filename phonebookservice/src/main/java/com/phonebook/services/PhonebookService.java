package com.phonebook.services;

import java.io.*;
import java.util.*;

import com.phonebook.models.Contact;

public class PhonebookService {
    private Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact c) {
        contacts.put(c.getName(), c);
        System.out.println("Contact has been added!");
    }

    public void searchContact(String name) {
        if (contacts.containsKey(name)) {
            System.out.println(name + " has been found!");
            System.out.println(contacts.get(name));
        } else {
            System.out.println(name + " does not exist!");
        }
    }

    public void removeContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("successfully removed " + name + "!");
        } else {
            System.out.println(name + " does not exist!");
        }
    }

    public void saveToCSV(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Contact> contact : contacts.entrySet()) {
                StringBuilder s = new StringBuilder();

                s.append(contact.getKey()).append("=");
                
                Contact newContact = contact.getValue();
                s.append(newContact.toCsvString());

                writer.write(s.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromCSV(String filename) {
        
    }
}