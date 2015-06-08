import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.example.baek.baekkimchi.MainActivity;
import com.example.baek.baekkimchi.R;

import junit.framework.TestCase;

/**
 * Created by Baek on 2015-06-02.
 */
public class UserInformationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public UserInformationTest() {
        super(MainActivity.class);
    }

    public void UserInformation1() {
        //When user executes the Personal Car Purchase Consulting with empty internal database, GUI will pop up for input profile.
    }

    public void UserInformation2() {
        //There should be options such as gender, age and salary
        Activity activity = getActivity();
        EditText et_age = (EditText) activity.findViewById(R.id.input_age);
        EditText et_cost = (EditText) activity.findViewById(R.id.input_cost);
        et_age.setText("30");
        et_cost.setText("3000");
        assertEquals("30", et_age.getText().toString());
    }

    public void UserInformation3() {
        //If user doesn’t want to input his data, user can skip to input information.
    }

    public void UserInformation4() {
        //User can open information page by clicking a button on main page and change his information again.

    }
}
