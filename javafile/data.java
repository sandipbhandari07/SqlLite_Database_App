package com.example.mylogin;

public class data {
    private int id;
    private String name;
    private int date;
    private int amount;

    public data(int id, String name, int date, int amount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
    }
    public data(String title, String date, String amount)
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
