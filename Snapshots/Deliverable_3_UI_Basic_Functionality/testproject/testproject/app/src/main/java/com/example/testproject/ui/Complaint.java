package com.example.testproject.ui;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Complaint {
    private String complaint;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String id;

    // Constructor for object from DB
    public Complaint(String complaint, String email, String firstName, String lastName, String role, String id){
        this.complaint = complaint;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.id = id;
    }
    // Constructor for object not from DB
    public Complaint(String complaint, String email, String firstName, String lastName, String role){
        this.complaint = complaint;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Complaint(){
        complaint = "";
        email = "";
        firstName = "";
        lastName = "";
        role = "";
    }
    // Getters
    public String getId() {return id;}
    public String getComplaint(){return complaint;}
    public String getEmail(){return email;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getRole(){return role;}
    // Setters
    public void setId(String id){this.id = id;}
    public void setComplaint(String complaint){this.complaint = complaint;}
    public void setEmail(String email){this.email = email;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setRole(String role){this.role = role;}
}
