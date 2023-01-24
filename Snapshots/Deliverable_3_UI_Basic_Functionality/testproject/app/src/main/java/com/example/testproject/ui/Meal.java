package com.example.testproject.ui;

public class Meal {
    private String mealName;
    private String mealType;
    private String cuisineType;
    private String ingredients;
    private String allergies;
    private String price;
    private String description;
	private String cook;
    private Boolean isOffered;
    private String id;

    public Meal(){
        isOffered = false;
    }
    public Meal(String name,String type,String cuisine,String ingredients,String allergies,String price,String description, String cook, String id){
        mealName = name;
        mealType = type;
        cuisineType = cuisine;
        this.ingredients = ingredients;
        this.allergies = allergies;
        this.price = price;
        this.description = description;
		this.cook = cook;
        isOffered = false;
        this.id = id;
    }

    public String getId() {return id;}

    public String getMealType() {return mealType;}

    public String getMealName() {return mealName;}

    public String getCuisineType() {return cuisineType;}

    public String getIngredients() {return ingredients;}

    public String getAllergies() {return allergies;}

    public String getPrice() {return price;}

    public String getDescription() {return description;}
	
	public String getCook() {return cook;}

    public Boolean isOffered() {return isOffered;}

    public void setOffered(Boolean offered) {isOffered = offered;}
}
