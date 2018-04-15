package com.chatapp.chatapp;

public class Message {

    public String actor;
    public String message;

    public Message(){
    }

    public Message(String actor , String message){
        this.actor = actor;
        this.message = message;
    }

    public String getActor(){
        return actor;
    }

    public void setActor(String actor){
        this.actor = actor;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
