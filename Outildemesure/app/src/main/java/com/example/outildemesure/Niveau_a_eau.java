package com.example.outildemesure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.outildemesure.controleur.threadfleche;

public class Niveau_a_eau extends AppCompatActivity implements SensorEventListener {

    private TextView tvDeltaX;
    private TextView tvDeltaY;
    private LinearLayout imgXL;
    private LinearLayout imgXR;
    private LinearLayout imgYB;
    private LinearLayout imgYT;
    private Button btnReturn;
    private int x = 0;
    private int y = 0;

    private threadfleche thread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau_aeau);

        init();

    }
    void init(){
        tvDeltaX = (TextView) findViewById(R.id.deltax);
        tvDeltaY = (TextView) findViewById(R.id.deltay);

        imgXL = (LinearLayout) findViewById(R.id.imgXL);
        imgXR = (LinearLayout) findViewById(R.id.imgXR);
        imgYB = (LinearLayout) findViewById(R.id.imgYB);
        imgYT = (LinearLayout) findViewById(R.id.imgYT);
        btnReturn = (Button)findViewById(R.id.btnReturn);
        thread1 = new threadfleche(imgXL,imgXR,imgYB,imgYT);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                //startActivityForResult(myIntent, 0);
                //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_LONG).show();
                Niveau_a_eau.this.finish();
            }

        });
        thread1.start();
        thread1.setRunning(true);
    }

    private SensorManager sensorManager;
    @Override
    protected void onResume() {
        super.onResume();

        sensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(
                this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        x =(int)(event.values[0]*100/SensorManager.GRAVITY_EARTH);
        y = (int)(event.values[1]*100/SensorManager.GRAVITY_EARTH);
        tvDeltaX.setText(x+"%");
        tvDeltaY.setText(y+"%");
        //x=0;
        //y = 10;
        //imgYT.setVisibility(View.INVISIBLE);
        if (x != 0 ){
            if(x<0){
                thread1.setImgXR(false);
                thread1.setImgXL(true);
            }
            if(x>0){
                thread1.setImgXR(true);
                thread1.setImgXL(false);
            }
        }
        else{
            thread1.setImgXR(false);
            thread1.setImgXL(false);
        }

        if (y != 0 ){
            if(y<0){
                thread1.setImgYT(false);
                thread1.setImgYB(true);
            }
            if(y > 0){
                thread1.setImgYT(true);
                thread1.setImgYB(false);
            }
        }
        else{
            thread1.setImgYT(false);
            thread1.setImgYB(false);
        }

    }
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}