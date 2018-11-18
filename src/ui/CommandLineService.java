package ui;

import services.ContactService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandLineService {
    private ContactService contactService = new ContactService();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void run() throws IOException {
        String input;

        do {
            showMenu();
            input = br.readLine();
            switch (input) {
                case "1":
                    contactService.createContact();
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

            }
            System.out.println(input);
        }
        while (!input.equals("0"));
    }

    public void showMenu() {
        System.out.println("Menu");
        System.out.println("1. Create contact");
        System.out.println("2. Modify Contact");
        System.out.println("3. Delete contact");
        System.out.println("4. Show contacts");
        System.out.println("0. Exit");
    }


}
