package com.company;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by LuyaoMBP on 9/12/17.
 */
public class Theater {
    private int count;
    private boolean[][] seats_map;

    // Todo: helper
    private String[][] seat_helper;

    public Theater() {
        this.count = 0;
        this.seats_map = new boolean[10][20];
        this.seat_helper = new String[10][20];
    }

    public void processOrder(Order order) {
        if (order.count == 0 || order.count + this.count > 200) {
            return;
        }

        int max_value = 0;
        int current_value = 0;

        //PriorityQueue<Seat> heap = new PriorityQueue<>(
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
                    heap.offer(new Seat(i, j));
                }
                if (this.seats_map[i][j] || j == 19){
                    if (heap.size() == 0) {
                        continue;
                    }
                    Seat[] current_seats = new Seat[order.count];
                    int m = 0;
                    if (heap.size() > order.count) {
                        for (m = 0; m < order.count; m++) {
                            Seat temp = heap.poll();
                            current_value += temp.value;
                            current_seats[m] = temp;
                        }
                    } else {
                        while (m < order.count && !heap.isEmpty()) {
                            Seat temp = heap.poll();
                            if (temp.x == i && temp.x + 1 < 10 && !this.seats_map[temp.x + 1][temp.y]) {
                                heap.offer(new Seat(temp.x + 1, temp.y));
                            }
                            current_value += temp.value;
                            current_seats[m++] = temp;
                        }
                    }
                    if (m == order.count && current_value > max_value) {
                        order.seats = current_seats;
                        max_value = current_value;
                    }
                    current_value = 0;
                    heap = new PriorityQueue<>(comparator);
                }
            }
        }
        bookSeats(order.seats, order.id);
    }

    private void getSeats(int row, int last_seat, int count, Seat[] output) {
        for (int i = 0; i < count; i++) {
            int seat = last_seat - count + 1 + i;
            // this.seats[row][seat] = true;
            output[i] = new Seat(row, seat);
        }
    }

    private void bookSeats(Seat[] seats_to_book, String order_id) {
        for (Seat seat : seats_to_book) {
            if (seat != null) {
                this.seats_map[seat.x][seat.y] = true;
                this.count++;
                this.seat_helper[seat.x][seat.y] = order_id;
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
