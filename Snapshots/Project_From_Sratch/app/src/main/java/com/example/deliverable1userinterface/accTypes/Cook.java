
package com.example.deliverable1userinterface.accTypes;

public class Cook extends Account {

    private final static Types accType = Types.COOK;

    public Cook(String firstName, String lastName, String userName) {
        super(firstName, lastName, userName, accType);
    }

    public String toString(){
        return ("placeholder text");
    }

}
