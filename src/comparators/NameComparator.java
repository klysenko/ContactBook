package comparators;

import model.Contact;

import java.util.Comparator;

public class NameComparator implements Comparator<Contact> {


    @Override
    public int compare(Contact contact1, Contact contact2) {
        return contact1.getFirstName().compareTo(contact2.getFirstName());

    }
}



