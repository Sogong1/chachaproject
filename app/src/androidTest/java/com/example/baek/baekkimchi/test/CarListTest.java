package com.example.baek.baekkimchi.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.baek.baekkimchi.MainActivity;

/**
 * Created by Baek on 2015-06-08.
 */
public class CarListTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public CarListTest() {
        super(MainActivity.class);
    }


    public void testCarList1() {
        //In main page, name and price have to be shown in car list.
    }

    public void testCarList2() {
        //Car list have to be sorted following view counts.
    }

    public void testCarList3() {
        //User can select options for filtering such as fuel type, mission type.
    }

}
