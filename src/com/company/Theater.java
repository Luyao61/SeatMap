package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by LuyaoMBP on 9/12/17.
 */
public class Theater {
    private int count;  // count the booked seats in each theater
    private boolean[][] seats_map;  // 2d seat map to mark if the seat is taken

    public Theater() {
        this.count = 0;
        this.seats_map = new boolean[10][20];
    }

    /**
     * calculate the seat value based on a preset origin;
     * col has more weight than row
     * @param row row number
     * @param seat seat number
     * @return positive number of seat value;
     */
    public int seatValue(int row, int seat) {
        int x = (seat > 9) ? seat + 1 : seat;
        x = x - 10;
        int y = (row > 5) ? row + 1 : row;
        y = 6 - y;

        int max = (0 - 10) * (0 - 10) + 2 * (6 - 0) * 2 * (6 - 0);
        return  max - (x * x + 2 * y * 2 * y);
    }

    /**
     * main function to process each order;
     * It will use a sliding window mechanism to find best seats on each row
     * and set the seats of each order;
     * @param order the order to be processed
     */
    public void processOrder(Order order) {
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
                    current_value += seatValue(i, j);
                } else {
                    // slide the window
                    current_value += seatValue(i, j);
                    current_value -= seatValue(i, j - order.count);
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
    private void getSeats(int row, int last_seat, int count, Seat[] output) {
        for (int i = 0; i < count; i++) {
            int seat = last_seat - count + 1 + i;
            output[i] = new Seat(row, seat);
        }
    }

    private void bookSeats(Seat[] seats_to_book) {
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
