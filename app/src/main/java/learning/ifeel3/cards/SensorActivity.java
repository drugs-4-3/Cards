package learning.ifeel3.cards;

import android.app.AlertDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SensorActivity extends AppCompatActivity  implements ShakeListener.OnShakeEventListener {

    SensorManager sensorManager;
    Sensor sensor;
    ShakeListener shakeListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
//
//        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        shakeListener = new ShakeListener(sensorManager);

        //final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensorManager.registerListener(shakeListener, sensor, sensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onResume() {
        super.onResume();
        //shakeListener.start();
    }
    @Override
    public void onPause() {
        super.onPause();
        //shakeListener.stop();
    }

    @Override
    public void onShakeEvent() {
        System.out.println("THE DEVICE IS SHAKING!!!");
    }
}
