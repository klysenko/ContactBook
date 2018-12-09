package dao;

import model.Contact;

import java.util.Collection;

public interface ContactDao {
    void saveContact(Contact contact);

    Collection<Contact> getAll();

    void deleteByName(String name);

    void modifyByName(String name, String newFirstName, String newLastName);
}
