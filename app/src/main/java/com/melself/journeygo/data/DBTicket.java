package com.melself.journeygo.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.data.model.Ticket;

@Entity(tableName = "tickets")
public class DBTicket {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private long id;

    @ColumnInfo(name = "_number")
    private String number;

    @ColumnInfo(name = "_country")
    private String country;

    @ColumnInfo(name = "_hotel")
    private String hotel;

    @ColumnInfo(name = "_date")
    private String date;

    @ColumnInfo(name = "_status")
    private String status;

    @Ignore
    public DBTicket() {
    }

    public DBTicket(long id, String number, String country, String hotel, String date, String status) {
        this.id = id;
        this.number = number;
        this.country = country;
        this.hotel = hotel;
        this.date = date;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static DBTicket convertToDBTicket(Ticket ticket){
        DBTicket dbTicket = new DBTicket();
        dbTicket.setId(ticket.getId());
        dbTicket.setNumber(ticket.getNumber());
        dbTicket.setCountry(ticket.getCountry());
        dbTicket.setHotel(ticket.getHotel());
        dbTicket.setDate(ticket.getDate());
        dbTicket.setStatus(ticket.getStatus());
        return dbTicket;
    }

    public Ticket convertToTicket(){
        Ticket ticket = new Ticket();
        ticket.setId(this.getId());
        ticket.setNumber(this.getNumber());
        ticket.setCountry(this.getCountry());
        ticket.setHotel(this.getHotel());
        ticket.setDate(this.getDate());
        ticket.setStatus(this.getStatus());
        return ticket;
    }
}
