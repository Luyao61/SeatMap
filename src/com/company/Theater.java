package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by LuyaoMBP on 9/12/17.
 */
public class Theater {
    private int count;
    private boolean[][] seats_map;

    public Theater() {
        this.count = 0;
        this.seats_map = new boolean[10][20];
    }

    public int seatValue(int row, int seat) {
        int x = (seat > 9) ? seat + 1 : seat;
        x = x - 10;
        int y = (row > 5) ? row + 1 : row;
        y = 6 - y;

        int max = ((0 - 10) * 2) * ((0 - 10) * 2) + (6 - 0) * (6 - 0);
        return  max - ((2*x)*(2*x) + y * y);
    }

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
                if (this.seats_map[i][j] == true) {
                    current_value = 0;
                    curr_count = 0;
                    continue;
                }
                if (curr_count < order.count) {
                    curr_count++;
                    current_value += seatValue(i, j);
                } else {
                    current_value += seatValue(i, j);
                    current_value -= seatValue(i, j - order.count);
                }
                if (curr_count == order.count && current_value > max_value) {
                    max_value = current_value;
                    getSeats(i, j, order.count, best_seats);
                    order.setSeats(best_seats);
                }
            }
        }
        bookSeats(best_seats);
    }

    private void getSeats(int row, int last_seat, int count, Seat[] output) {
        for (int i = 0; i < count; i++) {
            int seat = last_seat - count + 1 + i;
            // this.seats[row][seat] = true;
            output[i] = new Seat(row, seat);
        }
    }

    private void bookSeats(Seat[] seats_to_book) {
        for (Seat seat : seats_to_book) {
            if (seat != null) {
                this.seats_map[seat.x][seat.y] = true;
            }
        }
    }

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
