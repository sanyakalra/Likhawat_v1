package com.example.sanya.likhawat_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    private TextView textDraw, textUpload;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeIDs();
        textUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        textDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, DrawActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeIDs() {
        textDraw = (TextView) findViewById(R.id.textDraw);
        textUpload = (TextView) findViewById(R.id.textUpload);

    }


}
