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
import java.util.HashMap;
import java.util.LinkedList;

public class LoginFarmDetial extends AppCompatActivity {
    private TextView login_farm_title,login_farm_content;
    private setURL setURL;
    private String url = "http://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=376";
    private String data;
    private ConnectivityManager mgr;
    private WebView loginFarm_WebView;
    private float lat,lng;
    private String farmName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_farm_detial);
        login_farm_title = (TextView)findViewById(R.id.login_farm_title);
        login_farm_content = (TextView)findViewById(R.id.login_farm_content);
        loginFarm_WebView = (WebView)findViewById(R.id.loginFarm_WebView);

        setURL = new setURL();
        setURL.URL(url);
        initWebview();

        Intent it = getIntent();
        farmName = it.getStringExtra("farmName");
        data = it.getStringExtra("data");
        login_farm_title.setText(farmName);

        insertData();


        mgr = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = mgr.getActiveNetworkInfo();
        if (info != null && info.isConnected()){
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
        if (login_farm_content.getText() == "") {
            Log.d("Abner","TextView是空的");
            try {
                JSONArray jsonArray = new JSONArray(data);
                //Log.d("Abner",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject row = jsonArray.getJSONObject(i);
                    if (row.getString("農場名稱").equals(farmName)) {
                        String addr = row.getString("住址");
                        String tel = row.getString("農場電話");
                        String web = row.getString("農場網址");
                        lng = (float) row.getDouble("經度-X");
                        lat = (float) row.getDouble("緯度-Y");
                        login_farm_content.setText("農場地址:" + addr + "\n" + "TEL:" + tel + "\n" + web);
                    }
                }
            } catch (Exception e) {
                Log.d("Abner", e.toString());
            }
        }
        loginFarm_WebView.loadUrl("javascript:initMap(" + lat + ", " + lng +")");
    }


    private void insertData() {
        if (login_farm_content.getText() == "") {
            Log.d("Abner","TextView是空的");
            try {
                JSONArray jsonArray = new JSONArray(data);
                //Log.d("Abner",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject row = jsonArray.getJSONObject(i);
                    if (row.getString("農場名稱").equals(farmName)) {
                        String addr = row.getString("住址");
                        String tel = row.getString("農場電話");
                        String web = row.getString("農場網址");
                        lng = (float) row.getDouble("經度-X");
                        lat = (float) row.getDouble("緯度-Y");
                        login_farm_content.setText("農場地址:" + addr + "\n" + "TEL:" + tel + "\n" + web);
                    }
                }
            } catch (Exception e) {
                Log.d("Abner", e.toString());
            }
        }
    }


    private void initWebview() {
        WebViewClient client = new WebViewClient();
        loginFarm_WebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loginFarm_WebView.loadUrl("javascript:initMap(" + lat + ", " + lng +")");
            }
        });

        WebSettings settings = loginFarm_WebView.getSettings();
        settings.setJavaScriptEnabled(true);

        loginFarm_WebView.loadUrl("file:///android_asset/Page2.html");
    }
}
