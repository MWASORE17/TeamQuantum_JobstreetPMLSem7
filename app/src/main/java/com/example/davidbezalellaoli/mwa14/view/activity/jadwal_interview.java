package com.example.davidbezalellaoli.mwa14.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.davidbezalellaoli.mwa14.R;

public class jadwal_interview extends AppCompatActivity {

    TextView textView;
    String message = "No New Massages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_interview);
        this.setTitle("Quantum");

        textView = (TextView)findViewById(R.id.textView41);
        if(getIntent().getExtras()!=null){
            message = getIntent().getExtras().getString("message");
            if(message==null){
                message = "No New Messages";
            }
        }
        textView.setText("Jadwal interview anda: "+message);

    }
    //kembali ke halaman sebelumnya
    public void onClickBack(View view){
        onBackPressed();
    }

}
