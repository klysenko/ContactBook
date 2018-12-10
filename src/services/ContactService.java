package services;

import dao.ContactDao;
import model.Contact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContactService {
    private ContactDao dao;

    public ContactService(ContactDao dao) {
        this.dao = dao;
    }

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

    public Collection<Contact> getContactsByName(String name) {
        Collection<Contact> allContacts = dao.getAll();
        List<Contact> contactsWithName = new ArrayList<>();

        for (Contact contact : allContacts) {
            if (contact == null) {
                continue;
            }
            if (contact.getFirstName().contains(name)
                    || contact.getLastName().contains(name)) {
                contactsWithName.add(contact);
            }
        }
        return contactsWithName;
    }


    public void showAllContacts() {
        Collection<Contact> allContacts = dao.getAll();
        for (Contact contact : allContacts) {
            if (contact != null) {
                System.out.println(contact);
            }
        }
    }
}


