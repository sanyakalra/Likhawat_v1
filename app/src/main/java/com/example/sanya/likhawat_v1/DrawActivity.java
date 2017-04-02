package com.example.sanya.likhawat_v1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.myscript.atk.scw.SingleCharWidget;
import com.myscript.atk.scw.SingleCharWidgetApi;

public class DrawActivity extends AppCompatActivity implements
        SingleCharWidgetApi.OnConfiguredListener,
        SingleCharWidgetApi.OnTextChangedListener
{

    private static final String TAG = "SingleCharDemo";

    private SingleCharWidgetApi widget;
    private TextView btnReset, btnContinue, textView;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_draw);
        btnReset = (TextView) findViewById(R.id.btnReset);
        btnContinue = (TextView) findViewById(R.id.btnContinue);
        textView = (TextView) findViewById(R.id.textView) ;

        widget = (SingleCharWidget) findViewById(R.id.singleChar_widget);
        if (!widget.registerCertificate(MyCertificate.getBytes()))
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Please use a valid certificate.");
            dlgAlert.setTitle("Invalid certificate");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int which)
                {
                    //dismiss the dialog
                }
            });
            dlgAlert.create().show();
            return;
        }

        widget.setOnConfiguredListener(this);
        widget.setOnTextChangedListener(this);

        // references assets directly from the APK to avoid extraction in application
        // file system
        widget.addSearchDir("zip://" + getPackageCodePath() + "!/assets/conf");

        // The configuration is an asynchronous operation. Callbacks are provided to
        // monitor the beginning and end of the configuration process and update the UI
        // of the input method accordingly.
        //
        // "en_US" references the en_US bundle name in conf/en_US.conf file in your assets.
        // "si_text" references the configuration name in en_US.conf
        widget.configure("en_US", "si_text");
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Type your text");
                widget.clear();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (widget.getText().isEmpty()) {
                    textView.setText("Type your text");
                }
                else {
                    textView.setText(widget.getText());
                    widget.clear();
                }
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        widget.setOnTextChangedListener(null);
        widget.setOnConfiguredListener(null);
        super.onDestroy();
    }

    @Override
    public void onConfigured(SingleCharWidgetApi widget, boolean success)
    {
        if(!success)
        {
            Toast.makeText(getApplicationContext(), widget.getErrorString(), Toast.LENGTH_LONG).show();
            Log.e(TAG, "Unable to configure the Single Char Widget: " + widget.getErrorString());
            return;
        }
        Toast.makeText(getApplicationContext(), "Single Char Widget Configured", Toast.LENGTH_SHORT).show();
        if(BuildConfig.DEBUG)
            Log.d(TAG, "Single Char Widget configured!");
    }

    @Override
    public void onTextChanged(SingleCharWidgetApi widget, String s, boolean intermediate)
    {
        Toast.makeText(getApplicationContext(), "Recognition update", Toast.LENGTH_SHORT).show();
        if(BuildConfig.DEBUG)
        {
            Log.d(TAG, "Single Char Widget recognition: " + widget.getText());
        }
    }
}
