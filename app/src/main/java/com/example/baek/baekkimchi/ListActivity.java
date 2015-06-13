package com.example.baek.baekkimchi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private Button bt_oneFragment, bt_twoFragment;

    private int age, cost;
    private String gender;
    private String query;
    private boolean isSkip;
    private ArrayList<String> selectedFilter;

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


        selectedFilter = new ArrayList<>();

        query = "select * from car where price >= \""+cost+"\"-100 or price <= \""+cost+"\"+100 LIMIT 10";

        bt_oneFragment = (Button) findViewById(R.id.bt_oneFragment);
        bt_oneFragment.setOnClickListener(this);
        bt_twoFragment = (Button) findViewById(R.id.bt_twoFragment);
        bt_twoFragment.setOnClickListener(this);

        bt_filter = (Button) findViewById(R.id.btn_filter);
        bt_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlertDialog();
            }
        });

        bt_return = (Button) findViewById(R.id.btn_return);
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));
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
                bt_oneFragment.setBackgroundResource(R.drawable.title_mylist_select);
                bt_twoFragment.setBackgroundResource(R.drawable.title_rec_unselc);

                Bundle bundle1 = new Bundle();
                bundle1.putString("age", age+"");
                bundle1.putString("gender", gender);
                bundle1.putString("query", query);
                newFragment = new Userlist();
                newFragment.setArguments(bundle1);
                break;
            case FRAGMENT_TWO:
                bt_oneFragment.setBackgroundResource(R.drawable.title_mylist_unselc);
                bt_twoFragment.setBackgroundResource(R.drawable.title_rec_select);

                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("isSkip", true);
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

    public void setAlertDialog(){
        final CharSequence[] itemsMap = {"회사", "이름", "모델", "타입", "엔진", "공급방식", "배기량", "연료", "연비", "탑승인원", "구동방식", "변속기", "가격", "최대토큰", "최고출력"};
        final CharSequence[] items = {"company_index", "car_name", "car_model", "type", "engene_type", "supply_method", "displacement", "fuel_type", "fuel_economy", "riding_personnal", "drive_type", "mission", "price", "max_token", "max_output"};
        final ArrayList<Integer> selectedItemIndexList = new ArrayList<Integer>();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);     // 여기서 this는 Activity의 this

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle("필터를 선택하세요")        // 제목 설정
                .setMultiChoiceItems(itemsMap, new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked)
                            selectedItemIndexList.add(which);
                        else if (selectedItemIndexList.contains(which)) {
                            selectedItemIndexList.remove(Integer.valueOf(which));
                        }
                    }
                })
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    // 확인 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton) {
                        StringBuilder stringBuilder = new StringBuilder();
                        if (selectedItemIndexList.size() != 0)
                            for (int i = 0; i < selectedItemIndexList.size(); i++) {
                                String filter = items[selectedItemIndexList.get(i)].toString();
                                selectedFilter.add(filter);
                                stringBuilder = stringBuilder.append(" "+filter);
                            }
                        Toast.makeText(getApplicationContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    // 취소 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림장 띄우기
    }
}
