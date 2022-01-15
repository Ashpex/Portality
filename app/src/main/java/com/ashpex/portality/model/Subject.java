package com.ashpex.portality.model;

public class Subject {
    private int _id;
    private String subject_name;
    private String color;

    public Subject(int _id, String subject_name) {
        this._id = _id;
        this.subject_name = subject_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
