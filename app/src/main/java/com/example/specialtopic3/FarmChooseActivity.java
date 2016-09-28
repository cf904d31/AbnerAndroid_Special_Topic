package com.example.specialtopic3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class FarmChooseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_choose);
    }

    public void gotoTown(View v) {
        Intent it = new Intent(this,TownChooseActivity.class);
        startActivity(it);
    }

    public void gotoEducation(View v) {
        Intent it = new Intent(this,EducationTownChoose.class);
        startActivity(it);
    }

    public void gotoLive(View v) {
        Intent it = new Intent(this,LiveTownChoose.class);
        startActivity(it);
    }
}
