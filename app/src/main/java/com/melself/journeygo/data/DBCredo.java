package com.melself.journeygo.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "credo", foreignKeys = @ForeignKey(
        entity = DBProfile.class,
        parentColumns = "_id",
        childColumns = "_profile_id"))
public class DBCredo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long id;

    @ColumnInfo(name = "_profile_id")
    private long profile_id;

    @ColumnInfo(name = "_username")
    private String username;

    @ColumnInfo(name = "_email")
    private String email;

    @ColumnInfo(name = "_password")
    private String password;

    @ColumnInfo(name = "_role")
    private int role;

    @Ignore
    public DBCredo() {
    }

    public DBCredo(long id, long profile_id, String username, String email, String password, int role) {
        this.id = id;
        this.profile_id = profile_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(long profile_id) {
        this.profile_id = profile_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
