package com.example.davidbezalellaoli.mwa14.view.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.davidbezalellaoli.mwa14.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class home_admin extends AppCompatActivity {

    WebView myWebView;
    Button refresh;
    TextView result;
    RequestQueue requestQueue;
    String showUrl = "http://192.168.43.181/tutorial/showStudents.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        this.setTitle("Admin");

        refresh = (Button) findViewById(R.id.refresh);
        result = (TextView) findViewById(R.id.hasil);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, showUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray students = response.getJSONArray("students");
                    result.setText("");
                    for (int i = 0; i < students.length(); i++) {
                        JSONObject student = students.getJSONObject(i);
                        String firstname = student.getString("firstname");
                        String lastname = student.getString("lastname");
                        String age = student.getString("age");
                        result.append(firstname+" | "+lastname+" | "+age+"\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray students = response.getJSONArray("students");
                            result.setText("");
                            for (int i = 0; i < students.length(); i++) {
                                JSONObject student = students.getJSONObject(i);
                                String firstname = student.getString("firstname");
                                String lastname = student.getString("lastname");
                                String age = student.getString("age");
                                result.append(firstname+" | "+lastname+" | "+age+"\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled") @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = myWebView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptEnabled(true);
        myWebView.requestFocusFromTouch();
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://console.firebase.google.com/u/0/project/fmctest-76da0/notification/compose");
        return true;
    }
    public void onClickBack(View view){
        onBackPressed();
    }
}
