package com.example.adarshgovindarajansensorstoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity
        extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager SensorManager;
    private Sensor accelerometer, gyroscope, magnetometer, light, proximity, gravity;

    private TextView x, y, z, a, b, c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
        z = (TextView) findViewById(R.id.z);
        a = (TextView) findViewById(R.id.a);
        b = (TextView) findViewById(R.id.b);
        c = (TextView) findViewById(R.id.c);

        Log.d(TAG, "OnCreate: Initializing Sensor Services");
        SensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            SensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Accelerometer Listener Registered");
        } else {
            x.setText("Accelerometer isn't supported");
        }

        gyroscope = SensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope != null) {
            SensorManager.registerListener(MainActivity.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Gyroscope Listener Registered");
        } else {
            y.setText("Gyroscope isn't supported");
        }

        magnetometer = SensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetometer != null) {
            SensorManager.registerListener(MainActivity.this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Magnetometer Listener Registered");
        } else {
            z.setText("Magnetometer isn't supported");
        }

        light = SensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (light != null) {
        SensorManager.registerListener(MainActivity.this, light, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "OnCreate: Light Listener Registered");

        } else {
            a.setText("Light Sensor isn't supported");
        }

        proximity = SensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (proximity != null) {
            SensorManager.registerListener(MainActivity.this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Proximity Listener Registered");
        } else {
            b.setText("Proximity Sensor isn't supported");
        }

        gravity = SensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (gravity != null) {
            SensorManager.registerListener(MainActivity.this, gravity, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "OnCreate: Gravity Listener Registered");
        } else {
            c.setText("Gravity Sensor isn't supported");
        }
    }
    @Override
    protected void onStop() {
        super.onStop();

        SensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: x: " + sensorEvent.values[0] + "y: " + sensorEvent.values[1] + "z: " + sensorEvent.values[2]);

            x.setText("Accelerometer x value: " + sensorEvent.values[0] + " m/s²");
            //y.setText("Light Sensor: " + sensorEvent.values[1] + "Ix");
            //z.setText("Proximity Sensor: " + sensorEvent.values[2] + "cm");}

        }else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            y.setText("Gyroscope y value: " + sensorEvent.values[1] + " rad/s");
        }else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            z.setText("Magnetometer z value: " + sensorEvent.values[2] + " µT");
        }else if (sensor.getType() == Sensor.TYPE_LIGHT) {
                if (sensorEvent.values[0] >= 100) {
                    a.setText("Light: " + sensorEvent.values[0] + " Ix" + "           The light is bright.");
                } else {a.setText("Light: " + sensorEvent.values[0] + " Ix" + "           The light is dim.");}
        }else if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
            b.setText("Proximity: " + sensorEvent.values[0] + " cm");
        }else if (sensor.getType() == Sensor.TYPE_GRAVITY) {
            c.setText("Gravity z value: " + sensorEvent.values[2] + " m/s²");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}