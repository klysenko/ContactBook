package services;

import dao.SaveContactDao;
import model.Contact;

import java.io.IOException;

public class ContactService {
    private ReadService readService = new ReadService();
    private SaveContactDao dao = new SaveContactDao();

    public void createContact() throws IOException {
        System.out.println("Create contact");
        String firstName = readService.readField("First Name");
        String lastName = readService.readField("Last Name");
        Contact contact = new Contact(firstName, lastName);
        dao.saveContact(contact);
    }

    public void modifyContact() {
        System.out.println("Modify contact");
    }

    public void deleteContact() {
        System.out.println("Delete contact");
    }

    public void showContact() {
        System.out.println("Show contact");
    }
}
