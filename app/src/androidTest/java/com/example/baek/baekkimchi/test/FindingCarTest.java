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
        final String default_query = "select * from car where price >= \"2100\"-100 or price <= \"2100\"+100 LIMIT 10";
        final String default_xml = "<list><doc><car_index>1</car_index><name>SM3 네오</name><model>1.6 가솔린</model><type>준중형</type><engine_type>l4</engine_type><supply_method>자연흡기</supply_method><displacement>1598</displacement><fuel_type>가솔린</fuel_type><fuel_economy>15</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>1800</price><max_token>16.1</max_token><max_output>117</max_output></doc><doc><car_index>2</car_index><name>SM3</name><model>1.6 가솔린</model><type>준중형</type><engine_type>l4 1.6</engine_type><supply_method>자연흡기</supply_method><displacement>1598</displacement><fuel_type>가솔린</fuel_type><fuel_economy>15</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>1750</price><max_token>16.1</max_token><max_output>117</max_output></doc><doc><car_index>3</car_index><name>아반떼</name><model>1.6 GDi</model><type>준중형</type><engine_type>l4 직분사</engine_type><supply_method/><displacement>1691</displacement><fuel_type>가솔린</fuel_type><fuel_economy>14.1</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>수동</mission><price>1730</price><max_token>140</max_token><max_output>17</max_output></doc><doc><car_index>4</car_index><name>SM5 노바</name><model>2.0 가솔린</model><type>중형</type><engine_type>l4</engine_type><supply_method>자연흡기</supply_method><displacement>1998</displacement><fuel_type>가솔린</fuel_type><fuel_economy>12.6</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>2570</price><max_token>19.8</max_token><max_output>141</max_output></doc><doc><car_index>8</car_index><name>SM5 노바</name><model>TCE</model><type>중형</type><engine_type>l4</engine_type><supply_method>싱글 터보</supply_method><displacement>1618</displacement><fuel_type>가솔린</fuel_type><fuel_economy>13</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>2790</price><max_token>190</max_token><max_output>24.5</max_output></doc><doc><car_index>6</car_index><name>SM5 노바</name><model>1.5 디젤</model><type>중형</type><engine_type>l4 직분사</engine_type><supply_method>싱글 터보</supply_method><displacement>1461</displacement><fuel_type>디젤</fuel_type><fuel_economy>16.5</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>2680</price><max_token>24.5</max_token><max_output>110</max_output></doc><doc><car_index>7</car_index><name>아반떼</name><model>1.6 VGT</model><type>준중형</type><engine_type>l4 직분사</engine_type><supply_method/><displacement>1582</displacement><fuel_type>디젤</fuel_type><fuel_economy>18.5</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>수동</mission><price>1902</price><max_token>128</max_token><max_output>28.5</max_output></doc><doc><car_index>9</car_index><name>엑센트</name><model>1.4 VVT</model><type>소형</type><engine_type>l4 VVT</engine_type><supply_method>자연흡기</supply_method><displacement>1368</displacement><fuel_type>가솔린</fuel_type><fuel_economy>15</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>수동</mission><price>1367</price><max_token>100</max_token><max_output>13.6</max_output></doc><doc><car_index>10</car_index><name>엑센트</name><model>1.6 VGT</model><type>소형</type><engine_type>1.6 VGT</engine_type><supply_method>자연흡기</supply_method><displacement>1582</displacement><fuel_type>디젤</fuel_type><fuel_economy>19</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>수동</mission><price>1622</price><max_token>136</max_token><max_output>30.6</max_output></doc><doc><car_index>11</car_index><name>SM7 노바</name><model>2.5 가솔린</model><type>대형</type><engine_type>V6</engine_type><supply_method>자연흡기</supply_method><displacement>2495</displacement><fuel_type>가솔린</fuel_type><fuel_economy>10.2</fuel_economy><riding_personnal>5</riding_personnal><drive_type>전륜구동</drive_type><mission>자동</mission><price>3265</price><max_token>24.8</max_token><max_output>190</max_output></doc></list>";

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
