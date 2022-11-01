package com.melself.journeygo.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.melself.journeygo.data.model.Profile;

import java.util.List;

@Entity(tableName = "profile")
public class DBProfile {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long id;

    @ColumnInfo(name = "_firstName")
    private String firstName;

    @ColumnInfo(name = "_lastName")
    private String lastName;

    @ColumnInfo(name = "_patronymic")
    private String patronymic;

    @ColumnInfo(name = "_age")
    private String age;

    @ColumnInfo(name = "_phone")
    private String phone;

    @ColumnInfo(name = "_mainPassport")
    private String mainPassport;

    @ColumnInfo(name = "_mainInitialPassport")
    private String mainInitialPassport;

    @ColumnInfo(name = "_mainNumberPassport")
    private String mainNumberPassport;

    @ColumnInfo(name = "_interPassport")
    private String interPassport;

    @ColumnInfo(name = "_interInitialPassport")
    private String interInitialPassport;

    @ColumnInfo(name = "_interNumberPassport")
    private String interNumberPassport;

    @Ignore
    public DBProfile() {
    }

    public DBProfile(long id, String firstName, String lastName, String patronymic, String age, String phone, String mainPassport, String mainInitialPassport, String mainNumberPassport, String interPassport, String interInitialPassport, String interNumberPassport) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.age = age;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public static DBProfile convertToDBProfile(Profile profile){
        DBProfile dbProfile = new DBProfile();
        dbProfile.setId(profile.getId());
        dbProfile.setFirstName(profile.getFirstName());
        dbProfile.setLastName(profile.getLastName());
        dbProfile.setPatronymic(profile.getPatronymic());
        dbProfile.setAge(profile.getAge());
        dbProfile.setMainPassport(profile.getMainPassport());
        dbProfile.setMainInitialPassport(profile.getMainInitialPassport());
        dbProfile.setMainNumberPassport(profile.getMainNumberPassport());
        dbProfile.setInterPassport(profile.getInterPassport());
        dbProfile.setInterInitialPassport(profile.getInterInitialPassport());
        dbProfile.setInterNumberPassport(profile.getInterNumberPassport());
        return dbProfile;
    }

    public Profile convertToProfile(){
        Profile profile = new Profile();
        profile.setId(this.getId());
        profile.setFirstName(this.getFirstName());
        profile.setLastName(this.getLastName());
        profile.setPatronymic(this.getPatronymic());
        profile.setAge(this.getAge());
        profile.setMainPassport(this.getMainPassport());
        profile.setMainInitialPassport(this.getMainInitialPassport());
        profile.setMainNumberPassport(this.getMainNumberPassport());
        profile.setInterPassport(this.getInterPassport());
        profile.setInterInitialPassport(this.getInterInitialPassport());
        profile.setInterNumberPassport(this.getInterNumberPassport());
        return profile;
    }
}
