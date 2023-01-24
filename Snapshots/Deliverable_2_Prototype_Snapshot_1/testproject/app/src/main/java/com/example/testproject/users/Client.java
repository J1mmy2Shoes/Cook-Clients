
package com.example.testproject.users;


public class Client extends Account {

    private final static accType acc = accType.CLIENT;
    private String cardNum, address;


    public Client(String firstName, String lastName, String userName, String cardNum, String complaint) {
        super(firstName, lastName, userName, complaint, acc);
        this.cardNum = cardNum;
    }

    public String getCardNum() {
        return cardNum;
    }

    @Override
    public String toString(){
        return ("CardNum " + cardNum);
    }
}
