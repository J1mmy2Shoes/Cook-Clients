
package com.example.deliverable1userinterface.accTypes;

import java.io.Serializable;

//Serializable interface allows objects to be passed between activities
public class Account implements Serializable {

    private String firstName, lastName, email;
    private Types accType;

    public Account(String firstName, String lastName, String email, Types accType){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accType = accType;
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
    public Types getRole() {
        return accType;
    }

}
