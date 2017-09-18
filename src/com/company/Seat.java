package com.company;

/**
 * Created by LuyaoMBP on 9/13/17.
 */
public class Seat {
    int x, y;
    public Seat(int x, int y) throws MyInvalidInputException{
        if (x < 0 || x > 9 || y < 0 || y > 19) {
            throw new MyInvalidInputException("Invalid Seat Number");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("%c%d", (char)('A' + x), y + 1);
    }

    /**
     * calculate the seat value based on a preset origin;
     * col has more weight than row
     * @param row row number
     * @param seat seat number
     * @return positive number of seat value;
     */
    public static int calculateSeatValue(int row, int seat) {
        int x = (seat > 9) ? seat + 1 : seat;
        x = x - 10;
        int y = (row > 5) ? row + 1 : row;
        y = 6 - y;

        int max = (0 - 10) * (0 - 10) + 2 * (6 - 0) * 2 * (6 - 0);
        return  max - (x * x + 2 * y * 2 * y);
    }
}
