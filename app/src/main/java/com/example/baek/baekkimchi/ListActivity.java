package com.example.baek.baekkimchi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.example.baek.baekkimchi.Fragment.Ranklist;
import com.example.baek.baekkimchi.Fragment.Userlist;


public class ListActivity extends FragmentActivity implements View.OnClickListener {

    final String TAG = "ListActivity";

    int mCurrentFragmentIndex;
    public final static int FRAGMENT_ONE = 0;
    public final static int FRAGMENT_TWO = 1;

    private Button bt_return, bt_filter;
    private Button bt_userList, bt_recoList;

    private int age, cost;
    private String gender;
    private String query, where;
    private boolean isSkip, isRecommend;
    private CharSequence[] typeItem = {"소형", "중형", "준중","대", "스포츠", "경형"};
    private CharSequence[] missionItem = {"자동", "수동"};
    private CharSequence[] fuelTypeItem = {"가솔린", "디젤"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        if(intent.getExtras() == null){
            isSkip = false;
            age = 20;
            gender = "남자";
            cost = 2100;
        }
        else {
            isSkip = intent.getExtras().getBoolean("isSkip");
            age = intent.getExtras().getInt("age");
            gender = intent.getExtras().getString("gender");
            cost = intent.getExtras().getInt("cost");
        }

        query = "select car_index, company_name, car_name, car_model, type, engene_type, supply_method"
                +", displacement, fuel_type, fuel_economy, riding_personnal, drive_type"
                +", mission, price, max_token, max_output, img " +
                "from car c NATURAL JOIN company where price >= \""+cost+"\"-100 and price <= \""+cost+"\"+100 " +
                "order by c.price ASC LIMIT 10";


        bt_userList = (Button) findViewById(R.id.bt_oneFragment);
        bt_userList.setOnClickListener(this);
        bt_recoList = (Button) findViewById(R.id.bt_twoFragment);
        bt_recoList.setOnClickListener(this);

        bt_filter = (Button) findViewById(R.id.btn_filter);
        bt_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog filterDialog = new CustomDialog(ListActivity.this, "필터 선택");
                filterDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
                filterDialog.show();
            }
        });

        bt_return = (Button) findViewById(R.id.btn_return);
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("callAgain", true);
                startActivity(intent);
                finish();
            }
        });

   

        if (isSkip) {
            mCurrentFragmentIndex = FRAGMENT_TWO;
        }
        else {
            mCurrentFragmentIndex = FRAGMENT_ONE;
        }
        fragmentReplace(mCurrentFragmentIndex);
    }

    public void fragmentReplace(int reqNewFragmentIndex) {

        Fragment newFragment = null;

        Log.d(TAG, "fragmentReplace " + reqNewFragmentIndex);

        newFragment = getFragment(reqNewFragmentIndex);

        // replace fragment
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.ll_fragment, newFragment);

        // Commit the transaction
        transaction.commit();
    }

    private Fragment getFragment(int idx) {
        Fragment newFragment = null;

        switch (idx) {
            case FRAGMENT_ONE:
                bt_userList.setBackgroundResource(R.drawable.title_mylist_select);
                bt_recoList.setBackgroundResource(R.drawable.title_rec_unselc);

                isRecommend = false;

                Bundle bundle1 = new Bundle();

                bundle1.putString("age", age + "");
                bundle1.putString("gender", gender);
                bundle1.putString("query", query);

                Log.i("send_test_listA_age", age+"");
                Log.i("send_test_listA_gen", gender);

                newFragment = new Userlist();
                newFragment.setArguments(bundle1);
                break;
            case FRAGMENT_TWO:
                bt_userList.setBackgroundResource(R.drawable.title_mylist_unselc);
                bt_recoList.setBackgroundResource(R.drawable.title_rec_select);

                isRecommend = true;

                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("isSkip", true);
                bundle2.putString("where", where);
                newFragment = new Ranklist();
                newFragment.setArguments(bundle2);
                break;
            default:
                Log.d(TAG, "Unhandle case");
                break;
        }

        return newFragment;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_oneFragment:
                if(isSkip) {
                    startActivity(new Intent(ListActivity.this, MainActivity.class));
                    finish();
                 }
                else {
                    mCurrentFragmentIndex = FRAGMENT_ONE;
                    fragmentReplace(mCurrentFragmentIndex);
                }
                break;
            case R.id.bt_twoFragment:
                mCurrentFragmentIndex = FRAGMENT_TWO;
                fragmentReplace(mCurrentFragmentIndex);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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

    class CustomDialog extends Dialog implements View.OnClickListener {
        private RadioGroup type_group, mission_group, fuel_group;
        private TextView type_text, mission_text, fuel_text;
        private Button okButton;
        private Button cancelButton;
        private Context mContext;
        private String mTitle;
        private TextView mTitleView, filter_text;
        private String selectedGroup, selectedItem;


        public CustomDialog(Context context, String title) {
            super(context , android.R.style.Theme_DeviceDefault_Dialog);
            this.mTitle = title;
            this.mContext = context;
            /** 'Window.FEATURE_NO_TITLE' - Used to hide the title */
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.customdialog);

            mTitleView = (TextView) findViewById(R.id.title_text);
            mTitleView.setText(mTitle);

            filter_text = (TextView) findViewById(R.id.filter_text);

            type_text = (TextView) findViewById(R.id.type_text);
            type_text.setOnClickListener(this);
            mission_text = (TextView) findViewById(R.id.mission_text);
            mission_text.setOnClickListener(this);
            fuel_text = (TextView) findViewById(R.id.fuel_text);
            fuel_text.setOnClickListener(this);

            type_group = (RadioGroup) findViewById(R.id.type_group);
            type_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    if(checkedId > -1) {
                        RadioButton tmp = ((RadioButton) findViewById(checkedId));
                        filter_text.setText("필터 : " + tmp.getText());
                        selectedItem = tmp.getText().toString();
                    }
                }
            });
            mission_group = (RadioGroup) findViewById(R.id.mission_group);
            mission_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    if (checkedId > -1) {
                        RadioButton tmp = ((RadioButton) findViewById(checkedId));
                        filter_text.setText("필터 : " + tmp.getText());
                        selectedItem = tmp.getText().toString();
                    }
                }
            });
            fuel_group = (RadioGroup) findViewById(R.id.fuel_group);
            fuel_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    if (checkedId > -1) {
                        RadioButton tmp = ((RadioButton) findViewById(checkedId));
                        filter_text.setText("필터 : " + tmp.getText());
                        selectedItem = tmp.getText().toString();
                    }
                }
            });
            okButton = (Button)findViewById(R.id.button1);
            cancelButton = (Button)findViewById(R.id.button2);

            okButton.setOnClickListener(this);
            cancelButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v == type_text) {
                type_group.setVisibility(RadioGroup.VISIBLE);
                mission_group.setVisibility(RadioGroup.GONE);
                fuel_group.setVisibility(RadioGroup.GONE);
                mission_group.clearCheck();
                fuel_group.clearCheck();
                selectedGroup = "type";

            }else if(v==mission_text) {
                type_group.setVisibility(RadioGroup.GONE);
                mission_group.setVisibility(RadioGroup.VISIBLE);
                fuel_group.setVisibility(RadioGroup.GONE);
                type_group.clearCheck();
                fuel_group.clearCheck();
                selectedGroup = "mission";

            }else if(v==fuel_text) {
                type_group.setVisibility(RadioGroup.GONE);
                mission_group.setVisibility(RadioGroup.GONE);
                fuel_group.setVisibility(RadioGroup.VISIBLE);
                type_group.clearCheck();
                mission_group.clearCheck();
                selectedGroup = "fuel_type";

            }else if(v == okButton) {
                if (isRecommend) {
                    mCurrentFragmentIndex = FRAGMENT_TWO;
                    where = "where " + selectedGroup + " = \"" + selectedItem + "\" ";
                }
                else {
                    mCurrentFragmentIndex = FRAGMENT_ONE;
                    query = "select * from car where price >= \"" + cost + "\"-100 and price <= \"" + cost + "\"+100 and " + selectedGroup + " = \"" + selectedItem + "\" LIMIT 10";
                }

                fragmentReplace(mCurrentFragmentIndex);

                dismiss();
            } else if(v == cancelButton) {
                dismiss();
            }
        }
    }

}
