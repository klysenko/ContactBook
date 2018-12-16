package dao.impl;

import dao.ContactDao;
import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContactDiskDao implements ContactDao {
    private static int idGenerator;

    public ContactDiskDao() {
        Collection<Contact> allContacts = getAll();
        if (allContacts.isEmpty()) {
            idGenerator = 0;
        } else {
            Integer lastContactId = null;
            for (Contact contact : allContacts) {
                lastContactId = contact.getId();
            }
            idGenerator = ++lastContactId;
        }
    }

    @Override
    public void saveContact(Contact contact) {
        File file = new File("ContactBook.txt");
        try (FileWriter fileWriter = new FileWriter(file, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            if (contact.getId() == 0) {
                contact.setId(idGenerator++);
            }
            printWriter.println(contact);
            printWriter.flush();
        } catch (IOException e) {
            System.out.println("Failed to save contact due to: " + e.getMessage());
        }
    }

    @Override
    public Collection<Contact> getAll() {
        File file = new File("ContactBook.txt");
        List<Contact> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                String[] strings = line.split(" ");
                int id = Integer.valueOf(strings[0]);
                String firstName = strings[1];
                String lastName = strings[2];
                int age = Integer.valueOf(strings[3]);
                list.add(new Contact(id, firstName, lastName, age));
            }
        } catch (IOException e) {
            System.out.println("Failed to get all contacts due to: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void deleteByName(String name) {
        int remainedCount = 0;
        Collection<Contact> allContacts = getAll();
        List<Contact> filteredByNameContacts = new ArrayList<>();
        for (Contact contact : allContacts) {
            if (contact == null) {
                continue;
            }
            if (!contact.getFirstName().contains(name) && !contact.getLastName().contains(name)) {
                filteredByNameContacts.add(contact);
                remainedCount++;
            }

        }
        System.out.println("Number of deleted contacts: " + (allContacts.size() - remainedCount));
        saveContacts(filteredByNameContacts);
    }

    @Override
    public void modifyByName(String name, String newFirstName, String newLastName) {
        Collection<Contact> allContacts = getAll();

        for (Contact contact : allContacts) {
            if (contact == null) {
                continue;
            }
            if (contact.getFirstName().contains(name) || contact.getLastName().contains(name)) {
                contact.setFirstName(newFirstName);
                contact.setLastName(newLastName);
            }
        }
        saveContacts(allContacts);
    }

    private void clearContacts() {
        File file = new File("ContactBook.txt");
        try (FileWriter fileWriter = new FileWriter(file, false);
             PrintWriter printWriter = new PrintWriter(fileWriter, false)) {
            printWriter.flush();
        } catch (IOException e) {
            System.out.println("Failed to clear the file due to: " + e.getMessage());
        }
    }

    private void saveContacts(Collection<Contact> contactsToSave) {
        clearContacts();
        for (Contact contact : contactsToSave) {
            saveContact(contact);
        }
    }
}
