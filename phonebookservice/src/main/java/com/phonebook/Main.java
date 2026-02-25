package com.phonebook;

import java.util.*;

import com.phonebook.models.Contact;
import com.phonebook.services.PhonebookService;

public class Main {
    public static void main(String[] args) {
        // make a phonebookservice object and load data from csv
        PhonebookService service = new PhonebookService();
        service.loadFromCSV("contacts.csv");

        // loop until user exits
        boolean userHasNotExited = true;
        while (userHasNotExited) {
            // load menu
            System.out.println("1. Add | 2. Search | 3. Remove | 4. Display All | 5. Save to CSV | 0. Exit");

            // validate the input until the user inputs a valid value
            int choice;
            while (true) {
                System.out.println("Choice: ");

                // Use try catch method for when user puts the wrong data type value
                try {
                    // get the user's choice
                    choice = input.nextInt();
                    input.nextLine();

                    // check if input is a possible choice
                    if (choice >= 0 && choice <= 5) {
                        break;
                    } else {
                        System.out.println("[ERROR] Input must be between 0 to 5 (inclusive)");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("[ERROR] Invalid Input!");
                    input.nextLine();
                }
            }

            // do certain actions according to the value of choice
            switch (choice) {
                case 1:
                    // create a contact object and prompt the user to input strings
                    Contact addContact = new Contact();
                    addContact.setName(inputString("Enter Name: "));
                    addContact.setPhoneNumber(inputString("Enter Phone Number: "));
                    addContact.setEmail(inputString("Enter Email: "));

                    // then add the contact object into the hashmap
                    service.addContact(addContact);
                    break;
                case 2:
                    // prompt the user to input a string in order to search it
                    Contact searchedContact = service.searchContact(inputString("Enter Name: "));
                    if (searchedContact != null) {
                        System.out.printf("""
                                Found it!
                                Name:         %s
                                Phone Number: %s
                                Email:        %s
                                """, searchedContact.getName(), searchedContact.getPhoneNumber(),
                                searchedContact.getEmail());
                    } else {
                        System.out.println("Contact does not exist!");
                    }
                    break;
                case 3:
                    // prompt the user to input a string in order to remove it
                    boolean contactRemoved = service.removeContact(inputString("Enter Name: "));

                    if (contactRemoved) {
                        System.out.println("Removed Successfully!");
                    } else {
                        System.out.println("Contact does not exist!");
                    }
                    break;
                case 4:
                    displayAll(service);
                    break;
                case 5:
                    System.out.println("Saving contacts...");
                    service.saveToCSV("contacts.csv");
                    System.out.println("Contacts successfully saved!!!");
                    break;
                case 0:
                    service.saveToCSV("contacts.csv");
                    userHasNotExited = false;
                    break;
            }

        }
    }

    public static Scanner input = new Scanner(System.in);

    public static String inputString(String message) {
        String s;

        // validate the input until the user inputs a valid value
        while (true) {
            System.out.println(message);

            // get the user's choice
            s = input.nextLine().trim();

            // check if string is not empty
            if (!s.isEmpty()) {
                break;
            } else {
                System.out.println("[ERROR] String cannot be empty!");
            }
        }

        return s;
    }

    public static void displayAll(PhonebookService service) {
        System.out.println("+-----------------------+-----------------------+-----------------------+");
        System.out.println("| Name                  | Phone Number          | Email                 |");

        for (Contact contact : service.getAllContacts()) {
            System.out.println("+-----------------------+-----------------------+-----------------------+");
            System.out.printf("| %-22s| %-22s| %-22s|\n", contact.getName(), contact.getPhoneNumber(),
                    contact.getEmail());
        }

        System.out.println("+-----------------------+-----------------------+-----------------------+");
    }
}