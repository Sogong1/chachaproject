package com.example.baek.baekkimchi.test;

import android.app.Activity;
import android.database.Cursor;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.example.baek.baekkimchi.Database.DBManagerHandler;
import com.example.baek.baekkimchi.IntroActivity;
import com.example.baek.baekkimchi.MainActivity;
import com.example.baek.baekkimchi.R;

/**
 * Created by chasanghyeok on 2015. 6. 13..
 */
public class IntroSkipTest extends ActivityInstrumentationTestCase2<IntroActivity> {

    public IntroSkipTest() {
        super(IntroActivity.class);
    }

    public void testUserInformation1() {
        //When user executes the Personal Car Purchase Consulting with empty internal database,
        // GUI will pop up for input profile.
        Activity activity = getActivity();
        final TextView dbOK = (TextView)activity.findViewById(R.id.db_check);
        DBManagerHandler handler = new DBManagerHandler(getActivity().getApplicationContext());
        String query = "SELECT * FROM inform_table";
        Cursor cursor = handler.selectInform(query);

        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (cursor.moveToNext())
            assertEquals(dbOK.getText(), "true");
        else
            assertEquals(dbOK.getText(), "false");
    }

}
