package dao;

import model.Contact;


public class ContactDao {
    private int count = 0;
    private Contact[] store = new Contact[10];

    public void saveContact(Contact contact) {
        store[count] = contact;
        count++;
        System.out.println("Stored contact: " + contact);
    }

    public Contact[] getAll() {
        if (count == 0) {
            return new Contact[0];
        }
        Contact[] storeCopy = new Contact[store.length];
        System.arraycopy(store, 0, storeCopy, 0, store.length);
        return storeCopy;
    }

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
}
