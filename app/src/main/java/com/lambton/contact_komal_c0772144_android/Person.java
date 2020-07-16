package com.lambton.contact_komal_c0772144_android;

public class Person {

    int id;
    String firstName;
    String lastName;
    String phoneNumber;
    String emailaddress;
    String address;

    public Person(int id, String firstName, String lastName, String phoneNumber,String emailaddress, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailaddress=emailaddress;
        this.address = address;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getAddress() {
        return address;
    }
}
