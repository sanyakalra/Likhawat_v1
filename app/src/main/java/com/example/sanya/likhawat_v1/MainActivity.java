package com.example.sanya.likhawat_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by swati on 4/23/2017.
 */

public class MainActivity extends AppCompatActivity {

    private TextView textBaby, textAdult;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeIDs();
        textAdult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, AdultActivity.class);
                startActivity(intent);
            }
        });

        textBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, BabyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeIDs() {
        textAdult = (TextView) findViewById(R.id.textAdult);
        textBaby = (TextView) findViewById(R.id.textBaby);

    }


}
