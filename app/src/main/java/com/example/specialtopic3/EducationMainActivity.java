package com.example.specialtopic3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class EducationMainActivity extends AppCompatActivity {
    private ListView educationShowList;
    private TextView educationMainTxtTitle;
    private String town;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_main);
        educationShowList = (ListView)findViewById(R.id.educationShowList);
        educationMainTxtTitle = (TextView)findViewById(R.id.educationMainTxtTitle);

        Intent it = getIntent();
        town = it.getStringExtra("name");
        educationMainTxtTitle.setText(town);



    }

    public void insert(View v) {

    }
}
