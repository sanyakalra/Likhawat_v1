package com.example.sanya.likhawat_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AdultActivity extends AppCompatActivity{

    private TextView textDraw, textCamera;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adult_menu);
        initializeIDs();
        textCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AdultActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        textDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AdultActivity.this, DrawActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeIDs() {
        textDraw = (TextView) findViewById(R.id.textDraw);
        textCamera = (TextView) findViewById(R.id.textCamera);

    }


}
