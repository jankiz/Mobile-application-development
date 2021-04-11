package com.example.sensorsandwidgets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    int tag = 1;
    ImageView imageView;

    Sensor sensor;
    SensorManager sensorManager;
    boolean isRunning = false;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void openCamera(View view) {
        checkUserPermission();
    }

    void checkUserPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }

        takePicture();
    }

    private void takePicture() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, tag);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == tag && resultCode == RESULT_OK) {
            Bundle b = data.getExtras();
            Bitmap img = (Bitmap) b.get("data");
            imageView.setImageBitmap(img);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.values[0] > 40 && isRunning == false) {
            isRunning = true;
            mp = new MediaPlayer();
            try{
                mp.setDataSource("https://pl.meln.top/mr/40ffb47431dab1c8b1282445b1e4b5c0.mp3?session_key=a9f12e145752e4c159f06b0cb8e80118");
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }
}