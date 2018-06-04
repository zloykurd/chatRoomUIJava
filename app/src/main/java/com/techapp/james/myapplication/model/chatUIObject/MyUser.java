package com.techapp.james.myapplication.model.chatUIObject;

public class MyUser implements ChatUser {
    public String id = "", name = "";

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
