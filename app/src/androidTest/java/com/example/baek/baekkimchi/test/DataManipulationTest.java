package com.example.baek.baekkimchi.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.baek.baekkimchi.MainActivity;

/**
 * Created by Baek on 2015-06-08.
 */
public class DataManipulationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public DataManipulationTest() {
        super(MainActivity.class);
    }

    public void testDataManipulation1() {
        //Only administrator can insert, remove, and modify data in database.
    }
    public void testDataManipulation2() {
        //If user doesn’t login, user can’t access this page.
    }
}
