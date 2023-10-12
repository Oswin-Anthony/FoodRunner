package com.example.foodrunner;

public class fs_food_helper {
    String name;
    float cost;

    public fs_food_helper(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public float getCost() { return cost; }

    public void setCost(float cost) { this.cost = cost; }
}
