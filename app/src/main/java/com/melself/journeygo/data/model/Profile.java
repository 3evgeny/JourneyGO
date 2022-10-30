package com.melself.journeygo.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Profile {
    private long id;
    private String firstName;
    private String lastName;
    private String age;
    private String phone;
    private String mainPassport;

    public Profile() {
    }

    public Profile(long id, String firstName, String lastName, String age, String phone, String mainPassport) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
        this.mainPassport = mainPassport;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDbPassport() {
        return mainPassport;
    }

    public void setDbPassport(String mainPassport) {
        this.mainPassport = mainPassport;
    }
}
