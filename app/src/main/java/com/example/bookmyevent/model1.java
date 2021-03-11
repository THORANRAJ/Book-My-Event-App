package com.example.bookmyevent;

public class model1 {
     String match,location,purl,tickets,date,time,price,id;
    model1()
    {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public model1(String match, String location, String purl, String date, String price, String tickets, String time, String id){//String price) {
        this.match = match;
        this.location=location;
        this.purl=purl;
      // this.tickets=tickets;
        this.date=date;
        this.price=price;
        this.tickets=tickets;
        this.time=time;
        this.id=id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
   public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }
}

