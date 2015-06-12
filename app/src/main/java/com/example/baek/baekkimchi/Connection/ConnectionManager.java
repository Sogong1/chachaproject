package com.example.baek.baekkimchi.Connection;

import android.os.AsyncTask;
import android.util.Log;

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
    private String carNameList[];
    private String modelList[];
    private String priceList[];
    private ArrayList<DataSet> xmlList;

    HttpEntity entityResponse;
    InputStream im;
    BufferedReader reader;

    public ConnectionManager() {
        this.query = "";
    }

    public ConnectionManager(String query) {
        this.query = query;
    }

    @Override
    protected String doInBackground(Void... params) {
        // TODO Auto-generated method stub
        //이곳에서 UI를 변경하면 에러

        url = "http://172.200.153.146/system/blank.php";
        XMLMessage = "";    //final result XML
        message = "";       //temp message buffer

        request = new HttpPost(url);
        list = new Vector<NameValuePair>();
        //메세지 전송
        sendMessage(query);

        //웹서버에서 값 받기
        XMLMessage = getMessage(message);

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
        carNameList = new String[max];
        modelList = new String[max];
        priceList = new String[max];

        ArrayList<DataSet> xmllist = new ArrayList<DataSet>();

        for(int i = 0; i< child.getLength();i++){
            NodeList childList = child.item(i).getChildNodes();
            for(int j = 0;j<childList.getLength();j++){
                String element = childList.item(j).getNodeName();
//                System.out.print(element);
                String text = childList.item(j).getTextContent();
//                System.out.print(text);
//
                if(element.equals("name")){
//                    Log.i("XML text",text);
                    carNameList[i] = text;}
                else if(element.equals("model")){
                    modelList[i] = text;}
                else if(element.equals("price")){
                    priceList[i] = text;
                }
            }
            Log.i("XMLTest "+i+": ", carNameList[i]);
            Log.i("XMLTest "+i+": ", modelList[i]);
            Log.i("XMLTest "+i+": ", priceList[i]);

            xmllist.add(new DataSet(carNameList[i], modelList[i], Integer.parseInt(priceList[i])));

            Log.i("XMLTest_dataSet"+i,xmllist.get(i).getName());
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


    protected void onPostExecute(String result){
        //작업 마친후 내용. UI는 여기서 변경할 것,
        super.onPostExecute(result);
//        Log.i("XML result2 : ", result);
//        setXmlList(parseXML(result));
    }

}