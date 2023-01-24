package com.example.deliverable1userinterface.accTypes;

import androidx.annotation.NonNull;

public enum Types {ADMIN("Administrator"), COOK("Cook"),
    CLIENT("Client");

    String string;

    Types(String string){
        this.string = string;
    }
}
