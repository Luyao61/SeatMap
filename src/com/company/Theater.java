package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by LuyaoMBP on 9/12/17.
 */
public class Theater {
    private int count;  // count the booked seats in each theater
    private boolean[][] seats_map;  // 2d seat map to mark if the seat is taken

    // Help visualize seats; mark seats with order id
    private String[][] seat_helper;

    // Theater constructor
    public Theater() {
        this.count = 0;
        this.seats_map = new boolean[10][20];
        this.seat_helper = new String[10][20];
    }

    /**
     * main function to process each order;
     * It will use a bfs mechanism to find best seats on each row
     * and set the seats of each order;
     * If the available seats are not enough to accommodate current order,
     * it will generate seats on the next row to see if there is a match
     * @param order the order to be processed
     */
    public void processOrder(Order order) {
        if (order.count == 0 || order.count + this.count > 200) {
            return;
        }

        int max_value = 0;
        int current_value = 0;

        // comparator for seat value
        Comparator<Seat> comparator = new Comparator<Seat>() {
            @Override
            public int compare(Seat o1, Seat o2) {
                if (o1.value == o2.value) {
                    return 0;
                } else if (o1.value > o2.value) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };

        for (int i = 0; i < 10; i++) {
            PriorityQueue<Seat> heap = new PriorityQueue<>(comparator);
            for (int j = 0; j < 20; j++) {
                if (!this.seats_map[i][j]) {
                    // if the seat is not taken, put this seat into the heap;
                    heap.offer(new Seat(i, j));
                }
                // find top k best seats when reach end of row or
                if (this.seats_map[i][j] || j == 19){
                    if (heap.size() == 0) {
                        continue;
                    }
                    Seat[] current_seats = new Seat[order.count];
                    int m = 0;
                    // if one row has enough seat to accommodate the order
                    if (heap.size() > order.count) {
                        for (m = 0; m < order.count; m++) {
                            Seat temp = heap.poll();
                            current_value += temp.value;
                            current_seats[m] = temp;
                        }
                    } else {
                        // if available seats on the current row is not enough for this order
                        // expand this seat and generate the seat behind it
                        while (m < order.count && !heap.isEmpty()) {
                            Seat temp = heap.poll();
                            // put seat in the next row into the heap
                            if (temp.x == i && temp.x + 1 < 10 && !this.seats_map[temp.x + 1][temp.y]) {
                                heap.offer(new Seat(temp.x + 1, temp.y));
                            }
                            current_value += temp.value;
                            current_seats[m++] = temp;
                        }
                    }
                    if (m == order.count && current_value > max_value) {
                        // if we find the better seats, update seats and max_value
                        order.setSeats(current_seats);
                        max_value = current_value;
                    }
                    current_value = 0;
                    heap = new PriorityQueue<>(comparator);
                }
            }
        }
        bookSeats(order.seats, order.id);
    }

    private void bookSeats(Seat[] seats_to_book, String order_id) {
        for (Seat seat : seats_to_book) {
            if (seat != null) {
                // mark seats as taken
                this.seats_map[seat.x][seat.y] = true;
                this.count++;
                this.seat_helper[seat.x][seat.y] = order_id;
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

    // helper method to print out seat map, seat is filled with order id;
    public void helper2() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.seat_helper.length; i++) {
            for (int j = 0; j < this.seat_helper[0].length; j++) {
                if (seat_helper[i][j] != null) {
                    sb.append(seat_helper[i][j]);
                } else {
                    sb.append("XXXX");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
