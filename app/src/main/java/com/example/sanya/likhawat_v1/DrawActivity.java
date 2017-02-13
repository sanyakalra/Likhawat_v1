package com.example.sanya.likhawat_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by sanya on 13/2/17.
 */

public class DrawActivity extends AppCompatActivity {

    private TextView btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initializeIDs();
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initializeIDs() {
        btnContinue = (TextView) findViewById(R.id.btnContinue);
    }
}
