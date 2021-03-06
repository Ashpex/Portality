package com.ashpex.portality.model;

public class CourseSigned {
    private Integer _id;
    private String course_name;
    private String subject_name ;
    private String time_start;
    private String time_end;
    private Integer day_study;
    private int curr_state;
    private String teacher_name;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getDay_start() {
        return day_start;
    }

    public void setDay_start(String day_start) {
        this.day_start = day_start;
    }

    private String day_start;
    private String color;
    private String fee = "$0";

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Integer getCourse_id() {
        return _id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCourse_id(Integer course_id) {
        this._id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
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

    public Integer getDay_study() {
        return day_study;
    }

    public void setDay_study(Integer day_study) {
        this.day_study = day_study;
    }

    public int getCurr_state() {
        return curr_state;
    }

    public void setCurr_state(int curr_state) {
        this.curr_state = curr_state;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}
