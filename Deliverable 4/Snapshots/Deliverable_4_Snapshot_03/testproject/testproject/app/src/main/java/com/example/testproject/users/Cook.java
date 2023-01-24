
package com.example.testproject.users;

public class Cook extends Account {

    private final static accType acc = accType.COOK;


    public Cook(String firstName, String lastName, String userName, String complaint, String accStatus) {
        super(firstName, lastName, userName, complaint, accStatus, acc);

    }

    public String toString(){
        return ("placeholder text");
    }

}
