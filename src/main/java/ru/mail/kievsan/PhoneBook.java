package ru.mail.kievsan;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Optional;

public class PhoneBook {

    protected record Contact(String name, String phone) {
    }

    private final Map<String, Contact> contactName = new TreeMap<>();
    private final Map<String, Contact> contactPhone = new HashMap<>();

    public int add(String name, String phone) {
        if (
                name == null || phone == null ||
                name.isEmpty() || phone.isEmpty() ||
                contactName.containsKey(name)
        ) {
            return contactName.size();
        }
        Contact newContact = new Contact(name, phone);
        contactName.put(name, newContact);
        contactPhone.put(phone, newContact);

        return contactName.size();
    }

    public String findByNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return "";
        }
        return Optional.ofNullable(contactPhone.get(phoneNumber))
                .map(Contact::name)
                .orElse("");
    }

    public String findByName(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        return Optional.ofNullable(contactName.get(name))
                .map(Contact::phone)
                .orElse("");
    }

    public void printAllNames() {
        System.out.print("");
    }

}
