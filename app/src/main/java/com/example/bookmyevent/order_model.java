package com.example.bookmyevent;

public class order_model {
    String name,place,price,tickets,date;
    order_model()
    {

    }

    public order_model(String name, String place, String date, String tickets,String price) {
        this.name = name;
        this.place = place;
        this.date = date;
        this.tickets = tickets;
        this.price=price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }
}
