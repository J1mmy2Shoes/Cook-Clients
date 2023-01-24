
package com.example.deliverable1userinterface.accTypes;


public class Client extends Account {

    private final static Types accType = Types.CLIENT;
    private String cardNum, address;


    public Client(String firstName, String lastName, String userName, String cardNum) {
        super(firstName, lastName, userName,accType);
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
