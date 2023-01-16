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
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private boolean accelerometre = false;
    private boolean gravity = false;
    private boolean magnetometre = false;
    private Button btnQuit;
    private Button btnAccer;
    private Button  btnGrav;
    private Button  btnMag;


    SensorManager sensorManager;

    private EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * recuperation des objets dans la view
         */


        //txt = (EditText)findViewById(R.id.txt);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Faire la liste des capteurs de l'appareil
        listSensor();
        init();

    }
    public void init(){
        btnQuit = (Button)findViewById(R.id.btnQuitte);
        btnAccer = (Button)findViewById(R.id.btnAccer);
        btnGrav = (Button)findViewById(R.id.btnGrav);
        btnMag = (Button)findViewById(R.id.btnMag);
        /**
         * quitte
         */

        btnQuit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.finish();
                //Intent myIntent = new Intent(view.getBaseContext(), main2.class);
                //startActivityForResult(myIntent, 0);
                //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_LONG).show();
            }
        });

        /**
         *acccelerometre
         */
        if( accelerometre== true){
            btnAccer.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent myIntent = new Intent(getBaseContext(), Vibration.class);
                    startActivityForResult(myIntent, 0);
                    //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_LONG).show();
                }

            });
        }
        /**
         *
         */
        if( gravity== true){
            btnGrav.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent myIntent = new Intent(getBaseContext(), Niveau_a_eau.class);
                    startActivityForResult(myIntent, 0);
                    //MainActivity.this.onStop();
                    //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_LONG).show();
                }

            });
        }
        /**
         *
         */
        if( magnetometre== true){
            btnMag.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent myIntent = new Intent(getBaseContext(), Boussole.class);
                    startActivityForResult(myIntent, 0);
                    //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_LONG).show();
                }

            });
        }
    }

    /**
     * Lister les capteurs existants
     *
     * Trouve la liste de tous les capteurs existants, trouve un capteur spécifique ou l'ensemble des capteurs d'un type fixé.
     */
    private void listSensor() {
        // Trouver tous les capteurs de l'appareil :
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        // La chaîne descriptive de chaque capteur
        StringBuffer sensorDesc = new StringBuffer();
        // pour chaque capteur trouvé, construire sa chaîne descriptive
        for (Sensor sensor : sensors) {
            sensorDesc.append("New sensor detected : \r\n");
            sensorDesc.append("\tName: " + sensor.getName() + "\r\n");
            sensorDesc.append("\tType: " + getType(sensor.getType()) + "\r\n");
            sensorDesc.append("Version: " + sensor.getVersion() + "\r\n");
            sensorDesc.append("Resolution (in the sensor unit): " + sensor.getResolution() + "\r\n");
            sensorDesc.append("Power in mA used by this sensor while in use" + sensor.getPower() + "\r\n");
            sensorDesc.append("Vendor: " + sensor.getVendor() + "\r\n");
            sensorDesc.append("Maximum range of the sensor in the sensor's unit." + sensor.getMaximumRange() + "\r\n");
            sensorDesc.append("Minimum delay allowed between two events in microsecond" + " or zero if this sensor only returns a value when the data it's measuring changes" + sensor.getMinDelay() + "\r\n");
        }

        // Faire quelque chose de cette liste
        //Toast.makeText(this, sensorDesc.toString(), Toast.LENGTH_LONG).show();

        //txt.setText(sensorDesc.toString());

        // Pour trouver un capteur spécifique :
        Sensor gyroscopeDefault = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        // Pour trouver tous les capteurs d'un type fixé :
        List<Sensor> gyroscopes = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
    }

    private String getType(int type) {
        String strType;
        switch (type) {
            case Sensor.TYPE_ACCELEROMETER: strType = "TYPE_ACCELEROMETER";
                accelerometre = true;
                break;
            case Sensor.TYPE_GRAVITY:strType = "TYPE_GRAVITY";
                gravity = true;
                break;
            case Sensor.TYPE_GYROSCOPE:    strType = "TYPE_GYROSCOPE";
                break;
            case Sensor.TYPE_LIGHT:strType = "TYPE_LIGHT";
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:strType = "TYPE_LINEAR_ACCELERATION";
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:strType = "TYPE_MAGNETIC_FIELD";
                magnetometre = true;
                break;
            case Sensor.TYPE_ORIENTATION:strType = "TYPE_ORIENTATION";
                break;
            case Sensor.TYPE_PRESSURE:strType = "TYPE_PRESSURE";
                break;
            case Sensor.TYPE_PROXIMITY:    strType = "TYPE_PROXIMITY";
                break;
            case Sensor.TYPE_ROTATION_VECTOR:    strType = "TYPE_ROTATION_VECTOR";
                break;
            case Sensor.TYPE_TEMPERATURE:strType = "TYPE_TEMPERATURE";
                break;
            default: strType = "TYPE_UNKNOW";
                break;
        }
        return strType;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}