package com.example.baek.baekkimchi;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.baek.baekkimchi.Database.DBManagerHandler;

import java.util.Timer;
import java.util.TimerTask;


public class IntroActivity extends Activity {
    private Timer timer;
    private DBManagerHandler handler;

    private TextView dbCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        handler = new DBManagerHandler(getApplicationContext());
        dbCheck = (TextView)findViewById(R.id.db_check);

        // Animation 객체를 선언한 후 타겟 위젯에서 startAnimation
        // 이미지들이 담긴 컨테이너
        final ViewFlipper vf = (ViewFlipper) findViewById(R.id.view_flipper);

        // 타이머를 이용하여 0.5초 마다 다음 이미지를 보여줌
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // UI를 손대기 위해서는 runOnUiThread를 사용해야 함
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 다음 이미지 표시
                        vf.showNext();
                    }
                });
            }
        }, 500, 500);

        Thread thread = new Thread(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {

                    sleep(3000);

                    int age, cost;
                    String gender;
                    String query = "SELECT * FROM inform_table";
                    Cursor cursor = handler.selectInform(query);
                    if (cursor.moveToNext()) {
                        Intent intent = new Intent(IntroActivity.this, ListActivity.class);
                        intent.putExtra("isSkip", false);
                        intent.putExtra("age", cursor.getInt(cursor.getColumnIndex("age")));
                        intent.putExtra("gender", cursor.getString(cursor.getColumnIndex("gender")));
                        intent.putExtra("cost", cursor.getInt(cursor.getColumnIndex("cost")));

                        startActivity(intent);
                        finish();
                    } else {
                        startActivity(new Intent(IntroActivity.this, MainActivity.class));
                        finish();
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
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
}