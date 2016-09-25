package com.example.specialtopic3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WelcomeActivity extends AppCompatActivity {
    private ImageView showWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        showWelcome = (ImageView) findViewById(R.id.showWelcome);
        showWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMain();
            }
        });
    }
    private void gotoMain(){
        Intent it = new Intent(this, FarmChooseActivity.class);
        startActivity(it);
        finish();
    }
}
