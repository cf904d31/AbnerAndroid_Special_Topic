package com.example.specialtopic3;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ListView showList;
    private TextView mainTxtTitle;
    private LinkedList<HashMap<String,Object>> linkedList = new LinkedList<>();
    private String []from = {"title","content","img"};
    private int []to = {R.id.item_title,R.id.item_content,R.id.showImg};
    private SimpleAdapter adapter;
    private int[] img = {R.drawable.fruit1,R.drawable.fruit2,R.drawable.fruit3,R.drawable.fruit4,R.drawable.fruit5,R.drawable.fruit6,R.drawable.fruit7};
    private String town;
    private setURL setURL;
    private String url = "http://data.coa.gov.tw/Service/OpenData/DataFileService.aspx?UnitId=376";

    private ConnectivityManager mgr;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showList = (ListView) findViewById(R.id.showList);
        mainTxtTitle = (TextView) findViewById(R.id.mainTxtTitle);
        setURL = new setURL();

        intitListView();

        Intent it = this.getIntent();
        town = it.getStringExtra("name");
        mainTxtTitle.setText(town);

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

    private void intitListView() {

        linkedList = new LinkedList<>();
        adapter = new SimpleAdapter(this ,linkedList ,R.layout.layout_item ,from ,to);
        showList.setAdapter(adapter);


        showList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Abner","i = " + id);
                Toast.makeText(MainActivity.this,"title=" + linkedList.get(position).get("title"),Toast.LENGTH_SHORT).show();
                Intent it = new Intent();
                it.setClass(MainActivity.this,LoginFarmDetial.class);
                it.putExtra("farmName",(String)linkedList.get(position).get("title"));
                startActivity(it);
            }
        });
        showList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Abner","i = " + position + "->Long");
                Toast.makeText(MainActivity.this,"Long OK",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

//    private void insert() {
//        data = setURL.getData();
//
//        if (linkedList.isEmpty()) {
//            try {
//                JSONArray jsonArray = new JSONArray(data);
//                //Log.d("Abner",""+jsonArray.length());
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject row = jsonArray.getJSONObject(i);
//                    if (row.getString("縣市").equals(town)) {
//                        String name = row.getString("農場名稱");
//                        String addr = row.getString("住址");
//                        HashMap<String, Object> pageitem = new HashMap<>();
//                        pageitem.put(from[0], name);
//                        pageitem.put(from[1], addr);
//                        pageitem.put(from[2], img[(int) (Math.random() * 7)]);
//                        linkedList.add(pageitem);
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//            } catch (Exception e) {
//                Log.d("Abner", e.toString());
//            }
//        }
//    }


    public void insert(View v) {
        setURL.URL(url);
        data = setURL.getData();

        if (linkedList.isEmpty()) {
            try {
                JSONArray jsonArray = new JSONArray(data);
                //Log.d("Abner",""+jsonArray.length());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject row = jsonArray.getJSONObject(i);
                    if (row.getString("縣市").equals(town)) {
                        String name = row.getString("農場名稱");
                        String addr = row.getString("住址");
                        HashMap<String, Object> pageitem = new HashMap<>();
                        pageitem.put(from[0], name);
                        pageitem.put(from[1], addr);
                        pageitem.put(from[2], img[(int) (Math.random() * 7)]);
                        linkedList.add(pageitem);
                        adapter.notifyDataSetChanged();
                    }
                }
            } catch (Exception e) {
                Log.d("Abner", e.toString());
            }
        }
    }
}


//https://www.microsoft.com/zh-tw/store/p/%E5%8F%B0%E7%81%A3%E8%BE%B2%E5%A0%B4/9nblgggxwzth#
//        https://developers.google.com/maps/documentation/android-api/?hl=zh-tw
//        https://github.com/soyoungboy/android-material-design-Open-source-projects/blob/master/README.md
//        https://github.com/wasabeef/awesome-android-ui
//        http://data.gov.tw/node/24873
//        http://data.gov.tw/node/9552
//        http://data.gov.tw/node/11997
//        http://data.gov.tw/node/6413
