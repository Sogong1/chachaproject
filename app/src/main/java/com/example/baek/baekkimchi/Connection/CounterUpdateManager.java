package com.example.baek.baekkimchi.Connection;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

/**
 * Created by chasanghyeok on 2015. 6. 13..
 */
public class CounterUpdateManager extends AsyncTask<Void, Void, Void> {
    String car_index;
    String age;
    String gender;
    String url;

    HttpPost request;
    Vector<NameValuePair> list;
    HttpEntity resEntity;
    HttpClient client;
    HttpResponse res;

    public CounterUpdateManager(String car_index, String age, String gender){
        this.car_index = car_index;
        this.age = age;
        this.gender = gender;
    }

    protected void onCancelled(){
        super.onCancelled();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //이곳에서 UI를 변경하면 에러
        //
        url = "http://sogong.besaba.com/system/countAdd.php";
        //url = "http://172.200.153.146/system/countAdd.php";

        request = new HttpPost(url);
        list = new Vector<NameValuePair>();
        //메세지 전송
        sendMessage(car_index, age, gender);

        return null;

    }

    public void sendMessage(String car_index, String age, String gender){
        try {
            list.add(new BasicNameValuePair("car_index", car_index));
            list.add(new BasicNameValuePair("age", age));
            list.add(new BasicNameValuePair("gender", gender));

            Log.i("counter_test_index", car_index);
            Log.i("counter_test_age", age);
            Log.i("counter_test_gender", gender);

            resEntity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            request.setEntity(resEntity);
            client = new DefaultHttpClient();
            res = client.execute(request);
            Log.i("send Request","success!!");
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch(IOException e){

        }
    }
    protected void onPostExecute(Void result){
        //작업 마친후 내용. UI는 여기서 변경할 것,
        super.onPostExecute(result);
//        Log.i("XML result2 : ", result);
//        setXmlList(parseXML(result));
    }

}
