package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by LuyaoMBP on 9/17/17.
 */
public class TheaterTest {
    Theater theater_1;
    Order order_1;
    @Before
    public void setUp() throws MyInvalidInputException {
        theater_1 = new Theater();
        order_1 = new Order("R001", 2);
    }

    @Test
    public void processOrderTest() throws MyInvalidInputException {
        theater_1.processOrder(order_1);
        assertEquals("R001 F10,F11", order_1.toString());
    }

    @Test
    public void processOrderTest2() throws MyInvalidInputException {
        Order o1 = new Order("R001", 5);
        theater_1.processOrder(o1);
        assertEquals("R001 F8,F9,F10,F11,F12", o1.toString());

        Order o2 = new Order("R002", 1);
        theater_1.processOrder(o2);
        assertEquals("R002 G10", o2.toString());

        Order o3 = new Order("R003", 5);
        theater_1.processOrder(o3);
        assertEquals("R003 G11,G12,G13,G14,G15", o3.toString());
    }

    @Test
    public void bookSeatsTest() throws MyInvalidInputException {
        Seat[] seats_to_book = new Seat[2];
        seats_to_book[0] = new Seat(0, 0);
        seats_to_book[1] = new Seat(0, 1);
        theater_1.bookSeats(seats_to_book);
        assertTrue(theater_1.seats_map[0][0]);
        assertTrue(theater_1.seats_map[0][1]);
        assertEquals(2, theater_1.count);
    }

    @Test
    public void getSeatsTest() throws MyInvalidInputException {
        Seat[] output = new Seat[3];
        theater_1.getSeats(0, 5,3, output);
        assertEquals("A4", output[0].toString());
        assertEquals("A5", output[1].toString());
        assertEquals("A6", output[2].toString());
    }

    @Test
    public void getSeatsTest2() throws MyInvalidInputException {
        Seat[] output = new Seat[5];
        try {
            theater_1.getSeats(0, 3, 5, output);
            fail();
        } catch (MyInvalidInputException e) {
            return;
        }
    }
}