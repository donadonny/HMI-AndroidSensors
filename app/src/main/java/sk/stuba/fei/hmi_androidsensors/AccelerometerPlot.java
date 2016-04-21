package sk.stuba.fei.hmi_androidsensors;


import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.txusballesteros.SnakeView;

/**
 * Created by mlaticek on 4/20/2016.
 */
public class AccelerometerPlot extends Fragment implements SensorEventListener {

    private Context context;
    private SnakeView snakeViewX;
    private SnakeView snakeViewY;
    private SnakeView snakeViewZ;

    private SensorManager sensorManager;
    private Sensor sensor;

    private float x = 0.0f;
    private float priorX = 0.0f;
    private float y = 0.0f;
    private float priorY = 0.0f;
    private float z = 0.0f;
    private float priorZ = 0.0f;

    public void setSnakeViewX(SnakeView snakeViewX) {
        this.snakeViewX = snakeViewX;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View fragView = inflater.inflate(R.layout.accelerometer_plot_fragment, container, false);

        context = getActivity().getApplicationContext();

        snakeViewX = (SnakeView)fragView.findViewById(R.id.accValueX);
        snakeViewX.setMinValue(-15);
        snakeViewX.setMaxValue(15);
        snakeViewX.setMaximumNumberOfValues(20);

        snakeViewY = (SnakeView)fragView.findViewById(R.id.accValueY);
        snakeViewY.setMinValue(-15);
        snakeViewY.setMaxValue(15);
        snakeViewY.setMaximumNumberOfValues(20);

        snakeViewZ = (SnakeView)fragView.findViewById(R.id.accValueZ);
        snakeViewZ.setMinValue(0);
        snakeViewZ.setMaxValue(15);
        snakeViewZ.setMaximumNumberOfValues(20);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        return fragView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        if(Math.abs(x-priorX) > 0.5) {
            snakeViewX.addValue(x);
            priorX = x;
        }

        y = event.values[1];
        if(Math.abs(y-priorY) > 0.5) {
            snakeViewY.addValue(y);
            priorY = y;
        }

        z = event.values[2];
        if(Math.abs(z-priorZ) > 0.5) {
            snakeViewZ.addValue(z);
            priorZ = z;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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
}
