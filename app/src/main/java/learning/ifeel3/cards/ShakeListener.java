/*

        TODO: ADD SHAKE COUNTER AND PERFORM LISTENER ACTION ONLY AFTER FIXED NUMBER OF SHAKES
 */




package learning.ifeel3.cards;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;

public class ShakeListener implements SensorEventListener {

    private Context context;
    private SensorManager sensorManager;
    private Sensor sensor;
    private OnShakeEventListener listener;

    float[] gravity = new float[3];
    float[] linear_acceleration = new float[3];
    float lastAccel;
    float currAccel;
    float countedAccel;
    final float ACCEL_TRESHOLD = 10;

    public ShakeListener(SensorManager sensorManager, OnShakeEventListener listener) {
        this.sensorManager = sensorManager;
        this.listener = listener;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // alpha is calculated as t / (t + dT)
        // with t, the low-pass filter's time-constant
        // and dT, the event delivery rate

        final float alpha = 0.8f;

        gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

        linear_acceleration[0] = event.values[0] - gravity[0];
        linear_acceleration[1] = event.values[1] - gravity[1];
        linear_acceleration[2] = event.values[2] - gravity[2];


//        System.out.println("X: " + linear_acceleration[0]);
//        System.out.println("Y: " + linear_acceleration[1]);
//        System.out.println("Z: " + linear_acceleration[2]);

        currAccel = (float) Math.sqrt(
                linear_acceleration[0]*linear_acceleration[0] +
                linear_acceleration[1]*linear_acceleration[1] +
                linear_acceleration[2]*linear_acceleration[2]);

        countedAccel = currAccel - lastAccel;
        lastAccel = currAccel;

        if (countedAccel > ACCEL_TRESHOLD) {
            listener.onShakeEvent();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void start() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    public interface OnShakeEventListener {

        public void onShakeEvent();
    }
}