package services;

import dao.ContactDao;
import model.Contact;

public class ContactService {
    private ContactDao dao = new ContactDao();

    public void createContact(String firstName, String lastName, int age) {
        dao.saveContact(new Contact(firstName, lastName, age));
    }

    public void modifyContact(String name, String newFirstName, String newLastName) {
        System.out.println("Modify contact");
        dao.modifyByName(name, newFirstName, newLastName);
    }

    public void deleteByName(String name) {
        dao.deleteByName(name);
    }

    public Contact[] getContactsByName(String name) {
        Contact[] allContacts = dao.getAll();

        for (int i = 0; i < allContacts.length; i++) {
            Contact contact = allContacts[i];
            if (contact != null
                    && !contact.getFirstName().contains(name)
                    && !contact.getLastName().contains(name)) {
                allContacts[i] = null;
            }
        }
        return allContacts;
    }

    public void showAllContacts() {
        Contact[] allContacts = dao.getAll();
        if (allContacts.length == 0) {
            System.out.println("There are no contacts");
            return;
        }
        for (int i = 0; i < allContacts.length; i++) {
            Contact currentContact = allContacts[i];
            if (currentContact != null) {
                System.out.println(allContacts[i]);
            }
        }
    }
}
