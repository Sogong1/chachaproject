package com.example.baek.baekkimchi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.baek.baekkimchi.dataset.DataSet;

import java.util.ArrayList;


public class DetailViewActivity extends Activity {
    private TextView name;
    private TextView model;
    private TextView price;
    private TextView type;
    private TextView engine_type;
    private TextView supply_method;
    private TextView displacement;
    private TextView fuel_type;
    private TextView fuel_economy;
    private TextView riding_personal;
    private TextView drive_type;
    private TextView mission;
    private TextView max_token;
    private TextView max_output;
    private DataSet mdataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        initTextView();
        mdataSet = (DataSet)getIntent().getSerializableExtra("mDataset");

        setTextView();
    }

    public void initTextView(){
        name = (TextView)findViewById(R.id.Car_name);
        model = (TextView)findViewById(R.id.Car_model);
        price = (TextView)findViewById(R.id.Car_price);
        type = (TextView)findViewById(R.id.type);
        engine_type = (TextView)findViewById(R.id.engine_type);
        supply_method = (TextView)findViewById(R.id.supply_method);
        displacement = (TextView)findViewById(R.id.displacement);
        fuel_type = (TextView)findViewById(R.id.fuel_type);
        fuel_economy = (TextView)findViewById(R.id.fuel_economy);
        riding_personal = (TextView)findViewById(R.id.riding_personal);
        drive_type = (TextView)findViewById(R.id.drive_type);
        mission = (TextView)findViewById(R.id.mission);
        max_token = (TextView)findViewById(R.id.max_token);
        max_output = (TextView)findViewById(R.id.max_output);
    }

    public void setTextView(){
        name.setText(mdataSet.getName());
        model.setText(mdataSet.getModel());
        price.setText(mdataSet.getPrice()+"");
        type.setText(mdataSet.getType());
        engine_type.setText(mdataSet.getEngine_type());
        supply_method.setText(mdataSet.getSupply_method());
        displacement.setText(mdataSet.getDisplacement());
        fuel_type.setText(mdataSet.getFuel_type());
        fuel_economy.setText(mdataSet.getFuel_economy());
        riding_personal.setText(mdataSet.getRiding_personal());
        drive_type.setText(mdataSet.getDrive_type());
        mission.setText(mdataSet.getMission());
        max_token.setText(mdataSet.getMax_token());
        max_output.setText(mdataSet.getMax_output());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_view, menu);
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
}
