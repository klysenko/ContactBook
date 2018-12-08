package ui;

import Validation.AgeValidator;
import exceptions.InvalidAgeException;
import model.Contact;
import services.ContactService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandLineService {


    private ContactService contactService;
    private BufferedReader br;
   // private ContactService contactService = new ContactService();
   // private BufferedReader br = new BufferedReader(new InputStreamReader(System.in);
    private AgeValidator ageValidator = new AgeValidator();

    public CommandLineService (ContactService contactService, BufferedReader br) {
       this.contactService = contactService;
       this.br = br;
   }

    void run() throws IOException {
        String input;

        do {
            showMenu();
            input = br.readLine();
            switch (input) {
                case "0":
                    break;
                case "1":
                    showCreateMenu();
                    break;
                case "2":
                    modifyContact();
                    break;
                case "3":
                    deleteContact();
                    break;
                case "4":
                    contactService.showAllContacts();
                    break;
                default:
                    System.out.println("Incorrect value. Please enter value 0-4");
                    break;
            }
            System.out.println(input);
        }
        while (!input.equals("0"));
    }

    private void showMenu() {
        System.out.println("Menu");
        System.out.println("1. Create contact");
        System.out.println("2. Modify Contact");
        System.out.println("3. Delete contact");
        System.out.println("4. Show contacts");
        System.out.println("0. Exit");
    }

    String readLine(String message) throws IOException {
        String field;
        do {
            System.out.print(message);
            field = br.readLine();
        } while (field.trim().isEmpty());
        return field.trim();
    }

    private void showCreateMenu() throws IOException {

        String firstName = readLine("First Name: ");
        String lastName = readLine("Last Name: ");

        int age = readAge();
        contactService.createContact(firstName, lastName, age);
    }

    private int readAge() {
        try {
            int age = new Integer(readLine("Input age: "));
            ageValidator.validate(age);
            return age;
        } catch (NumberFormatException e) {
            System.out.println("Wrong format!!!");
            return readAge();
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
            return readAge();
        } catch (IOException e) {
            System.out.println("Failed to read file!");
            return readAge();
        }
    }

    private void deleteContact() throws IOException {
        String name = readLine("Name: ");
        contactService.deleteByName(name);
    }

    private void modifyContact() throws IOException {
        String name = readLine("Name: ");
        Contact[] contacts = contactService.getContactsByName(name);
        if (areContactsEmpty(contacts)) {
            System.out.println("No contacts found");
            return;
        }
        for (Contact contact : contacts) {
            if (contact != null) {
                System.out.println("Found contact:");
                System.out.println(contact);
                System.out.println();
            }
        }
        String newFirstName = readLine("New First Name: ");
        String newLastName = readLine("New Last Name: ");
        contactService.modifyContact(name, newFirstName, newLastName);
    }

    private boolean areContactsEmpty(Contact[] contacts) {
        int i = 0;
        for (Contact contact : contacts) {
            if (contact == null) {
                i++;
            }
        }
        return contacts.length == i;
    }
}
