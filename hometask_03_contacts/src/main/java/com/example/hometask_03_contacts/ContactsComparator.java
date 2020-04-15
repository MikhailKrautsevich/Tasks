package com.example.hometask_03_contacts;

import java.util.Comparator;

public class ContactsComparator implements Comparator <ContactClass> {
    @Override
    public int compare(ContactClass o1, ContactClass o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
