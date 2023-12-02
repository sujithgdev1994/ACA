package com.infant.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataRequest {
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Time")
    private String time;
    @JsonProperty("illness")
    private String illness;
    @JsonProperty("Temp")
    private String temparature;
    @JsonProperty("Activity")
    private String activity;

    @JsonProperty("Date")
    public String getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("Time")
    public String getTime() {
        return time;
    }

    @JsonProperty("Time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("illness")
    public String getIllness() {
        return illness;
    }

    @JsonProperty("illness")
    public void setIllness(String illness) {
        this.illness = illness;
    }

    @JsonProperty("Temp")
    public String getTemparature() {
        return temparature;
    }

    @JsonProperty("Temp")
    public void setTemparature(String temparature) {
        this.temparature = temparature;
    }

    @JsonProperty("Activity")
    public String getActivity() {
        return activity;
    }

    @JsonProperty("Activity")
    public void setActivity(String activity) {
        this.activity = activity;
    }
}