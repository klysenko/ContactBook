package dao.impl;

import dao.ContactDao;
import model.Contact;

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
    public Contact [] getAll() {
        return new Contact[0];
   }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void modifyByName(String name, String newFirstName, String newLastName) {

    }
}
