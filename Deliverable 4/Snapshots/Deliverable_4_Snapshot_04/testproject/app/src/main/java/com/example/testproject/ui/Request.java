package com.example.testproject.ui;

import java.util.PriorityQueue;

public class Request {

    private String mealName;
    private String cook;
    private String client;
    private String cookName;
    private String status;


    public Request(){}
    public Request(String name, String cookId, String chef, String clientId){
        mealName = name;
        cook = cookId;
        client = clientId;
        cookName = chef;
        status = "pending";
    }

    public  String getMealName() {return mealName;}
    public  String getCookName() {return cookName;}
    public  String getCook() {return cook;}
    public  String getClient() {return client;}
    public  String getStatus() {return  status;}


}
