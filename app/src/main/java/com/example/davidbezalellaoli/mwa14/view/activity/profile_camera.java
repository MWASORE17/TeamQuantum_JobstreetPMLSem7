package com.example.davidbezalellaoli.mwa14.view.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.davidbezalellaoli.mwa14.R;

public class profile_camera extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView buckysImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_camera);
        this.setTitle("Photo");

        Button buckyButton = (Button) findViewById(R.id.buckysButton);
        buckysImageView = (ImageView) findViewById(R.id.buckysImageView);

        //Disable the button if the user has no camera
        if(!hasCamera())
            buckyButton.setEnabled(false);
    }

    //Check if the user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Launching the camera
    public void launchCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take a picture and pass results along to onActivityResult
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    //If you want to return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //Get the photo
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
            buckysImageView.setImageBitmap(photo);
        }
    }

    public void OCBack(View view){
        onBackPressed();
    }
}