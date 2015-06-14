package com.example.baek.baekkimchi.test;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.baek.baekkimchi.MainActivity;
import com.example.baek.baekkimchi.R;

import junit.framework.TestCase;

import org.w3c.dom.Text;

/**
 * Created by Baek on 2015-06-02.
 */
public class UserInformationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    Activity activity;

    public UserInformationTest() {
        super(MainActivity.class);
    }


    public void testUserInformation2() {
        //There should be options such as gender, age and salary
        activity = getActivity();
        final EditText et_age = (EditText) activity.findViewById(R.id.input_age);
        final EditText et_cost = (EditText) activity.findViewById(R.id.input_cost);
        final RadioGroup rg_gender = (RadioGroup) activity.findViewById(R.id.input_gender);

        boolean ageExist = false;
        boolean genderExist = false;
        boolean costExist = false;

        if(et_age != null) ageExist = true;
        if(rg_gender != null) genderExist = true;
        if(et_cost != null) costExist = true;

        assertEquals(ageExist, true);
        assertEquals(genderExist, true);
        assertEquals(costExist, true);

    }


    public void testUserInformation3() {
        //User can open information page by clicking a button on main page and change his information again.
        activity = getActivity();

        TextView calledAgain = (TextView) activity.findViewById(R.id.called_again);
        Intent intent = activity.getIntent();
        if(intent.getExtras() != null)
            assertEquals(calledAgain.getText().toString(), "true");
        else
            assertEquals(calledAgain.getText().toString(), "false");

    }

    public void testUserInformation4() {
        activity = getActivity();
        //If user doesn’t want to input his data, user can skip to input information.
        final Button btn_skip = (Button) activity.findViewById(R.id.btn_skip);
        final TextView testBtnSkip = (TextView) activity.findViewById(R.id.testBtnSkip);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btn_skip.callOnClick();
                Log.i("testBtnSkipText: ", testBtnSkip.getText().toString());
                assertEquals(testBtnSkip.getText().toString(), "Skip: true");
            }
        });

        Log.i("testBtnSkipText: ", testBtnSkip.getText().toString());
    }

}
