package com.example.ch5_mycontactlist;
//This class is full of methods to get/set (insert/update) existing contacts
//Declares variables for each piece of data needed for contact
//Declares methods to set values of variables & methods to get values of variables

import java.util.Calendar;

public class Contact {
    private int contactID;
    private String contactName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String cellNumber;
    private String eMail;
    private Calendar birthday;

    public Contact() {
        //-1 = Determines if contact is new and needs to be inserted OR already exists and needs to be updated
        contactID = -1;
        //birthday initialized to current date; allows app to assume there will always be a value in this variable
        birthday = Calendar.getInstance();
    }

    public int getContactID() {
        return contactID;
    }
    public void setContactID(int i) {
        contactID = i;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactName(String s) {
        contactName = s;
    }
    public Calendar getBirthday() {
        return birthday;
    }
    public void setBirthday(Calendar c) {
        birthday = c;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String s) {
        streetAddress = s;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String s) {
        city = s;
    }
    public String getState() {
        return state;
    }
    public void setState(String s) {
        state = s;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String s) {
        zipCode = s;
    }
    public void setPhoneNumber(String s) {
        phoneNumber = s;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setCellNumber(String s) {
        cellNumber = s;
    }
    public String getCellNumber() {
        return cellNumber;
    }
    public void setEMail(String s) {
        eMail = s;
    }
    public String getEMail() {
        return eMail;
    }
}
