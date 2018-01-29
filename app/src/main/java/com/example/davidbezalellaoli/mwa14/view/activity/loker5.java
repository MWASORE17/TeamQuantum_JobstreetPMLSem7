package com.example.davidbezalellaoli.mwa14.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.davidbezalellaoli.mwa14.R;
import java.util.HashMap;
import java.util.Map;

public class loker5 extends AppCompatActivity {

    Button insert;
    RequestQueue requestQueue;
    String insertUrl = "http://192.168.43.181/tutorial/insertStudent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loker5);
        this.setTitle("Quantum");

        insert = (Button) findViewById(R.id.insert);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("firstname","PT. Blue Bird");
                        parameters.put("lastname","Driver");
                        parameters.put("age","kevin tio");

                        return parameters;
                    }
                };
                requestQueue.add(request);
                Toast.makeText(loker5.this, "Berhasil diapply, silahkan tunggu notifikasi selanjutnya", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onClickBack(View view){
        onBackPressed();
    }
}