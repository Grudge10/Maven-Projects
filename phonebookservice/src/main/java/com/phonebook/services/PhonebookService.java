package com.phonebook.services;

import java.io.*;
import java.util.*;

import com.phonebook.models.Contact;

public class PhonebookService {
    private Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact c) {
        contacts.put(c.getName(), c);
    }

    public boolean searchContact(String name) {
        if (contacts.containsKey(name)) {
            System.out.println(contacts.get(name));
            return true;
        } else {
            return false;
        }
    }

    public boolean removeContact(String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            return true;
        } else {
            return false;
        }
    }

    public void saveToCSV(String filename) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Contact> contact : contacts.entrySet()) {
                StringBuilder s = new StringBuilder();

                Contact newContact = contact.getValue();

                s.append(newContact.toCsvString()).append("\n");

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