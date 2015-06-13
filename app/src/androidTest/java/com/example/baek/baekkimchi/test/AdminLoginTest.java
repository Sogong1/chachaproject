package com.example.baek.baekkimchi.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.baek.baekkimchi.MainActivity;

/**
 * Created by Baek on 2015-06-08.
 */
public class AdminLoginTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public AdminLoginTest() {
        super(MainActivity.class);
    }

    public void testAdminLogin1() {
        //When user opens the URL of admin website, GUI will popup for Log-in.
    }
    public void testAdminLogin2() {
        //There should be input fields for ID and password to login into the website.
    }
    public void testAdminLogin3() {
        //Administrator ID should be saved into the database.
    }
    public void testAdminLogin4() {
        //If user enters wrong information, it will give alert or, popup for wrong inputs.
    }
    public void testAdminLogin5() {
        //After successful log-in user should redirect to the administrator page.
    }
}
