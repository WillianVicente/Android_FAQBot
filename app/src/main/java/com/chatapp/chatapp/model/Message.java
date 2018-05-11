package com.chatapp.chatapp.model;

public class Message {

    private String time;
    private String actor;
    private String message;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Message(){
    }

    public Message(String actor , String message, String time){
        this.actor = actor;
        this.message = message;
        this.time = time;
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
