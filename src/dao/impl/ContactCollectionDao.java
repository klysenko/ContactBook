package dao.impl;

import dao.ContactDao;
import model.Contact;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ContactCollectionDao implements ContactDao {
    private Map<Integer, Contact> store;
    private int generator = 0;

    public ContactCollectionDao() {
        store = new HashMap<>();
    }
    @Override
    public void saveContact(Contact contact) {
        contact.setId(generator++);
      store.put(contact.getId(), contact);
      System.out.println(contact);
    }

    @Override
    public Collection<Contact> getAll() {

       Map <Integer,Contact> storeCopy = new HashMap<>();
       storeCopy.putAll(store);
       return (Collection<Contact>) storeCopy;


    }

    @Override
    public void deleteByName(String name) {
        int deletedCount = 0;
        for (Map.Entry<Integer, Contact> entry:  store.entrySet()) {
            Integer key = entry.getKey();
            Contact value = entry.getValue();
            if (value == null) {
                continue;
            }
            if (value.getFirstName().contains(name) || value.getLastName().contains(name)) {
                store.remove(key, value);
                deletedCount++;
            }
        }
        System.out.println("Number of deleted contacts: " + deletedCount);
    }



    @Override
    public void modifyByName(String name, String newFirstName, String newLastName) {

    }
}
