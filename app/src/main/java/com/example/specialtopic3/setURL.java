package com.example.specialtopic3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 小豬 on 2016/9/25.
 */
public class setURL {
    private String data;
    public void URL(String url) {
        final String uri = url;
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    BufferedReader buf =
                            new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    data = buf.readLine();
                    buf.close();
                } catch (Exception e) {
                    //Log.d("Abner",e.toString());
                }
            }
        }.start();
    }
    public String getData() {
        return data;
    }
}
