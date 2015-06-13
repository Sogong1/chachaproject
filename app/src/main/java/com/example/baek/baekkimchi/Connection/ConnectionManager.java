package com.example.baek.baekkimchi.Connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.baek.baekkimchi.dataset.DataSet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Baek on 2015-06-09.
 */
public class ConnectionManager extends AsyncTask<Void, Void, String> {
    String query;
    String url;
    String XMLMessage;
    String message;
    HttpPost request;
    Vector<NameValuePair> list;
    HttpEntity resEntity;
    HttpClient client;
    HttpResponse res;

    private String age;
    private String gender;
    private String cost;

    private String index[];
    private String carNameList[];
    private String modelList[];
    private String priceList[];
    private String type[];
    private String engine_type[];
    private String supply_method[];
    private String displacement[];
    private String fuel_type[];
    private String fuel_economy[];
    private String riding_personal[];
    private String drive_type[];
    private String mission[];
    private String max_token[];
    private String max_output[];

    private ArrayList<DataSet> xmlList;
    private int state;
    private int USERLIST_REQUEST = 1000;
    private int RECOMMENDLIST_REQUEST = 2000;

    HttpEntity entityResponse;
    InputStream im;
    BufferedReader reader;

    private ProgressDialog dialog;
    private Context mContext;

    public ConnectionManager() {
        this.query = "";
    }

    public ConnectionManager(Context context, String query, int state) {
        this.state = state;
        this.query = query;
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(mContext);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("로딩중입니다..");
        Log.i("tnstj : ", "111");
        // show dialog
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        // TODO Auto-generated method stub
        //이곳에서 UI를 변경하면 에러
        if(state == USERLIST_REQUEST)
            url = "http://172.200.153.146/system/get_list.php";
        else if(state == RECOMMENDLIST_REQUEST)
            url = "http://172.200.153.146/system/get_recommend.php";
        else
            url = "http://172.200.153.146/system/get_list.php";

        XMLMessage = "";    //final result XML
        message = "";       //temp message buffer

        request = new HttpPost(url);
        list = new Vector<NameValuePair>();
        //메세지 전송
        sendMessage(query);

        //웹서버에서 값 받기
        XMLMessage = getMessage(message);

        Log.i("tnstj : ", "222");
        try {
            //asyncDialog.setProgress(i * 30);
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("tnstj : ", "333");

        return XMLMessage;
    }

    public void sendMessage(String query){
        try {
            list.add(new BasicNameValuePair("query", query));
            resEntity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
            request.setEntity(resEntity);
            client = new DefaultHttpClient();
            res = client.execute(request);
        }catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IOException e){

        }
    }

    public String getMessage(String message){
        try {
            entityResponse = res.getEntity();
            im = entityResponse.getContent();
            reader = new BufferedReader(new InputStreamReader(im, HTTP.UTF_8));
            String tmp = "";
            //한줄 한줄 읽기
            while((tmp = reader.readLine()) != null){
                if(tmp!=null){
                    message += tmp;
                }
            }
            im.close();
            Log.i("XML result1 : ",message);

            setXmlList(parseXML(message));

            return message;
        }catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IOException e){

        }
        return null; //오류시 error
    }

    public ArrayList<DataSet> parseXML(String xml){
        //get XML and parse it. After that draw list.

        String temp = xml.substring(0,xml.length());

//        System.out.print(temp);
        StringReader sr = new StringReader(temp);
        InputSource is = new InputSource(sr);

        NodeList result = null;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);
            result = document.getElementsByTagName("list");
        }catch(Exception e){
            e.printStackTrace();
        }

        NodeList child = result.item(0).getChildNodes();
//        System.out.println(child.getLength());
        int max = child.getLength();
        initList(max);

        ArrayList<DataSet> xmllist = new ArrayList<DataSet>();

        for(int i = 0; i< child.getLength();i++){
            NodeList childList = child.item(i).getChildNodes();
            for(int j = 0;j<childList.getLength();j++){
                String element = childList.item(j).getNodeName();
//                System.out.print(element);
                String text = childList.item(j).getTextContent();
                setValue(element, i, text);
            }

            xmllist.add(new DataSet(index[i],carNameList[i], modelList[i], Integer.parseInt(priceList[i]), type[i]
                    , engine_type[i], supply_method[i], displacement[i], fuel_type[i], fuel_economy[i]
                    , riding_personal[i], drive_type[i], mission[i], max_token[i], max_output[i], temp));

            Log.i("XMLTest_dataSet"+i,xmllist.get(i).getIndex());
        }
//
//        xmllist[0] = carNameList;
//        xmllist[1] = modelList;
//        xmllist[2] = priceList;

        return xmllist;
    }

    public void setXmlList(ArrayList<DataSet> list){
        xmlList = list;
    }

    public ArrayList<DataSet> getXmlList() {
        return xmlList;
    }

    public void initList(int n){
        index = new String[n];
        carNameList = new String[n];
        modelList = new String[n];
        priceList = new String[n];
        type = new String[n];
        engine_type = new String[n];
        supply_method = new String[n];
        displacement = new String[n];
        fuel_type = new String[n];
        fuel_economy = new String[n];
        riding_personal = new String[n];
        drive_type = new String[n];
        mission = new String[n];
        max_token = new String[n];
        max_output = new String[n];
    }

    public void setValue(String element, int position, String text){
        if(element.equals("car_index")) index[position] = text;
        else if(element.equals("name")) carNameList[position] = text;
        else if(element.equals("model")) modelList[position] = text;
        else if(element.equals("price")) priceList[position] = text;
        else if(element.equals("type")) type[position] = text;
        else if(element.equals("engine_type")) engine_type[position] = text;
        else if(element.equals("supply_method")) supply_method[position] = text;
        else if(element.equals("displacement")) displacement[position] = text;
        else if(element.equals("fuel_type")) fuel_type[position] = text;
        else if(element.equals("fuel_economy")) fuel_economy[position] = text;
        else if(element.equals("riding_personnal")) riding_personal[position] = text;
        else if(element.equals("drive_type")) drive_type[position] = text;
        else if(element.equals("mission")) mission[position] = text;
        else if(element.equals("max_token")) max_token[position] = text;
        else if(element.equals("max_output")) max_output[position] = text;

    }


    protected void onPostExecute(String result){
        //작업 마친후 내용. UI는 여기서 변경할 것,
        Log.i("tnstj : ", "444");
        dialog.dismiss();
        super.onPostExecute(result);
//        Log.i("XML result2 : ", result);
//        setXmlList(parseXML(result));
    }

}