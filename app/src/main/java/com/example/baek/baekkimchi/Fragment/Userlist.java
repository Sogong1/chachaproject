package com.example.baek.baekkimchi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

import com.example.baek.baekkimchi.Connection.ConnectionManager;
import com.example.baek.baekkimchi.DetailViewActivity;
import com.example.baek.baekkimchi.R;

import java.util.ArrayList;

import com.example.baek.baekkimchi.dataset.DataSet;


public class Userlist extends Fragment {

    private ListView mListView;
    private CustomAdapter mAdapter;
    private String query;
    private Bundle bundle;
    private ConnectionManager mConnectionManager;
    private ArrayList<DataSet> temp;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        query = bundle.getString("query");
        mConnectionManager = new ConnectionManager(query);
        LinearLayout comboBox = (LinearLayout)getActivity().findViewById(R.id.recommend_combo);
        comboBox.setVisibility(View.GONE);

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_ranklist, container, false);

        mListView = (ListView) v.findViewById(R.id.rank_list);

        mAdapter = new CustomAdapter(getActivity(), R.layout.item_card, temp);
        // use a linear layout manager

        mListView.setAdapter(mAdapter);

        if (temp == null)
            Log.i("XML TEST2!!", "It's null!!");
        else
            Log.i("XML TEST2!!", "성공");

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

//    public class CustomAdapter extends BaseAdapter {
//
//
//        // 문자열을 보관 할 ArrayList
//        private ArrayList<DataSet> mDataset;
//
//        // 생성자
//        public CustomAdapter(ArrayList<DataSet> dataList) {
//            mDataset = dataList;
//
//        }
//        // 현재 아이템의 수를 리턴
//        @Override
//        public int getCount() {
//            return mDataset.size();
//        }
//
//        // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
//        @Override
//        public Object getItem(int position) {
//            return mDataset.get(position);
//        }
//
//        // 아이템 position의 ID 값 리턴
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        // 출력 될 아이템 관리
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            Log.i("XML Test_size: ",mDataset.size()+"");
//
//            final int pos = position;
//            final Context context = parent.getContext();
//            int listSize = mDataset.size();
//
//            // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
//            if ( convertView == null ) {
//                // view가 null일 경우 커스텀 레이아웃을 얻어 옴
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(R.layout.item_card, parent, false);
//
//                LinearLayout ll_cardLayout = (LinearLayout) convertView.findViewById(R.id.ll_cardLayout);
//                LinearLayout.LayoutParams lparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
//
//                // TextView에 현재 position의 문자열 추가
//                TextView Car_rank = (TextView) convertView.findViewById(R.id.Car_rank);
//                Car_rank.setText(pos + 1 + "");
//
//                TextView Car_name = (TextView) convertView.findViewById(R.id.Car_name);
//                TextView Car_company = (TextView) convertView.findViewById(R.id.Car_company);
//                TextView Car_price = (TextView) convertView.findViewById(R.id.Car_price);
//                if(position == 4)
//                    Log.i("getViewTest: position",position+"");
//
//                for(int i = 0; i<listSize; i++){
//                    if(position == i){
//                        Car_name.setText(mDataset.get(position).getName());
//                        Car_company.setText(mDataset.get(position).getCompany());
//                        Car_price.setText(mDataset.get(position).getPrice()+"만원");
//
//                        Log.i("getViewTest: int i",i+"");
//                        Log.i("getViewTest: position",position+"");
//                        Log.i("getViewTest: Car_name",mDataset.get(position).getName());
//                        Log.i("getViewTest: Car_com", mDataset.get(position).getCompany());
//                        Log.i("getViewTest: Car_price",mDataset.get(position).getPrice()+"");
//                    }
//
//                }Log.i("getViewTest","finish!");
//
//
//                // 리스트 아이템을 터치 했을 때 이벤트 발생
//                convertView.setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(getActivity(), DetailViewActivity.class));
//                    }
//                });
//
//                if (position == 0) {
//                    lparam.height = 500;
//                    Car_rank.setTextSize(20);
//                    Car_name.setTextSize(25);
//                    Car_company.setTextSize(25);
//                    Car_price.setTextSize(25);
//                    ll_cardLayout.setLayoutParams(lparam);
//                    ll_cardLayout.setBackgroundColor(Color.parseColor("#FAED7D"));
//                }
//            }
//
//            return convertView;
//        }
//
//        // 외부에서 아이템 추가 요청 시 사용
//        public void add(DataSet data) {
//            mDataset.add(data);
//        }
//
//        // 외부에서 아이템 삭제 요청 시 사용
//        public void remove(int position) {
//            mDataset.remove(position);
//        }
//
//    }
//}

