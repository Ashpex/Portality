package com.ashpex.portality.model;

public class UserCourse {
    private String course_name;
    private Integer subject_id;
    private String time_start;
    private String time_end;
    private Integer day_study;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Integer getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Integer subject_id) {
        this.subject_id = subject_id;
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
}
