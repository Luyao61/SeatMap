package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by LuyaoMBP on 9/17/17.
 */
public class SeatTest {
    Seat seat_1;

    @Before
    public void setUp() throws Exception {
        seat_1 = new Seat(1,1);
    }

    @Test
    public void toStringTest() {
        assertEquals("B2", seat_1.toString());
    }

    @Test
    public void seatValueTest(){
        assertEquals(0, Seat.calculateSeatValue(0,0));
        assertEquals(239, Seat.calculateSeatValue(5,9));
    }

    @Test
    public void InvalidSeatTest1() {
        try {
            Seat invalidSeat = new Seat(-1, -1);
            fail("Exception expected.");
        } catch (MyInvalidInputException e) {
            return;
        }
    }

    @Test
    public void InvalidSeatTest2() {
        try {
            Seat invalidSeat = new Seat(30, 30);
            fail("Exception expected.");
        } catch (MyInvalidInputException e) {
            return;
        }
    }
}