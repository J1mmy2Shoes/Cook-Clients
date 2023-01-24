
package com.example.testproject.users;

public class Cook extends Account {

    private final static accType ACC = accType.COOK;
    private String address, description;
    private double rating;
    private int ratingNum;


    public Cook(String firstName, String lastName, String userName, String complaint, String accStatus) {
        super(firstName, lastName, userName, complaint, accStatus, ACC);
        rating = -1;
        address = "Address unset";
        description = "No description";
        ratingNum = 0;
    }

    public Cook(String firstName, String lastName, String userName, String address, String desc, String complaint,
                String accStatus, double rating) {
        super(firstName, lastName, userName, complaint, accStatus, ACC);
        this.rating = rating;
        this.address = address;
        description = desc;
        ratingNum = 0;
    }

    public String toString(){
        return ("placeholder text");
    }
    public int getRatingNum() {return ratingNum;}
    public double getRating() {return rating;}
    public void setRating(double rating) {this.rating = rating;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
