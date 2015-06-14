package com.example.baek.baekkimchi;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baek.baekkimchi.Database.DBManagerHandler;


public class MainActivity extends Activity {

    private TextView testBtnOK, testBtnSkip, calledAgain;
    private EditText input_age, input_cost;
    private RadioGroup input_gender;
    private DBManagerHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new DBManagerHandler(getApplicationContext());
        input_age = (EditText) findViewById(R.id.input_age);
        input_gender = (RadioGroup) findViewById(R.id.input_gender);
        input_cost = (EditText) findViewById(R.id.input_cost);
        testBtnOK = (TextView)findViewById(R.id.testBtnOK);
        testBtnSkip = (TextView)findViewById(R.id.testBtnSkip);
        calledAgain = (TextView)findViewById(R.id.called_again);

        Intent intent = getIntent();
        if(intent.getExtras() != null) calledAgain.setText("true");
        else calledAgain.setText("false");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int clickOKMethod(View v) {
        int age=0, cost=0;
        String gender="";

        try {
            age = Integer.parseInt(input_age.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "나이를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
            return 0;
        }

        if (input_gender.getCheckedRadioButtonId() == R.id.input_male)
            gender = "남자";
        else if (input_gender.getCheckedRadioButtonId() == R.id.input_female)
            gender = "여자";
        else {
            Toast.makeText(this, "성별을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return 0;
        }

        try {
            cost = Integer.parseInt(input_cost.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "금액을 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
            return 0;
        }

        Toast.makeText(this, "나이 : " + age + "\n성별 : " + gender + "\n금액 : " + cost, Toast.LENGTH_SHORT).show();

        String query1 = "SELECT * FROM inform_table";
        Cursor cursor = handler.selectInform(query1);
        if (cursor.moveToNext()) {
            String query2 = "UPDATE inform_table SET age = "+age+", gender = '"+gender+"', cost="+cost+";";
            handler.update(query2);
        }
        else {
            handler.insertInform(age, gender, cost); // db에 insert
        }

        Cursor tmp = handler.selectInform(query1);
        while(tmp.moveToNext()) {
            Log.i("DB: ", tmp.getInt(tmp.getColumnIndex("age")) + "");
            Log.i("DB: ", tmp.getString(tmp.getColumnIndex("gender")));
            Log.i("DB: ", tmp.getInt(tmp.getColumnIndex("cost")) + "");
        }
        testBtnOK.setText("OK: true");
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("isSkip", false);
        intent.putExtra("age", age);
        intent.putExtra("gender", gender);
        intent.putExtra("cost", cost);

        startActivity(intent);
        finish();

        return 0;
    }

    public void clickSkipMethod(View v) {
        testBtnSkip.setText("Skip: true");
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putExtra("isSkip", true);

        startActivity(intent);
        finish();
    }
}
