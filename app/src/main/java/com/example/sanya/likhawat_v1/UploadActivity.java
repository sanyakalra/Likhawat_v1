package com.example.sanya.likhawat_v1;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UploadActivity extends AppCompatActivity {

    private ImageView viewImage;
    private FloatingActionButton btnUpload;
    private TextView btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initializeIDs();
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                String imagePath = "";
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagePath = cursor.getString(columnIndex);
                }
                cursor.close();

                if (null != selectedImage) {
                    viewImage=(ImageView)findViewById(R.id.viewImage);
                    viewImage.setImageURI(selectedImage);
                    Log.w("pathimgfromgal*********", imagePath+"");
                    btnUpload.setVisibility(View.INVISIBLE);
                    btnContinue.setVisibility(View.VISIBLE);
                }

            }
        }
    }

    private void initializeIDs()
    {
        viewImage = (ImageView) findViewById(R.id.viewImage);
        btnUpload = (FloatingActionButton) findViewById(R.id.btnUpload);
        btnContinue = (TextView) findViewById(R.id.btnContinue);
    }
}
