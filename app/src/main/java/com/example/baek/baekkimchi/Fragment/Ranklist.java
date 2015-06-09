package com.example.baek.baekkimchi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    private Button bt_FragmentOne;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        isSkip = bundle.getBoolean("isSkip");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_ranklist, container, false);
        View tempView = inflater.inflate(R.layout.activity_list, container, false);

        bt_FragmentOne = (Button) tempView.findViewById(R.id.bt_oneFragment);
        bt_FragmentOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
                getActivity().finish();
            }
        });

        mListView = (ListView) v.findViewById(R.id.rank_list);

        mAdapter = new CustomAdapter();
        // use a linear layout manager

        mListView.setAdapter(mAdapter);

        // specify an adapter
        mAdapter.add(new DataSet("A", "A", 1234));
        mAdapter.add(new DataSet("B", "B", 345));
        mAdapter.add(new DataSet("C", "C", 678));

        return v;
    }

    public class CustomAdapter extends BaseAdapter {


        // 문자열을 보관 할 ArrayList
        private ArrayList<DataSet> mDataset;

        // 생성자
        public CustomAdapter() {
            mDataset = new ArrayList<DataSet>();
        }

        // 현재 아이템의 수를 리턴
        @Override
        public int getCount() {
            return mDataset.size();
        }

        // 현재 아이템의 오브젝트를 리턴, Object를 상황에 맞게 변경하거나 리턴받은 오브젝트를 캐스팅해서 사용
        @Override
        public Object getItem(int position) {
            return mDataset.get(position);
        }

        // 아이템 position의 ID 값 리턴
        @Override
        public long getItemId(int position) {
            return position;
        }

        // 출력 될 아이템 관리
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            // 리스트가 길어지면서 현재 화면에 보이지 않는 아이템은 converView가 null인 상태로 들어 옴
            if ( convertView == null ) {
                // view가 null일 경우 커스텀 레이아웃을 얻어 옴
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_card, parent, false);

                LinearLayout ll_cardLayout = (LinearLayout) convertView.findViewById(R.id.ll_cardLayout);
                LinearLayout.LayoutParams lparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                GradientDrawable shape =  new GradientDrawable();
                shape.setCornerRadius(30);

                if (position > 0) {
                    lparam.height = 300;
                    ll_cardLayout.setLayoutParams(lparam);
                } else {
                    ll_cardLayout.setBackgroundColor(Color.parseColor("#FF7E7E"));
                }

                // TextView에 현재 position의 문자열 추가
                TextView Car_rank = (TextView) convertView.findViewById(R.id.Car_rank);
                Car_rank.setText(position+1+"");

                TextView Car_name = (TextView) convertView.findViewById(R.id.Car_name);
                Car_name.setText(mDataset.get(position).getName());

                TextView Car_company = (TextView) convertView.findViewById(R.id.Car_company);
                Car_company.setText(mDataset.get(position).getCompany());

                TextView Car_price = (TextView) convertView.findViewById(R.id.Car_price);
                Car_price.setText(mDataset.get(position).getPrice()+"");

                // 리스트 아이템을 터치 했을 때 이벤트 발생
                convertView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), DetailViewActivity.class));
                    }
                });

            }

            return convertView;
        }

        // 외부에서 아이템 추가 요청 시 사용
        public void add(DataSet data) {
            mDataset.add(data);
        }

        // 외부에서 아이템 삭제 요청 시 사용
        public void remove(int position) {
            mDataset.remove(position);
        }

    }
}

