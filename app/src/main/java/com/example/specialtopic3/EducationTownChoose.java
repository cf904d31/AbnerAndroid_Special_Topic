package com.example.specialtopic3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class EducationTownChoose extends AppCompatActivity {
    private setURL setURL;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_town_choose);
        setURL = new setURL();
        setURL.URL("http://data.coa.gov.tw/Service/OpenData/EzgoOutdoorEdu.aspx");
        data = setURL.getData();
        Log.d("Abner","EducationTownChoose"+data);
    }

    public void taipei(View v) {
        setURL.URL("http://data.coa.gov.tw/Service/OpenData/EzgoOutdoorEdu.aspx");
        data = setURL.getData();
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","台北市");
        Log.d("Abner","taipei"+data);
        it.putExtra("data",data);
        startActivity(it);
    }

    public void newTaipei(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","新北市");
        startActivity(it);
    }

    public void keelung(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","基隆市");
        startActivity(it);
    }

    public void taoyuan(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","桃園市");
        startActivity(it);
    }

    public void hsinchuCity(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","新竹市");
        startActivity(it);
    }

    public void hsinchu(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","新竹縣");
        startActivity(it);
    }

    public void miaoli(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","苗栗縣");
        startActivity(it);
    }

    public void taichung(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","台中市");
        startActivity(it);
    }

    public void changhua(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","彰化縣");
        startActivity(it);
    }

    public void nantou(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","南投縣");
        startActivity(it);
    }

    public void yunlin(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","雲林縣");
        startActivity(it);
    }

    public void chiayi(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","嘉義縣");
        startActivity(it);
    }

    public void chiayiCity(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","嘉義市");
        startActivity(it);
    }

    public void tainan(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","台南市");
        startActivity(it);
    }

    public void kaohsiung(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","高雄市");
        startActivity(it);
    }

    public void pingtung(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","屏東縣");
        startActivity(it);
    }

    public void taitung(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","台東縣");
        startActivity(it);
    }

    public void hualien(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","花蓮縣");
        startActivity(it);
    }

    public void yilan(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","宜蘭縣");
        startActivity(it);
    }

    public void penghu(View v) {
        Intent it = new Intent();
        it.setClass(this,EducationMainActivity.class);
        it.putExtra("name","澎湖縣");
        startActivity(it);
    }
}
