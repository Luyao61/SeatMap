package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by LuyaoMBP on 9/17/17.
 */
public class OrderTest {
    Order order_1;
    @Before
    public void setUp() throws MyInvalidInputException {
        order_1 = new Order("R001", 5);
    }

    @Test
    public void toStringTest1() {
        assertEquals("R001 NA,NA,NA,NA,NA", order_1.toString());
    }

    @Test
    public void toStringTest2() throws MyInvalidInputException {
        Order order_2 = new Order("R002", 3);
        assertEquals("R002 NA,NA,NA", order_2.toString());
    }

    @Test
    public void setSeatsTest1() throws MyInvalidInputException {
        Seat[] seats = new Seat[5];
        for (int i = 0; i < 5; i++) {
            seats[i] = new Seat(0, i);
        }
        order_1.setSeats(seats);
        for (int i = 0; i < 5; i++) {
            assertEquals(0, order_1.seats[i].x);
            assertEquals(i, order_1.seats[i].y);
        }
    }

    @Test
    public void setSeatsTest2() throws MyInvalidInputException {
        Seat[] seats = new Seat[4];
        for (int i = 0; i < 4; i++) {
            seats[i] = new Seat(0, i);
        }
        try {
            order_1.setSeats(seats);
            fail("expecting an exception here.");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    @Test
    public void constructorTest1() {
        try {
            Order invliad_order = new Order(null, 1);
            fail("Exception expected.");
        } catch (MyInvalidInputException e) {
            return;
        }
    }

    @Test
    public void constructorTest2() {
        try {
            Order invliad_order = new Order("R001", -1);
            fail("Exception expected.");
        } catch (MyInvalidInputException e) {
            return;
        }
    }
}