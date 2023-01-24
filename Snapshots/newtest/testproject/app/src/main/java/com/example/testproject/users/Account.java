
package com.example.testproject.users;

import java.io.Serializable;

//Serializable interface allows objects to be passed between activities
public class Account implements Serializable {

    private String firstName, lastName, email;
    private accType acc;

    public Account(String firstName, String lastName, String email, accType acc){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.acc = acc;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public accType getRole() {
        return acc;
    }



}
