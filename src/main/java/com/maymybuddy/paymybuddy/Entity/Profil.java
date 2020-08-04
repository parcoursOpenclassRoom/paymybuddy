package com.maymybuddy.paymybuddy.Entity;

public class Profil {
    private int id;
    private String firstName;
    private String lastName;

    public Profil() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getName(){
        return firstName +" "+ lastName;
    }
}
