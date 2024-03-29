package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by LuyaoMBP on 9/12/17.
 */
public class Theater {
    int count;  // count the booked seats in each theater
    boolean[][] seats_map;  // 2d seat map to mark if the seat is taken

    public Theater() {
        this.count = 0;
        this.seats_map = new boolean[10][20];
    }
    
    /**
     * main function to process each order;
     * It will use a sliding window mechanism to find best seats on each row
     * and set the seats of each order;
     * @param order the order to be processed
     */
    public void processOrder(Order order) throws MyInvalidInputException {
        if (order.count + this.count > 200 || order.count == 0) {
            return;
        }
        int max_value = 0;
        int curr_count = 0;
        int current_value = 0;
        Seat[] best_seats = new Seat[order.count];
        for (int i = 0; i < 10; i++) {
            current_value = 0;
            curr_count = 0;
            for (int j = 0; j < 20; j++) {
                // if the seat is taken,
                if (this.seats_map[i][j] == true) {
                    current_value = 0;
                    curr_count = 0;
                    continue;
                }
                // expand the window size to order size
                if (curr_count < order.count) {
                    curr_count++;
                    current_value += Seat.calculateSeatValue(i, j);
                } else {
                    // slide the window
                    current_value += Seat.calculateSeatValue(i, j);
                    current_value -= Seat.calculateSeatValue(i, j - order.count);
                }
                if (curr_count == order.count && current_value > max_value) {
                    // if we find the better seats, update seats and max_value
                    max_value = current_value;
                    getSeats(i, j, order.count, best_seats);
                    order.setSeats(best_seats);
                }
            }
        }
        bookSeats(best_seats);
    }

    // create a array of seats
    public void getSeats(int row, int last_seat, int count, Seat[] output) throws MyInvalidInputException {
        if (count > last_seat + 1) {
            throw new MyInvalidInputException("count must be less than or equals to last_seat + 1");
        }
        for (int i = 0; i < count; i++) {
            int seat = last_seat - count + 1 + i;
            output[i] = new Seat(row, seat);
        }
    }

    public void bookSeats(Seat[] seats_to_book) {
        for (Seat seat : seats_to_book) {
            if (seat != null) {
                // mark seats as taken
                this.seats_map[seat.x][seat.y] = true;
                this.count++;
            }
        }
    }

    // helper method to print out seat
    public void helper() {
        StringBuilder sb = new StringBuilder();
        for (boolean[] row : this.seats_map) {
            for (boolean seat : row) {
                if (seat) {
                    sb.append("*");
                } else {
                    sb.append("O");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
