package com.example.foodapp;

public class Signup_Model {
    private String Name;
    private String Email;
    private String Password;
    private String PhoneNo;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }



    public Signup_Model(String name, String email, String password, String phoneNo) {
        Name = name;
        Email = email;
        Password = password;
        PhoneNo = phoneNo;
    }

    public Signup_Model() {
    }
}
