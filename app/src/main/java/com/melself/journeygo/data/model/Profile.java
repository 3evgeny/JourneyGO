package com.melself.journeygo.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

public class Profile {
    private long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String age;
    private String mainPassport;
    private String mainInitialPassport;
    private String mainNumberPassport;
    private String interPassport;
    private String interInitialPassport;
    private String interNumberPassport;

    public Profile() {
    }

    public Profile(long id, String firstName, String lastName, String patronymic, String age, String mainPassport, String mainInitialPassport, String mainNumberPassport, String interPassport, String interInitialPassport, String interNumberPassport) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.age = age;
        this.mainPassport = mainPassport;
        this.mainInitialPassport = mainInitialPassport;
        this.mainNumberPassport = mainNumberPassport;
        this.interPassport = interPassport;
        this.interInitialPassport = interInitialPassport;
        this.interNumberPassport = interNumberPassport;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMainPassport() {
        return mainPassport;
    }

    public void setMainPassport(String mainPassport) {
        this.mainPassport = mainPassport;
    }

    public String getMainInitialPassport() {
        return mainInitialPassport;
    }

    public void setMainInitialPassport(String mainInitialPassport) {
        this.mainInitialPassport = mainInitialPassport;
    }

    public String getMainNumberPassport() {
        return mainNumberPassport;
    }

    public void setMainNumberPassport(String mainNumberPassport) {
        this.mainNumberPassport = mainNumberPassport;
    }

    public String getInterPassport() {
        return interPassport;
    }

    public void setInterPassport(String interPassport) {
        this.interPassport = interPassport;
    }

    public String getInterInitialPassport() {
        return interInitialPassport;
    }

    public void setInterInitialPassport(String interInitialPassport) {
        this.interInitialPassport = interInitialPassport;
    }

    public String getInterNumberPassport() {
        return interNumberPassport;
    }

    public void setInterNumberPassport(String interNumberPassport) {
        this.interNumberPassport = interNumberPassport;
    }
}
