package com.example.demofirebase;

public class Data {

    public String Name,Email,Country, Gender;
    public long Contact;

    public Data() {
    }

    public Data(String name, String email, long contact, String pass, String gender) {
        Name = name;
        Email = email;

        Contact = contact;
        Country= pass;
        Gender= gender;
    }
}
