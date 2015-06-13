package com.example.baek.baekkimchi.test;

import android.app.Activity;
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

    public UserInformationTest() {
        super(MainActivity.class);
    }


    public void testUserInformation2() {
        //There should be options such as gender, age and salary
        Activity activity = getActivity();
        final EditText et_age = (EditText) activity.findViewById(R.id.input_age);
        final EditText et_cost = (EditText) activity.findViewById(R.id.input_cost);
        final RadioGroup rg_gender = (RadioGroup) activity.findViewById(R.id.input_gender);
        final RadioButton rb_male = (RadioButton) activity.findViewById(R.id.input_male);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                et_age.setText("30");
                assertEquals("30", et_age.getText().toString());
                et_cost.setText("3000");
                assertEquals("3000", et_cost.getText().toString());

                Log.i("testRadioID1: ",rg_gender.getCheckedRadioButtonId()+"");
                rb_male.setChecked(true);
                Log.i("testRadioID2: ",rg_gender.getCheckedRadioButtonId()+"");
                Log.i("testRadioID3: ",R.id.input_male+"");

                assertEquals(rg_gender.getCheckedRadioButtonId(), R.id.input_male);

            }
        });

    }

    public void testUserInformation3() {
        //If user doesn’t want to input his data, user can skip to input information.
        Activity activity = getActivity();
        final Button btn_skip = (Button) activity.findViewById(R.id.btn_skip);
        final TextView testBtnSkip = (TextView) activity.findViewById(R.id.testBtnSkip);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btn_skip.callOnClick();
                Log.i("testBtnSkipText: ",testBtnSkip.getText().toString());
                assertEquals(testBtnSkip.getText().toString(), "Skip: true");
            }
        });

        Log.i("testBtnSkipText: ",testBtnSkip.getText().toString());
    }

    public void testUserInformation4() {
        //User can open information page by clicking a button on main page and change his information again.

    }
}
