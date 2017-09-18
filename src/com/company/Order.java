package com.company;

import java.lang.*;

/**
 * Created by LuyaoMBP on 9/13/17.
 */
public class Order {
    String id;
    int count;  // number of tickets in each Order
    Seat[] seats;   // Seat assignments

    /**
     * Constructor for Order
     * @param id order id
     * @param count number of tickets in each Order
     */
    public Order(String id, int count) throws MyInvalidInputException {
        if (id == null || id.length() == 0 || count < 0) {
            throw new MyInvalidInputException("invalid input");
        }
        this.id = id;
        this.count = count;
        this.seats = new Seat[count];
    }

    /**
     * Copy seats
     * @param seats Array of seats to be copied;
     */
    public void setSeats(Seat[] seats) {
        if (seats == null || seats.length != this.count) {
            throw new IllegalArgumentException("invalid argument seats");
        }
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