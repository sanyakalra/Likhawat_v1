package com.example.sanya.likhawat_v1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myscript.atk.scw.SingleCharWidget;
import com.myscript.atk.scw.SingleCharWidgetApi;

/**
 * Created by swati on 4/23/2017.
 */

public class BabyActivity extends AppCompatActivity implements
        SingleCharWidgetApi.OnConfiguredListener,
        SingleCharWidgetApi.OnTextChangedListener
{

    private static final String TAG = "SingleCharDemo";

    private SingleCharWidgetApi widget;
    private FloatingActionButton btnErase, btnRecognise, btnNext;
    private TextView textRecognise, textView;
    private Intent intent;
    private int[] images = {
            R.drawable.apple, R.drawable.fish, R.drawable.candy, R.drawable.baloons, R.drawable.minion, R.drawable.tweety, R.drawable.santa, R.drawable.tom};
    private String[] value = {
            "Apple","fish","candy","baloon", "minion","tweety", "santa", "tom"
    };
    private int a=0;
    private PopupWindow popup;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    private ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_baby);
        btnErase = (FloatingActionButton) findViewById(R.id.btnErase);
        btnRecognise = (FloatingActionButton) findViewById(R.id.btnRecognise);
        btnNext = (FloatingActionButton) findViewById(R.id.btnNext) ;
        textRecognise = (TextView) findViewById(R.id.textRecognise);
        textView = (TextView) findViewById(R.id.textView);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        //imageView4 = (ImageView) findViewById(R.id.imageView4);


//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent = new Intent(BabyActivity.this, Image_change.class);
//                startActivity(intent);
//            }
//        });

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
        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // textView.setText("Type your text");
                widget.clear();
                textRecognise.setText("");
            }
        });

            final ImageView imageView = (ImageView) findViewById(R.id.imageView);
            btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                            if (a >= (images.length)-1) {
                                a = 0;
                            }
                            else{
                            ++a;}
                            imageView.setImageResource(images[a]);
                            Log.d("1","A: "+a);
                            textRecognise.setText("");
                            //imageView4.setVisibility(View.INVISIBLE);


            }
        });



        btnRecognise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (widget.getText().isEmpty()) {
                    textView.setText("Type your text");
                }
                else {
                    textRecognise.setText(widget.getText());
                    String b= (String) textRecognise.getText();
                    String c = b.toLowerCase();
                    String d = value[a].toLowerCase();
                   // Toast.makeText(BabyActivity.this,"A value :"+value[a]+" b = "+b+" C value: "+c,Toast.LENGTH_SHORT).show();
                    if(c.equals(d))
                    {
                        //imageView4.setVisibility(View.VISIBLE);
                        //imageView4.setImageResource(R.drawable.liksymbol);
                        Toast.makeText(BabyActivity.this,"Congrats!!! You won a candy!",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(BabyActivity.this,"OOPS!! You lost, Try Again!",Toast.LENGTH_SHORT).show();
                    }
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
        //Toast.makeText(getApplicationContext(), "Single Char Widget Configured", Toast.LENGTH_SHORT).show();
        if(BuildConfig.DEBUG)
            Log.d(TAG, "Single Char Widget configured!");
    }

    @Override
    public void onTextChanged(SingleCharWidgetApi widget, String s, boolean intermediate)
    {
        //Toast.makeText(getApplicationContext(), "Recognition update", Toast.LENGTH_SHORT).show();
        if(BuildConfig.DEBUG)
        {
            Log.d(TAG, "Single Char Widget recognition: " + widget.getText());
        }
    }
}

