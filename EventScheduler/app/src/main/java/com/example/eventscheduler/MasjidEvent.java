package com.example.eventscheduler;

public class MasjidEvent {
    private String event_name;
    private String date;
    private String duration;
    private String description;
    //image


    public MasjidEvent(String event_name, String date, String duration) {
        this.event_name = event_name;
        this.date = date;
        this.duration = duration;
        //this.description = description;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
