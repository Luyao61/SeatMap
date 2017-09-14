package com.company;

/**
 * Created by LuyaoMBP on 9/13/17.
 */
public class Order {
    String id;
    int count;
    Seat[] seats;

    public Order(String id, int count) {
        this.id = id;
        this.count = count;
        this.seats = new Seat[count];
    }

    public void setSeats(Seat[] seats) {
        for (int i = 0; i < seats.length; i++) {
            this.seats[i] = seats[i];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.id);
        String delim = " ";
        for (Seat s : seats) {
            if (s == null) {
                sb.append(delim).append("NA");
            } else {
                sb.append(delim).append(s.toString());
            }
            delim = ",";
        }
        return sb.toString();
    }
}