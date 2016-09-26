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
    private TextView login_farm_title,login_farm_content,login_farm_location;
    private setURL setURL;
    private String url = "http://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=376";
    private String data;
    private ConnectivityManager mgr;
    private WebView loginFarm_WebView;
    private float lat,lng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_farm_detial);
        login_farm_title = (TextView)findViewById(R.id.login_farm_title);
        login_farm_content = (TextView)findViewById(R.id.login_farm_content);
        login_farm_location = (TextView)findViewById(R.id.login_farm_location);
        loginFarm_WebView = (WebView)findViewById(R.id.loginFarm_WebView);

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
        login_farm_title.setText(farmName);

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


    private void initWebview() {
        WebViewClient client = new WebViewClient();
        loginFarm_WebView.setWebViewClient(client);

        WebSettings settings = loginFarm_WebView.getSettings();
        settings.setJavaScriptEnabled(true);

        //loginFarm_WebView.addJavascriptInterface(new AbnerJS(),"Abner");
        loginFarm_WebView.loadUrl("file:///android_asset/Page2.html");
    }
}
