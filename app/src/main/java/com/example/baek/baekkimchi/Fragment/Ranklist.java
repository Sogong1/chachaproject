package com.example.baek.baekkimchi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.baek.baekkimchi.Connection.ConnectionManager;
import com.example.baek.baekkimchi.DetailViewActivity;
import com.example.baek.baekkimchi.MainActivity;
import com.example.baek.baekkimchi.R;

import java.util.ArrayList;

import com.example.baek.baekkimchi.dataset.DataSet;


public class Ranklist extends Fragment {

    private ListView mListView;
    private CustomAdapter mAdapter;
    private Bundle bundle;
    private boolean isSkip;
    private ArrayList<DataSet> DatasetList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        isSkip = bundle.getBoolean("isSkip");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_ranklist, container, false);

        mListView = (ListView) v.findViewById(R.id.rank_list);

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

        mAdapter = new CustomAdapter(getActivity(), R.layout.item_card, DatasetList);
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
//                ll_cardLayout.setBackgroundColor(Color.parseColor("#FAED7D"));
//            }
//            else {
//                Car_rank.setTextSize(13);
//                Car_name.setTextSize(18);
//                Car_company.setTextSize(18);
//                Car_price.setTextSize(18);
//                ll_cardLayout.setLayoutParams(lparam);
//                ll_cardLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            }

            return v;
        }
    }
}

