package dao;

import model.Contact;

public interface ContactDao {
    void saveContact(Contact contact);

    Contact [] getAll();

    void deleteByName(String name);

    void modifyByName(String name, String newFirstName, String newLastName);
}
