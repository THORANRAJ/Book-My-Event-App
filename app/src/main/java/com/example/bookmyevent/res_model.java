package com.example.bookmyevent;

public class res_model {
    String hname,location,rating;

    res_model() {
    }

    public res_model(String hname, String location, String rating) {
        this.hname = hname;
        this.location = location;
        this.rating = rating;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
