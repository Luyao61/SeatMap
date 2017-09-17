package com.company;

/**
 * Created by LuyaoMBP on 9/13/17.
 */
public class Seat {
    int x, y;
    public Seat(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%c%d", (char)('A' + x), y + 1);
    }
}
