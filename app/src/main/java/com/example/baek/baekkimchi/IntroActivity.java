package com.example.baek.baekkimchi;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;


public class IntroActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Animation 객체를 선언한 후 타겟 위젯에서 startAnimation
        ImageView mAnimTarget = (ImageView)findViewById(R.id.img_intro);
        Animation anim = new TranslateAnimation(0, 200, 0, 0);
        anim.setDuration(1000);
        mAnimTarget.startAnimation(anim);

        // Animation을 정의한 xml파일을 로드한 후
        Animation flowAnimation;
        flowAnimation = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.translate);
        mAnimTarget.startAnimation(flowAnimation);

        Thread thread = new Thread(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    sleep(3000);
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        };

        thread.start();
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
