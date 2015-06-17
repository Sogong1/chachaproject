package com.example.baek.baekkimchi.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.baek.baekkimchi.ListActivity;
import com.example.baek.baekkimchi.R;

/**
 * Created by Baek on 2015-06-08.
 */
public class FindingCarTest extends ActivityInstrumentationTestCase2<ListActivity> {

    public final static int FRAGMENT_ONE = 0;
    public final static int FRAGMENT_TWO = 1;

    String gender_table;

    public FindingCarTest() {
        super(ListActivity.class);
    }

    public void testFindingCarTest1() {
        //There should be a tool which will find appropriate cars for each user according to
        // salary, gender, age and filters.
        //Assume that default_age is 20, default_gender is "남자".

        Activity activity = getActivity();

        final TextView age_check = (TextView) activity.findViewById(R.id.age_check);
        final TextView gender_check = (TextView) activity.findViewById(R.id.gender_check);
        final TextView query_check = (TextView) activity.findViewById(R.id.query_check);
        final TextView xml_check = (TextView) activity.findViewById(R.id.xml_check);

        final String default_age = "20";
        final String default_gender = "남자";
        final String default_query
                = "select car_index, company_name, car_name, car_model, type, engene_type, supply_method"
                +", displacement, fuel_type, fuel_economy, riding_personnal, drive_type"
                +", mission, price, max_token, max_output, img " +
                "from car c NATURAL JOIN company where price >= \"2100\"-100 or price <= \"2100\"+100 " +
                "order by c.price ASC LIMIT 10";
        final String default_xml = "<<list><doc><car_index>57</car_index><company_name>기아자동차</company_name><name>K5</name><model>2.0 가솔린</model><type>중형</type><engine_type>l4 2.0</engine_type><supply_method>자연흡기</supply_method><displacement>1999</displacement><fuel_type>가솔린</fuel_type><fuel_economy>12.2</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>수동</mission><price>2040</price><max_token>20.5</max_token><max_output>172</max_output><img>57.png</img></doc><doc><car_index>52</car_index><company_name>기아자동차</company_name><name>K3 세단</name><model>1.6 디젤</model><type>준중형</type><engine_type>l4 직분사 디젤</engine_type><supply_method>싱글 터보</supply_method><displacement>1582</displacement><fuel_type>디젤</fuel_type><fuel_economy>16.2</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>2050</price><max_token>28.5</max_token><max_output>128</max_output><img>52.png</img></doc><doc><car_index>71</car_index><company_name>기아자동차</company_name><name>스포티지R</name><model>2.0 디젤 2WD</model><type>준중형</type><engine_type>l4 2.0 직분사</engine_type><supply_method>싱글 터보</supply_method><displacement>1995</displacement><fuel_type>디젤</fuel_type><fuel_economy>14.4</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>수동</mission><price>2065</price><max_token>41</max_token><max_output>184</max_output><img>71.jpg</img></doc><doc><car_index>89</car_index><company_name>현대자동차</company_name><name>i30</name><model>2.0 GDi</model><type>준중형</type><engine_type>l4 직분사</engine_type><supply_method>자연흡기</supply_method><displacement>1999</displacement><fuel_type>가솔린</fuel_type><fuel_economy>11.8</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>2080</price><max_token>172</max_token><max_output>21</max_output><img>89.png</img></doc><doc><car_index>35</car_index><company_name>쉐보레</company_name><name>크루즈</name><model>1.4 가솔린 터보</model><type>준중형</type><engine_type>l4</engine_type><supply_method>싱글 터보</supply_method><displacement>1362</displacement><fuel_type>가솔린</fuel_type><fuel_economy>12.6</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>2092</price><max_token>20.4</max_token><max_output>140</max_output><img>35.png</img></doc><doc><car_index>55</car_index><company_name>기아자동차</company_name><name>K3 쿠페</name><model>1.6 가솔린 터보</model><type>준중형</type><engine_type>l4 1.6</engine_type><supply_method>싱글 터보</supply_method><displacement>1591</displacement><fuel_type>가솔린</fuel_type><fuel_economy>12.7</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>수동</mission><price>2100</price><max_token>27</max_token><max_output>204</max_output><img>55.png</img></doc><doc><car_index>69</car_index><company_name>기아자동차</company_name><name>쏘울</name><model>1.6 디젤 에코 다이나믹스</model><type>준중형</type><engine_type>I4 1.6 직분사 디젤</engine_type><supply_method>싱글 터보</supply_method><displacement>1585</displacement><fuel_type>디젤</fuel_type><fuel_economy>14.1</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>2100</price><max_token>26.5</max_token><max_output>128</max_output><img>69.png</img></doc><doc><car_index>42</car_index><company_name>쉐보레</company_name><name>트랙스</name><model>1.4 가솔린 터보</model><type>소형</type><engine_type>l4</engine_type><supply_method>싱글 터보</supply_method><displacement>1362</displacement><fuel_type>가솔린</fuel_type><fuel_economy>12.2</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>2137</price><max_token>20.4</max_token><max_output>140</max_output><img>42.png</img></doc></list>";

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //check age is changed.
                age_check.setText(default_age);
                assertEquals(default_age, age_check.getText().toString());

                //check gender is changed.
                gender_check.setText(default_gender);
                assertEquals(default_gender, gender_check.getText().toString());
                setGenderTable(gender_check.getText().toString());
                query_check.setText(default_query);
                assertEquals(default_query, query_check.getText().toString());
                xml_check.setText(default_xml);
                assertEquals(default_xml, xml_check.getText().toString());

            }
        });

    }

    public void setGenderTable(String s){
        if(s.equals("남"))
            gender_table = "man_hit";
        else if(s.equals("여자"))
            gender_table = "woman_hit";
    }

}
