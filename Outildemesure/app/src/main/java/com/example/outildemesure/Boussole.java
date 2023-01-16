package com.example.outildemesure;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


//import com.example.outildemesure.R;
import com.example.outildemesure.vue.CompassView;

import java.util.List;

public class Boussole extends Activity implements SensorEventListener {

    //La vue de notre boussole
    private CompassView compassView;

    //Le gestionnaire des capteurs
    private SensorManager sensorManager;
    //Notre capteur de la boussole num�rique
    private Sensor sensor;
    private Button btnReturnBou;

    //Notre listener sur le capteur de la boussole num�rique

        @Override
        public void onSensorChanged(SensorEvent event) {
            updateOrientation(event.values[SensorManager.DATA_X]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boussole);
        compassView = (CompassView)findViewById(R.id.compassView);
        //R�cup�ration du gestionnaire de capteurs
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //Demander au gestionnaire de capteur de nous retourner les capteurs de type boussole
        List<Sensor> sensors =sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

        btnReturnBou = (Button)findViewById(R.id.btnReturnBou);
        btnReturnBou.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Intent myIntent = new Intent(view.getBaseContext(), main2.class);
                //startActivityForResult(myIntent, 0);
                //Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_LONG).show();
                Boussole.this.finish();
            }
        });

        //s�il y a plusieurs capteurs de ce type on garde uniquement le premier
        if (sensors.size() > 0) {
            sensor = sensors.get(0);
        }
    }

    //Mettre � jour l'orientation
    protected void updateOrientation(float rotation) {
        compassView.setNorthOrientation(rotation);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Lier les �v�nements de la boussole num�rique au listener
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop(){
        super.onStop();
        //Retirer le lien entre le listener et les �v�nements de la boussole num�rique
        sensorManager.unregisterListener(this);
    }
}