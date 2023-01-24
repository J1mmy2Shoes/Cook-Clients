package com.example.testproject.ui;

import com.example.testproject.R;

public class Request {

    private String mealName;
    private String cook;
    private String client;
    private String status;
    private Boolean isOffered;
    private String id;

    public Request() {;}
    public Request(String name, String cookId, String clientId){
        mealName = name;
        cook = cookId;
        client = clientId;
        status = "pending";
    }

    public String getId() {return id;}

    public  String getMealName() {return mealName;}
    public  String getCook() {return cook;}
    public  String getClient() {return client;}
    public  String getStatus() {return  status;}


}
