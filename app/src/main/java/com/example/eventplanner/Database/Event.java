package com.example.eventplanner.Database;
//This is the event model class
public class Event {

    //variable declaration
    private int id;
    private String eventName, date, time, location, theme, dressCode, photographer, description, place;


    //default constructor
    public Event() {
    }

    //overloaded constructor
    public Event(int id, String eventName, String date, String time, String location, String theme, String dressCode, String photographer,  String description, String place) {
        this.id = id;
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.theme = theme;
        this.dressCode = dressCode;
        this.photographer = photographer;
        this.description = description;
        this.place = place;


    }

    //overloaded constructor without id.
    public Event(String eventName, String date, String time, String location, String theme, String dressCode, String photographer, String description, String place) {
        this.eventName = eventName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.theme = theme;
        this.dressCode = dressCode;
        this.photographer = photographer;
        this.description = description;
        this.place = place;

    }

    //setters and getters for the variables.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDressCode() {
        return dressCode;
    }

    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    public String getPhotographer() {
        return photographer;
    }

    public void setPhotographer(String photographer) {
        this.photographer = photographer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
