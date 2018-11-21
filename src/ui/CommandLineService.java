package ui;

import services.ContactService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandLineService {
    private ContactService contactService = new ContactService();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    void run() throws IOException {
        String input;

        do {
            showMenu();
            input = br.readLine();
            switch (input) {
                case "1":
                    String firstName = readField("First Name");
                    String lastName = readField("Last Name");
                    contactService.createContact(firstName, lastName);
                    break;
                case "2":
                    contactService.modifyContact();
                    break;
                case "3":
                    contactService.deleteContact();
                    break;
                case "4":
                    contactService.showContact();
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

    String readField(String fieldName) throws IOException {
        String field;
        do {
            System.out.print(fieldName);
            field = br.readLine();
        } while (field.trim().isEmpty());
        return field.trim();
    }
}
