package com.example.baek.baekkimchi.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.example.baek.baekkimchi.DetailViewActivity;
import com.example.baek.baekkimchi.MainActivity;
import com.example.baek.baekkimchi.R;

/**
 * Created by Baek on 2015-06-08.
 */
public class DetailViewTest extends ActivityInstrumentationTestCase2<DetailViewActivity> {

    public DetailViewTest() {
        super(DetailViewActivity.class);
    }

    public void testDetailView1() {
        //On this page, user should be able to inquire car’s specs in detail.
        Activity activity = getActivity();

        TextView name = (TextView)activity.findViewById(R.id.Car_name);
        TextView model = (TextView)activity.findViewById(R.id.Car_model);
        TextView price = (TextView)activity.findViewById(R.id.Car_price);
        TextView type = (TextView)activity.findViewById(R.id.type);
        TextView engine_type = (TextView)activity.findViewById(R.id.engine_type);
        TextView supply_method = (TextView)activity.findViewById(R.id.supply_method);
        TextView displacement = (TextView)activity.findViewById(R.id.displacement);
        TextView fuel_type = (TextView)activity.findViewById(R.id.fuel_type);
        TextView fuel_economy = (TextView)activity.findViewById(R.id.fuel_economy);
        TextView riding_personal = (TextView)activity.findViewById(R.id.riding_personal);
        TextView drive_type = (TextView)activity.findViewById(R.id.drive_type);
        TextView mission = (TextView)activity.findViewById(R.id.mission);
        TextView max_token = (TextView)activity.findViewById(R.id.max_token);
        TextView max_output = (TextView)activity.findViewById(R.id.max_output);

        assertEquals(name != null, true);
        assertEquals(model != null, true);
        assertEquals(price != null, true);
        assertEquals(type != null, true);
        assertEquals(engine_type != null, true);
        assertEquals(supply_method != null, true);
        assertEquals(displacement != null, true);
        assertEquals(fuel_type != null, true);
        assertEquals(fuel_economy != null, true);
        assertEquals(riding_personal != null, true);
        assertEquals(drive_type != null, true);
        assertEquals(mission != null, true);
        assertEquals(max_token != null, true);
        assertEquals(max_output != null, true);


    }
}
