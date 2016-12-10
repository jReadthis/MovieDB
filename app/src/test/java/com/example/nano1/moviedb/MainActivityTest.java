package com.example.nano1.moviedb;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nano1 on 5/31/2016.
 */
public class MainActivityTest {

    @Test
    public void testRandomNumberLess() throws Exception {
        MainActivity mainActivity = new MainActivity();
        int expected = mainActivity.randomNumber(1,10);
        System.out.println(expected);
        assertTrue(expected < 10);
    }

    @Test
    public void testRandomNumberGreater() throws Exception {
        MainActivity mainActivity = new MainActivity();
        int expected = mainActivity.randomNumber(1,10);
        System.out.println(expected);
        assertTrue(expected > 1);
    }
}