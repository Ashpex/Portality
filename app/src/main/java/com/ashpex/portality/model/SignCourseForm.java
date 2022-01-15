package com.ashpex.portality.model;

public class SignCourseForm {
    private String name;
    private String email;
    private String password;
    private int type;
    private String gender;
    private String birthday;

    public SignCourseForm(String name, String email, String password, int type, String gender, String birthday) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
