package com.ashpex.portality.model;

public class SignUpCourseForm {
    private String course_name;
    private int subject_id;
    private String description;
    private String time_start;
    private String time_end;
    private int day_study;
    private String day_start;
    private String day_end;
    private int max_slot;
    private long fee;
    private String room;
    private String requirement;

    public SignUpCourseForm(String course_name, int subject_id, String description, String time_start,
                            String time_end, int day_study, String day_start, String day_end, int max_slot,
                            long fee, String room, String requirement) {
        this.course_name = course_name;
        this.subject_id = subject_id;
        this.description = description;
        this.time_start = time_start;
        this.time_end = time_end;
        this.day_study = day_study;
        this.day_start = day_start;
        this.day_end = day_end;
        this.max_slot = max_slot;
        this.fee = fee;
        this.room = room;
        this.requirement = requirement;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public int getDay_study() {
        return day_study;
    }

    public void setDay_study(int day_study) {
        this.day_study = day_study;
    }

    public String getDay_start() {
        return day_start;
    }

    public void setDay_start(String day_start) {
        this.day_start = day_start;
    }

    public String getDay_end() {
        return day_end;
    }

    public void setDay_end(String day_end) {
        this.day_end = day_end;
    }

    public int getMax_slot() {
        return max_slot;
    }

    public void setMax_slot(int max_slot) {
        this.max_slot = max_slot;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }
}
