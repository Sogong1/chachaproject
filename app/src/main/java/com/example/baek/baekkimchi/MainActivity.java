package com.example.baek.baekkimchi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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


public class MainActivity extends Activity {

    private Button bt_ok;
    private Button bt_skip;
    private TextView testBtnOK, testBtnSkip;
    private EditText input_age, input_cost;
    private RadioGroup input_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_age = (EditText) findViewById(R.id.input_age);
        input_gender = (RadioGroup) findViewById(R.id.input_gender);
        input_cost = (EditText) findViewById(R.id.input_cost);
        testBtnOK = (TextView)findViewById(R.id.testBtnOK);
        testBtnSkip = (TextView)findViewById(R.id.testBtnSkip);

        bt_ok = (Button) findViewById(R.id.btn_ok);
        bt_skip = (Button) findViewById(R.id.btn_skip);
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

    public void clickOKMethod(View v) {
        int age = Integer.parseInt(input_age.getText().toString());
//        input_gender.getch
        Toast.makeText(this, "Incorrect your ID or Password", Toast.LENGTH_SHORT).show();

        testBtnOK.setText("OK: true");
        startActivity(new Intent(MainActivity.this, ListActivity.class));
        finish();
        }

    public void clickSkipMethod(View v) {
        testBtnSkip.setText("Skip: true");
        startActivity(new Intent(MainActivity.this, ListActivity.class));
        finish();
    }
}
