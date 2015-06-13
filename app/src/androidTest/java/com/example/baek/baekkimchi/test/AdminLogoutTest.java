package com.example.baek.baekkimchi.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.baek.baekkimchi.MainActivity;

/**
 * Created by Baek on 2015-06-08.
 */
public class AdminLogoutTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public AdminLogoutTest() {
        super(MainActivity.class);
    }

    public void testAdminLogout1() {
        //Only if administrator were login into the website, then logout tool/button should be visible otherwise it shall be void.
    }
}
