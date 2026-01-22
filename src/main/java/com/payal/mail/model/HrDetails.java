package com.payal.mail.model;

public class HrDetails {

    private String name;
    private String email;
    private String company;

    public HrDetails() {
    }

    //used when retrieving row from excel and making java object and storing object inside List
    public HrDetails(String name, String email, String company) {
        this.name = name;
        this.email = email;
        this.company = company;
    }

    
    // getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
