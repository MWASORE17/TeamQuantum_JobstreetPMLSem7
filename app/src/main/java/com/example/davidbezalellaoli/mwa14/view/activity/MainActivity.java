package com.example.davidbezalellaoli.mwa14.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.davidbezalellaoli.mwa14.R;
import com.example.davidbezalellaoli.mwa14.model.session.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends ParentActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<MyData> data_list;
    Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Quantum");

        refresh = (Button)findViewById(R.id.refresh);
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        data_list  = new ArrayList<>();
        load_data_from_server(0);
        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CustomAdapter(this,data_list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                public boolean onSingleTapUp(MotionEvent e){
                    return true;
                }
            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)){
                    int position = rv.getChildAdapterPosition(child);
                    if(data_list.get(position).getId()==1) {
                        Intent i = new Intent(getApplicationContext(), loker0.class);
                        getApplicationContext().startActivity(i);
                    }
                    else if(data_list.get(position).getId()==2) {
                        Intent i = new Intent(getApplicationContext(), loker1.class);
                        getApplicationContext().startActivity(i);
                    }
                    else if(data_list.get(position).getId()==3) {
                        Intent i = new Intent(getApplicationContext(), loker2.class);
                        getApplicationContext().startActivity(i);
                    }
                    else if(data_list.get(position).getId()==4) {
                        Intent i = new Intent(getApplicationContext(), loker3.class);
                        getApplicationContext().startActivity(i);
                    }
                    else if(data_list.get(position).getId()==5) {
                        Intent i = new Intent(getApplicationContext(), loker4.class);
                        getApplicationContext().startActivity(i);
                    }
                    else if(data_list.get(position).getId()==6) {
                        Intent i = new Intent(getApplicationContext(), loker5.class);
                        getApplicationContext().startActivity(i);
                    }
                    else if(data_list.get(position).getId()==7) {
                        Intent i = new Intent(getApplicationContext(), activity_loker6.class);
                        getApplicationContext().startActivity(i);
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == data_list.size()-1){
                    load_data_from_server(data_list.get(data_list.size()-1).getId());
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load_data_from_server(data_list.get(data_list.size()-1).getId());
            }
        });

        /* checking the session */
        if (!SessionManager.with(getApplicationContext()).isuserlogin()) {
            this.doChangeActivity(getApplicationContext(), AuthActivity.class);
        }
    }
    private void load_data_from_server(int id) {
        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.43.181/test/script.php?id="+integers[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    JSONArray array = new JSONArray(response.body().string());
                    for (int i=0; i<array.length(); i++){
                        JSONObject object = array.getJSONObject(i);
                        MyData data = new MyData(object.getInt("id"),object.getString("description"),
                                object.getString("image"));
                        data_list.add(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.out.println("End of content");
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }

        };
        task.execute(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //logout
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                SessionManager.with(getApplicationContext()).clearsession();
                ParentActivity.doChangeActivity(getApplicationContext(), AuthActivity.class);
                break;

            case R.id.photo:
                Intent photo = new Intent(MainActivity.this, profile_camera.class);
                startActivity(photo);
                break;

            case R.id.notes:
                Intent notes = new Intent(MainActivity.this, notes.class);
                startActivity(notes);
                break;

            case R.id.lowongan:
                Intent lowongan = new Intent(MainActivity.this, daftarLoker.class);
                startActivity(lowongan);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
