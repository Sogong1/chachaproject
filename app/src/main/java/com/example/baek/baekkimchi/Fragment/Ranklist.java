package com.example.baek.baekkimchi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baek.baekkimchi.Connection.ConnectionManager;
import com.example.baek.baekkimchi.DetailViewActivity;
import com.example.baek.baekkimchi.R;

import java.util.ArrayList;

import com.example.baek.baekkimchi.dataset.DataSet;


public class Ranklist extends Fragment {

    private ListView mListView;
    private CustomAdapter mAdapter;
    private Bundle bundle;
    private String query;
    private boolean isSkip;
    private ConnectionManager mConnectionManager;
    private int USERLIST_REQUEST = 1000;
    private int RECOMMENDLIST_REQUEST = 2000;
    private ArrayList<DataSet> temp;
    private ArrayList<DataSet> DatasetList;
    private ArrayAdapter<CharSequence>  selected_age;
    private ArrayAdapter<CharSequence>  selected_gender;
    private String query_age;
    private String query_gender;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        isSkip = bundle.getBoolean("isSkip");
        LinearLayout comboBox = (LinearLayout)getActivity().findViewById(R.id.recommend_combo);
        comboBox.setVisibility(View.VISIBLE);

        Spinner age_combo = (Spinner) getActivity().findViewById(R.id.age_combo);
        Spinner gender_combo = (Spinner) getActivity().findViewById(R.id.gender_combo);
        age_combo.setPrompt("나이를 입력해주세요.");
        gender_combo.setPrompt("성별을 선택해주세요.");

        selected_age = ArrayAdapter.createFromResource(getActivity(), R.array.age, android.R.layout.simple_spinner_item);

        selected_age.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        age_combo.setAdapter(selected_age);
        age_combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),
                        selected_age.getItem(position) + "을 선택 했습니다.", Toast.LENGTH_SHORT).show();
                setAge(selected_age.getItem(position).toString());
                if(query_gender == null)
                    updateUI(query_age, "man_hit");
                else
                    updateUI(query_age, query_gender);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        selected_gender = ArrayAdapter.createFromResource(getActivity(), R.array.gender, android.R.layout.simple_spinner_item);

        selected_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_combo.setAdapter(selected_gender);
        gender_combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
                Toast.makeText(getActivity(),
                        selected_gender.getItem(position) + "을 선택 했습니다.", Toast.LENGTH_SHORT).show();
                setGender(selected_gender.getItem(position).toString());
                updateUI(query_age, query_gender);
            }
            public void onNothingSelected(AdapterView<?>  parent) {
            }
        });

        DatasetList = new ArrayList<>();
        DatasetList.add(new DataSet("A", "A", 1234));
        DatasetList.add(new DataSet("B", "B", 345));
        DatasetList.add(new DataSet("C", "C", 678));
        DatasetList.add(new DataSet("A", "A", 1234));
        DatasetList.add(new DataSet("B", "B", 345));
        DatasetList.add(new DataSet("C", "C", 678));
        DatasetList.add(new DataSet("A", "A", 1234));
        DatasetList.add(new DataSet("B", "B", 345));
        DatasetList.add(new DataSet("C", "C", 678));


//        query = bundle.getString("query");
        query = "SELECT car_name, car_model, type, engene_type, supply_method"
                +", displacement, fuel_type, fuel_economy, riding_personnal, drive_type"
                +", mission, price, max_token"
                +" FROM man_hit NATURAL JOIN car ORDER BY `20` DESC LIMIT 10";

        mConnectionManager = new ConnectionManager(query, RECOMMENDLIST_REQUEST);
        mConnectionManager.execute();

        Log.i("What first?", "fucks");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        temp = mConnectionManager.getXmlList();
        if (temp == null)
            Log.i("XML TEST1 !!", "It's null!!");
        else
            Log.i("XML TEST1 !!", "성공");
    }

    public void setAge(String s){
        query_age = s;
    }

    public void setGender(String s){
        query_gender = s;
    }
    public String setGenderTable(String s){
        if(s.equals("남자"))
            return "man_hit";
        else if(s.equals("여자"))
            return "woman_hit";
        return "";
    }

    public void updateUI(String age, String gender){
        final String age_query = age;
        final String gender_query;
        String gender_temp = gender;
        gender_query = setGenderTable(gender_temp);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                query = "SELECT car_name, car_model, type, engene_type, supply_method"
                        +", displacement, fuel_type, fuel_economy, riding_personnal, drive_type"
                        +", mission, price, max_token"
                        +" FROM "+gender_query+" NATURAL JOIN car ORDER BY `"+age_query+"` DESC LIMIT 10";

                mConnectionManager = new ConnectionManager(query, RECOMMENDLIST_REQUEST);
                mConnectionManager.execute();

                Log.i("What first?", "fucks");

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                temp = mConnectionManager.getXmlList();

                mAdapter = new CustomAdapter(getActivity(), R.layout.item_card, temp);
//        mAdapter = new CustomAdapter(getActivity(), R.layout.item_card, DatasetList);
                // use a linear layout manager

                mListView.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_ranklist, container, false);

        mListView = (ListView) v.findViewById(R.id.rank_list);

        mAdapter = new CustomAdapter(getActivity(), R.layout.item_card, temp);
//        mAdapter = new CustomAdapter(getActivity(), R.layout.item_card, DatasetList);
        // use a linear layout manager

        mListView.setAdapter(mAdapter);

        return v;
    }

    private class CustomAdapter extends ArrayAdapter<DataSet> {

        private ArrayList<DataSet> mDataset;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<DataSet> items) {
            super(context, textViewResourceId, items);
            this.mDataset = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.item_card, null);
            }
            LinearLayout ll_cardLayout = (LinearLayout) v.findViewById(R.id.ll_cardLayout);
            LinearLayout.LayoutParams lparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            // TextView에 현재 position의 문자열 추가
            TextView Car_rank = (TextView) v.findViewById(R.id.Car_rank);
            Car_rank.setText(position + 1 + "");

            TextView Car_name = (TextView) v.findViewById(R.id.Car_name);
            Car_name.setText(mDataset.get(position).getName());

            TextView Car_company = (TextView) v.findViewById(R.id.Car_company);
            Car_company.setText(mDataset.get(position).getCompany());

            TextView Car_price = (TextView) v.findViewById(R.id.Car_price);
            Car_price.setText(mDataset.get(position).getPrice() + "만원");

            // 리스트 아이템을 터치 했을 때 이벤트 발생
            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.i("Combo Test!!",query_age);
                    Log.i("Combo Test!!",query_gender);
                    startActivity(new Intent(getActivity(), DetailViewActivity.class));
                }
            });
//
//            if (position == 0) {
//                lparam.height = 500;
//                Car_rank.setTextSize(20);
//                Car_name.setTextSize(25);
//                Car_company.setTextSize(25);
//                Car_price.setTextSize(25);
//                ll_cardLayout.setLayoutParams(lparam);
//            }
//            else {
//                Car_rank.setTextSize(13);
//                Car_name.setTextSize(18);
//                Car_company.setTextSize(18);
//                Car_price.setTextSize(18);
//                ll_cardLayout.setLayoutParams(lparam);
//            }

            return v;
        }
    }
}

