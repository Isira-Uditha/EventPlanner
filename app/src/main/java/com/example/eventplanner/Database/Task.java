package com.example.eventplanner.Database;

public class Task {

    private int id;
    private String taskName , description;
    private int finished;
    private String date , time;

    public Task() {
    }

    public Task(String taskName, String description, String date, int finished, String time) {
        this.taskName = taskName;
        this.description = description;
        this.date = date;
        this.finished = finished;
        this.time = time;
    }

    public Task(int id, String taskName,String date,String time, String description, int finished) {
        this.id = id;
        this.taskName = taskName;
        this.date = date;
        this.time = time;
        this.description = description;
        this.finished = finished;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
