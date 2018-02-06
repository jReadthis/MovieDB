package com.example.nano1.moviedb.activity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nano1 on 2/5/2018.
 */
public class ListActivityTest {

    ListActivity listActivity;

    @Before
    public void setUp() throws Exception {
        listActivity = new ListActivity();
    }

    @After
    public void tearDown() throws Exception {
        listActivity = null;
    }

    @Test
    public void testGenerateDates(){
        listActivity.generateDates();
        assertTrue(true);

    }


}