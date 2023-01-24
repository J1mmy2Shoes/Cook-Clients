package com.example.testproject.users;

import androidx.annotation.NonNull;

public enum accType {ADMIN("Administrator"), COOK("Cook"),
    CLIENT("Client");

    String string;

    accType(String string){
        this.string = string;
    }

    @NonNull
    @Override
    public String toString() {
        return string;
    }
}
