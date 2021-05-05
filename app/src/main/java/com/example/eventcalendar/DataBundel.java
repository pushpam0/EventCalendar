package com.example.eventcalendar;

public class DataBundel {
String Event_Name,Name_Of_Name,Event_Date,Event_Time;
   public DataBundel(){}



    public DataBundel(String event_Name, String name_Of_Name, String event_Date, String event_Time) {
        Event_Name = event_Name;
        Name_Of_Name = name_Of_Name;
        Event_Date = event_Date;
        Event_Time=event_Time;
    }

    public String getEvent_Time() {
        return Event_Time;
    }

    public void setEvent_Time(String event_Time) {
        Event_Time = event_Time;
    }

    public String getEvent_Name() {
        return Event_Name;
    }

    public void setEvent_Name(String event_Name) {
        Event_Name = event_Name;
    }

    public String getName_Of_Name() {
        return Name_Of_Name;
    }

    public void setName_Of_Name(String name_Of_Name) {
        Name_Of_Name = name_Of_Name;
    }

    public String getEvent_Date() {
        return Event_Date;
    }

    public void setEvent_Date(String event_Date) {
        Event_Date = event_Date;
    }
}
