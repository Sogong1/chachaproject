package com.example.baek.baekkimchi.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.baek.baekkimchi.Connection.ConnectionManager;
import com.example.baek.baekkimchi.Connection.CounterUpdateManager;
import com.example.baek.baekkimchi.Connection.DownloadImagesTask;
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
    private DownloadImagesTask mDownloadImagesTask;
    private ConnectionManager mConnectionManager;

    private TextView age_check;
    private TextView gender_check;
    private TextView query_check;
    private TextView xml_check;

    private int USERLIST_REQUEST = 1000;
    private int RECOMMENDLIST_REQUEST = 2000;
    private ArrayList<DataSet> temp;
    private ArrayList<String> urlList = new ArrayList<String>();
    private ArrayList<ImageView> imgViewList = new ArrayList<ImageView>();

    private ImageView testImg1;
    private ImageView testImg2;
    private ImageView testImg3;
    private ImageView testImg4;
    private ImageView testImg5;
    private ImageView testImg6;
    private ImageView testImg7;
    private ImageView testImg8;
    private ImageView testImg9;
    private ImageView testImg10;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        age = bundle.getString("age");
        gender = bundle.getString("gender");
        query = bundle.getString("query");
        mConnectionManager = new ConnectionManager(getActivity(), query, USERLIST_REQUEST);

        LinearLayout comboBox = (LinearLayout) getActivity().findViewById(R.id.recommend_combo);
        comboBox.setVisibility(View.GONE);
//        if(mConnectionManager.isCancelled() == false)
//            mConnectionManager.cancel(true);
        mConnectionManager.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        Log.i("send_test_userlist_age", age);
        Log.i("send_test_userlist_gen", gender);

        initTestView();
        initTestImgView();

    }

    public void initTestImgView(){
        testImg1 = (ImageView)getActivity().findViewById(R.id.image_test1);
        testImg2 = (ImageView)getActivity().findViewById(R.id.image_test2);
        testImg3 = (ImageView)getActivity().findViewById(R.id.image_test3);
        testImg4 = (ImageView)getActivity().findViewById(R.id.image_test4);
        testImg5 = (ImageView)getActivity().findViewById(R.id.image_test5);
        testImg6 = (ImageView)getActivity().findViewById(R.id.image_test6);
        testImg7 = (ImageView)getActivity().findViewById(R.id.image_test7);
        testImg8 = (ImageView)getActivity().findViewById(R.id.image_test8);
        testImg9 = (ImageView)getActivity().findViewById(R.id.image_test9);
        testImg10 = (ImageView)getActivity().findViewById(R.id.image_test10);

        imgViewList.add(testImg1);
        imgViewList.add(testImg2);
        imgViewList.add(testImg3);
        imgViewList.add(testImg4);
        imgViewList.add(testImg5);
        imgViewList.add(testImg6);
        imgViewList.add(testImg7);
        imgViewList.add(testImg8);
        imgViewList.add(testImg9);
        imgViewList.add(testImg10);
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

    public void initUrlList(ArrayList<DataSet> temp, ArrayList<ImageView> imgViewList){
        String url = "http://sogong.besaba.com/s_img/";
        for(int i = 0; i< temp.size(); i++)
            urlList.add(url+temp.get(i).getImg());
        for(int i = 0; i< temp.size(); i++)
            imgViewList.get(i).setTag(urlList.get(i));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_ranklist, container, false);

        mListView = (ListView) v.findViewById(R.id.rank_list);
        temp = mConnectionManager.getXmlList();
//        setBitmap(getBitmap(temp));
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                temp = mConnectionManager.getXmlList();
                mDownloadImagesTask = new DownloadImagesTask();
                initUrlList(temp, imgViewList);
                for(int i = 0; i< mConnectionManager.getItemSize(); i++) {
                    mDownloadImagesTask = new DownloadImagesTask(getActivity());
                    mDownloadImagesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, imgViewList.get(i));
                }

                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new CustomAdapter(getActivity(), R.layout.item_card, temp, imgViewList);
                        mListView.setAdapter(mAdapter);
                        if(temp.size() != 0)
                            setTestView(age, gender, query, temp.get(0).getXml());
                    }
                }, 3000);
            }
        }, 3000);
        return v;
    }

    public void setImgFromTestView(ImageView from, ImageView to){
        Drawable d = from.getDrawable();
        Bitmap bp = ((BitmapDrawable)d).getBitmap();
        to.setImageBitmap(bp);
    }

    public void onStop(){
        mConnectionManager.cancel(true);
        super.onStop();
    }

    private class CustomAdapter extends ArrayAdapter<DataSet> {

        private ArrayList<DataSet> mDataset;
        private ArrayList<ImageView> mImageView;

        public CustomAdapter(Context context, int textViewResourceId, ArrayList<DataSet> items, ArrayList<ImageView> mImageView) {
            super(context, textViewResourceId, items);
            this.mDataset = items;
            this.mImageView = mImageView;
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

            ImageView cardImg = (ImageView) v.findViewById(R.id.car_image);
            setImgFromTestView(mImageView.get(position), cardImg);

            DataSet clickedDataSet = mDataset.get(position);
            final Intent intent = new Intent(getActivity(), DetailViewActivity.class);
            intent.putExtra("mDataset",clickedDataSet);
            final int temp_position = position;

            // 리스트 아이템을 터치 했을 때 이벤트 발생
            v.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.i("index_test", mDataset.get(temp_position).getIndex());
                    Log.i("send_test_age", age);
                    Log.i("send_test_gender", gender);
                    new CounterUpdateManager(mDataset.get(temp_position).getIndex(), age, gender).execute();
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

