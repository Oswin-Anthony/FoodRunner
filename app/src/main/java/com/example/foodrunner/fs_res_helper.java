package com.example.foodrunner;

public class fs_res_helper {
    double cost;
    String name;
    double rating;

    public fs_res_helper(double cost, String name, double rating) {
        this.name = name;
        this.rating = rating;
        this.cost = cost;
    }

    public String get_name() { return name; }

    public void set_name(String name) { this.name = name; }

    public double get_rating() { return rating; }

    public void set_rating(double rating) { this.rating = rating; }

    public double get_cost() { return cost; }

    public void set_cost(double cost) { this.cost = cost; }
}
