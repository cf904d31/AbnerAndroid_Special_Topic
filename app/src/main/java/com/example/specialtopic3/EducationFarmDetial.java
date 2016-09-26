package com.example.specialtopic3;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class EducationFarmDetial extends AppCompatActivity {
    private TextView education_farm_title,education_farm_content,education_farm_service;
    private setURL setURL;
    private String url = "http://data.coa.gov.tw/Service/OpenData/EzgoOutdoorEdu.aspx";
    private String data;
    private ConnectivityManager mgr;
    private WebView educationFarm_WebView;
    private float lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_farm_detial);

        education_farm_title = (TextView)findViewById(R.id.education_farm_title);
        education_farm_content = (TextView)findViewById(R.id.education_farm_content);
        educationFarm_WebView = (WebView)findViewById(R.id.educationFarm_WebView);
        education_farm_service = (TextView)findViewById(R.id.education_farm_service);

        setURL = new setURL();
        initWebview();


        mgr = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = mgr.getActiveNetworkInfo();
        if (info != null && info.isConnected()){
            setURL.URL(url);

            try {
                Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
                while (ifs.hasMoreElements()){
                    NetworkInterface ip = ifs.nextElement();
                    Enumeration<InetAddress> ips = ip.getInetAddresses();
                    while (ips.hasMoreElements()){
                        InetAddress ia = ips.nextElement();
                        Log.d("Abner", ia.getHostAddress());
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this,"抱歉您未開啟網路，請開啟網路並刷新",Toast.LENGTH_SHORT).show();
        }
    }

    public void insert(View v) {
        setURL.URL(url);
        data = setURL.getData();
        Intent it = getIntent();
        String farmName = it.getStringExtra("farmName");
        education_farm_title.setText(farmName);

        if (education_farm_content.getText() == "") {


            Log.d("Abner","TextView是空的");
            try {
                JSONArray jsonArray = new JSONArray(data);
                //Log.d("Abner",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject row = jsonArray.getJSONObject(i);
                    if (row.getString("FarmNm_CH").equals(farmName)) {
                        String addr = row.getString("Address_CH");
                        String tel = row.getString("TEL");
                        String web = row.getString("WebURL");
                        String fb = row.getString("Facebook");
                        String service = row.getString("ServeItem");
                        lng = (float) row.getDouble("Longitude");
                        lat = (float) row.getDouble("Latitude");
                        education_farm_content.setText("農場地址:" + addr + "\n" + "TEL:" + tel + "\n" + web + "\n" + fb);
                        education_farm_service.setText(service);
                    }
                }
            } catch (Exception e) {
                Log.d("Abner", e.toString());
            }
        }
        educationFarm_WebView.loadUrl("javascript:initMap(" + lat + ", " + lng +")");
    }


    private void initWebview() {
        WebViewClient client = new WebViewClient();
        educationFarm_WebView.setWebViewClient(client);

        WebSettings settings = educationFarm_WebView.getSettings();
        settings.setJavaScriptEnabled(true);

        //loginFarm_WebView.addJavascriptInterface(new AbnerJS(),"Abner");
        educationFarm_WebView.loadUrl("file:///android_asset/Page2.html");
    }
}