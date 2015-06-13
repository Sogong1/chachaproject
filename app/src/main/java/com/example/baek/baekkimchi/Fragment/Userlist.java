package com.example.baek.baekkimchi.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baek.baekkimchi.Connection.ConnectionManager;
import com.example.baek.baekkimchi.DetailViewActivity;
import com.example.baek.baekkimchi.R;

import java.util.ArrayList;

import com.example.baek.baekkimchi.dataset.DataSet;


public class Userlist extends Fragment {

    private ListView mListView;
    private CustomAdapter mAdapter;
    private String age;
    private String gender;
    private String query;
    private Bundle bundle;
    private ConnectionManager mConnectionManager;

    private TextView age_check;
    private TextView gender_check;
    private TextView query_check;
    private TextView xml_check;

    private int USERLIST_REQUEST = 1000;
    private int RECOMMENDLIST_REQUEST = 2000;
    private ArrayList<DataSet> temp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        age = bundle.getString("age");
        gender = bundle.getString("gender");
        query = bundle.getString("query");
        mConnectionManager = new ConnectionManager(getActivity(), query, USERLIST_REQUEST);
        LinearLayout comboBox = (LinearLayout) getActivity().findViewById(R.id.recommend_combo);
        comboBox.setVisibility(View.GONE);

        mConnectionManager.execute();

        initTestView();


//        Log.i("tnstj :", "5");
////        temp = mConnectionManager.getXmlList();
//        Log.i("tnstj :", "6");
//        if (temp == null)
//            Log.i("XML TEST1 !!", "It's null!!");
//        else
//            Log.i("XML TEST1 !!", "성공");
    }

    public void initTestView(){
        age_check = (TextView)getActivity().findViewById(R.id.age_check);
        gender_check = (TextView)getActivity().findViewById(R.id.gender_check);
        query_check = (TextView)getActivity().findViewById(R.id.query_check);
        xml_check = (TextView)getActivity().findViewById(R.id.xml_check);
    }
    public void setTestView(String age, String gender, String query, String xml){
        age_check.setText(age);
        gender_check.setText(gender);
        query_check.setText(query);
        xml_check.setText(xml);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_ranklist, container, false);

        mListView = (ListView) v.findViewById(R.id.rank_list);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                temp = mConnectionManager.getXmlList();
                mAdapter = new CustomAdapter(getActivity(), R.layout.item_card, temp);
                mListView.setAdapter(mAdapter);
            }
        }, 2000);

//        setTestView(age, gender, query, temp.get(0).getXml());

        return v;
    }

    private class CustomAdapter extends ArrayAdapter<DataSet> {

        private ArrayList<DataSet> mDataset;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<DataSet> items) {
            super(context, textViewResourceId, items);
            this.mDataset = items;
        }

        public int getItemCount() {
            return mDataset.size();
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
            Car_company.setText(mDataset.get(position).getModel());

            TextView Car_price = (TextView) v.findViewById(R.id.Car_price);
            Car_price.setText(mDataset.get(position).getPrice() + "만원");

            DataSet clickedDataSet = mDataset.get(position);

            final Intent intent = new Intent(getActivity(), DetailViewActivity.class);
            intent.putExtra("mDataset",clickedDataSet);

            // 리스트 아이템을 터치 했을 때 이벤트 발생
            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
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

