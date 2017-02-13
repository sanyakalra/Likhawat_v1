package com.example.sanya.likhawat_v1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by sanya on 13/2/17.
 */

public class UploadActivity extends AppCompatActivity {

    private Button btnUpload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initializeIDs();
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initializeIDs() {
        btnUpload = (Button) findViewById(R.id.btn_upload);
    }
}
