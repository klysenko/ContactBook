package dao.impl;

import dao.ContactDao;
import model.Contact;

public class ContactArrayDao implements ContactDao {
    private static int ID_GENERATOR = 0;
    private int count = 0;
    private Contact[] store = new Contact[10];

    @Override
    public void saveContact(Contact contact) {
        contact.setId(getNextId());
        store[count] = contact;
        count++;
        System.out.println("Stored contact: " + contact.toString());
    }

    @Override
    public Contact[] getAll() {
        if (count == 0) {
            return new Contact [count];
        }
        Contact[] storeCopy = new Contact[store.length];
        System.arraycopy(store, 0, storeCopy, 0, store.length);
        return storeCopy;
    }

    @Override
    public void deleteByName(String name) {
        int deletedCount = 0;
        for (int i = 0; i < store.length; i++) {
            Contact contact = store[i];
            if (contact == null) {
                continue;
            }
            if (contact.getFirstName().contains(name) || contact.getLastName().contains(name)) {
                store[i] = null;
                deletedCount++;
            }
        }
        System.out.println("Number of deleted contacts: " + deletedCount);
    }

    @Override
    public void modifyByName(String name, String newFirstName, String newLastName) {
        for (int i = 0; i < store.length; i++) {
            Contact contact = store[i];
            if (contact == null) {
                continue;
            }
            if (contact.getFirstName().contains(name) || contact.getLastName().contains(name)) {
                contact.setFirstName(newFirstName);
                contact.setLastName(newLastName);
            }
        }

    }

    private int getNextId() {
        return ID_GENERATOR++;
    }
}
