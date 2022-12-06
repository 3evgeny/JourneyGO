package com.melself.journeygo.data.model;

public class Ticket {
    private long id;
    private long person_id;
    private String number;
    private String country;
    private String hotel;
    private String date;
    private String status;

    public Ticket() {
    }

    public Ticket(long id, long person_id, String number, String country, String hotel, String date, String status) {
        this.id = id;
        this.person_id = person_id;
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

    public long getPerson_id() {
        return person_id;
    }

    public void setPerson_id(long person_id) {
        this.person_id = person_id;
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
}
