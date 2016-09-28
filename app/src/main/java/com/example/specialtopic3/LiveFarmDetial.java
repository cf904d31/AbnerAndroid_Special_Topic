package com.example.specialtopic3;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class LiveFarmDetial extends AppCompatActivity {
    private TextView live_farm_title,live_farm_content,live_farm_hosttell,live_farm_price,live_farm_staycapacity,live_farm_openhours,live_farm_creditcard,
            live_farm_trafficguide,live_farm_parkinglot,live_farm_weburl,live_farm_petnotice,live_farm_reminder,live_farm_stayfeature;
    private setURL setURL;
    private String url = "http://data.coa.gov.tw/Service/OpenData/EzgoTravelHotelStay.aspx";
    private String data;
    private ConnectivityManager mgr;
    private String farmName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_farm_detial);

        live_farm_title = (TextView) findViewById(R.id.live_farm_title);
        live_farm_content = (TextView) findViewById(R.id.live_farm_content);
        live_farm_hosttell = (TextView) findViewById(R.id.live_farm_hosttell);
        live_farm_price = (TextView) findViewById(R.id.live_farm_price);
        live_farm_staycapacity = (TextView) findViewById(R.id.live_farm_staycapacity);
        live_farm_openhours = (TextView) findViewById(R.id.live_farm_openhours);
        live_farm_creditcard = (TextView) findViewById(R.id.live_farm_creditcard);
        live_farm_trafficguide = (TextView) findViewById(R.id.live_farm_trafficguide);
        live_farm_parkinglot = (TextView) findViewById(R.id.live_farm_parkinglot);
        live_farm_weburl = (TextView) findViewById(R.id.live_farm_weburl);
        live_farm_petnotice = (TextView) findViewById(R.id.live_farm_petnotice);
        live_farm_reminder = (TextView) findViewById(R.id.live_farm_reminder);
        live_farm_stayfeature = (TextView) findViewById(R.id.live_farm_stayfeature);

        setURL = new setURL();
        setURL.URL(url);

        Intent it = getIntent();
        farmName = it.getStringExtra("farmName");
        data = it.getStringExtra("data");
        live_farm_title.setText(farmName);

        insertData();


        mgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = mgr.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            try {
                Enumeration<NetworkInterface> ifs = NetworkInterface.getNetworkInterfaces();
                while (ifs.hasMoreElements()) {
                    NetworkInterface ip = ifs.nextElement();
                    Enumeration<InetAddress> ips = ip.getInetAddresses();
                    while (ips.hasMoreElements()) {
                        InetAddress ia = ips.nextElement();
                        Log.d("Abner", ia.getHostAddress());
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "抱歉您未開啟網路，請開啟網路並刷新", Toast.LENGTH_SHORT).show();
        }
    }

    public void insert(View v) {
        setURL.URL(url);
        data = setURL.getData();
        if (live_farm_content.getText() == "") {
            Log.d("Abner", "TextView是空的");
            try {
                JSONArray jsonArray = new JSONArray(data);
                //Log.d("Abner",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject row = jsonArray.getJSONObject(i);
                    if (row.getString("Name").equals(farmName)) {
                        String addr = row.getString("Address");
                        String tel = row.getString("Tel");
                        String hostTell = row.getString("HostWords");
                        String price = row.getString("Price");
                        String staycapacity = row.getString("StayCapacity");
                        String openHours = row.getString("OpenHours");
                        String creditCard = "";
                        if (row.get("CreditCard").equals("False")) {
                            if (row.getString("TravelCard").equals("False")) {
                                creditCard = "信用卡:不可使用\n國民旅遊卡:不可使用";
                            } else if (row.getString("TravelCard").equals("True")) {
                                creditCard = "信用卡:不可使用\n國民旅遊卡:可使用";
                            }
                        } else if (row.get("CreditCard").equals("True")) {
                            if (row.getString("TravelCard").equals("False")) {
                                creditCard = "信用卡:可使用\n國民旅遊卡:不可使用";
                            } else if (row.getString("TravelCard").equals("True")) {
                                creditCard = "信用卡:可使用\n國民旅遊卡:可使用";
                            }
                        }
                        String trafficGuide = row.getString("TrafficGuidelines");
                        String parkingLot = row.getString("ParkingLot");
                        String web = row.getString("Url");
                        String email = row.getString("Email");
                        String blog = row.getString("BlogUrl");
                        String petNotice = row.getString("PetNotice");
                        String reminder = row.getString("Reminder");
                        String stayFeature = row.getString("StayFeature");
                        live_farm_content.setText("農場地址:" + addr + "\n" + "TEL:" + tel);
                        live_farm_hosttell.setText(hostTell);
                        live_farm_price.setText(price);
                        live_farm_staycapacity.setText(staycapacity);
                        live_farm_openhours.setText(openHours);
                        live_farm_creditcard.setText(creditCard);
                        live_farm_trafficguide.setText(trafficGuide);
                        live_farm_parkinglot.setText(parkingLot);
                        live_farm_weburl.setText("網站:" + web + "\n" + "Email:" + email + "\n" + "部落格:" + blog + "\n");
                        live_farm_petnotice.setText(petNotice);
                        live_farm_reminder.setText(reminder);
                        live_farm_stayfeature.setText(stayFeature);
                        Log.d("Abner", "農場地址:" + addr + "\n" + "TEL:" + tel);
                    }
                }
            } catch (Exception e) {
                Log.d("Abner", e.toString());
            }
        }
    }


    private void insertData() {
        if (live_farm_content.getText() == "") {
            Log.d("Abner", "TextView是空的");
            try {
                JSONArray jsonArray = new JSONArray(data);
                //Log.d("Abner",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject row = jsonArray.getJSONObject(i);
                    if (row.getString("Name").equals(farmName)) {
                        String addr = row.getString("Address");
                        String tel = row.getString("Tel");
                        String hostTell = row.getString("HostWords");
                        String price = row.getString("Price");
                        String staycapacity = row.getString("StayCapacity");
                        String openHours = row.getString("OpenHours");
                        String creditCard = "";
                        if (row.get("CreditCard").equals("False")) {
                            if (row.getString("TravelCard").equals("False")) {
                                creditCard = "信用卡:不可使用\n國民旅遊卡:不可使用";
                            } else if (row.getString("TravelCard").equals("True")) {
                                creditCard = "信用卡:不可使用\n國民旅遊卡:可使用";
                            }
                        } else if (row.get("CreditCard").equals("True")) {
                            if (row.getString("TravelCard").equals("False")) {
                                creditCard = "信用卡:可使用\n國民旅遊卡:不可使用";
                            } else if (row.getString("TravelCard").equals("True")) {
                                creditCard = "信用卡:可使用\n國民旅遊卡:可使用";
                            }
                        }
                        String trafficGuide = row.getString("TrafficGuidelines");
                        String parkingLot = row.getString("ParkingLot");
                        String web = row.getString("Url");
                        String email = row.getString("Email");
                        String blog = row.getString("BlogUrl");
                        String petNotice = row.getString("PetNotice");
                        String reminder = row.getString("Reminder");
                        String stayFeature = row.getString("StayFeature");
                        live_farm_content.setText("農場地址:" + addr + "\n" + "TEL:" + tel);
                        live_farm_hosttell.setText(hostTell);
                        live_farm_price.setText(price);
                        live_farm_staycapacity.setText(staycapacity);
                        live_farm_openhours.setText(openHours);
                        live_farm_creditcard.setText(creditCard);
                        live_farm_trafficguide.setText(trafficGuide);
                        live_farm_parkinglot.setText(parkingLot);
                        live_farm_weburl.setText("網站:" + web + "\n" + "Email:" + email + "\n" + "部落格:" + blog + "\n");
                        live_farm_petnotice.setText(petNotice);
                        live_farm_reminder.setText(reminder);
                        live_farm_stayfeature.setText(stayFeature);
                        Log.d("Abner", "農場地址:" + addr + "\n" + "TEL:" + tel);
                    }
                }
            } catch (Exception e) {
                Log.d("Abner", e.toString());
            }
        }
    }
}