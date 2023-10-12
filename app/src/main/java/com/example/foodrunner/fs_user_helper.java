package com.example.foodrunner;

import android.media.Image;

public class fs_user_helper {
    String email, name, number;

    public fs_user_helper(String email, String name, String number) {
        this.email = email;
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }
}