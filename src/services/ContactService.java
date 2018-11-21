package services;

import dao.SaveContactDao;
import model.Contact;

public class ContactService {
    private SaveContactDao dao = new SaveContactDao();

    public void createContact(String firstName, String lastName) {
        System.out.println("Create contact");
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
