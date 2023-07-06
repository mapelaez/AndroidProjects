package com.example.paneladmin.contenidoExtras.sensores;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paneladmin.R;

public class Sensores  extends Activity implements SensorEventListener {

    private SensorManager sensorManager = null;
    private Sensor sensorDeTemperatura = null;
    private Sensor sensorAcelerometro = null;
    private Sensor sensorGravedad = null ;


    private TextView textViewAcelerometro = null;
    private TextView textViewTemperatura = null;
    private TextView textViewGravedad = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensores);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorDeTemperatura = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorGravedad = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);


        textViewAcelerometro = (TextView) findViewById(R.id.sensorDeMovimiento);
        textViewAcelerometro.setTextSize(20);

        textViewGravedad= (TextView) findViewById(R.id.sensorDeGravedad);
        textViewGravedad.setTextSize(20);

        textViewTemperatura = (TextView) findViewById(R.id.sensorDeTemperatura);
        textViewTemperatura.setTextSize(20);


        if(sensorAcelerometro == null){
            Toast.makeText(getApplicationContext(), "No hay Sensor movimiento", Toast.LENGTH_SHORT).show();}
        else{
            Toast.makeText(getApplicationContext(), "Sí hay Sensor de movimiento", Toast.LENGTH_SHORT).show();
        }


        if(sensorDeTemperatura == null){
            Toast.makeText(getApplicationContext(), "No hay Sensor de Temperatura", Toast.LENGTH_SHORT).show();}
        else{
            Toast.makeText(getApplicationContext(), "Si hay Sensor de Temperatura", Toast.LENGTH_SHORT).show();
        }


        if (sensorGravedad== null) {
            Toast.makeText(this, "No hay sensor de Gravedad", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Si hay sensor de Gravedad", Toast.LENGTH_LONG).show();
        }


    }



    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent arg0) {
        synchronized (this){
            float[] masData;
            float x;
            float y;
            float z;
            // TODO Auto-generated method stub
            switch(arg0.sensor.getType()){

                case Sensor.TYPE_ACCELEROMETER:
                    masData = arg0.values;
                    x = masData[0];
                    y = masData[1];
                    z = masData[2];
                    textViewAcelerometro.setText("Acelerometro:\nx: " + x + "\ny: "+y + "\nz: "+z);
                    break;

                case Sensor.TYPE_GRAVITY:
                    masData = arg0.values;
                    x = masData[0];
                    y = masData[1];
                    z = masData[2];
                    textViewGravedad.setText("Gravedad:\nx: " + x + "\ny: "+y + "\nz: "+z);
                    break;
                case Sensor.TYPE_AMBIENT_TEMPERATURE:
                    x=sensorDeTemperatura.getPower();
                    textViewTemperatura.setText("Temperatura: "+ x);
                    break;

                default:
                    break;
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorDeTemperatura, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, sensorGravedad, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        super.onStop();
    }


}
