package sk.stuba.fei.hmi_androidsensors.Accelerometer;

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
import android.widget.LinearLayout;

import sk.stuba.fei.hmi_androidsensors.LightSensor.LightIndicatorView;
import sk.stuba.fei.hmi_androidsensors.R;

/**
 * Created by mlaticek on 4/14/2016.
 */
public class AccelerometerSummaryFragment extends Fragment implements SensorEventListener {

    private Context context;
    private AccelerometerGLView accelerometerGLview;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magneticField;

    private float valueX = 0f;
    private float valueY = 0f;
    private float valueZ = 9.81f;

    private float valueXPrior = 0f;
    private float valueYPrior = 0f;
    private float valueZPrior = 9.81f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        context = getActivity().getApplicationContext();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
//        magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//        if(magneticField != null) {
//            sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL);
//        }
        View fragView = inflater.inflate(R.layout.accelerometer_fragment, container, false);
        LinearLayout linearLayout = (LinearLayout) fragView.findViewById(R.id.acc_summary_fragment);
        accelerometerGLview = new AccelerometerGLView(context);
        linearLayout.addView(accelerometerGLview);

        return fragView;
    }

    private float[] accValues = new float[3];
    private float[] magneticValues = { 0.0f, 0.0f, 1.0f };
    boolean redraw = false;

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                if(Math.abs(accValues[0]-event.values[0]) > 1.0 ||
                        Math.abs(accValues[1] - event.values[1]) > 1.0) {
                    accValues = event.values.clone();
//                    Log.i("AccelerometerSummaryFragment", "Acc Values = X: " + String.valueOf(accValues[0]) + " Y: " +String.valueOf(accValues[1]) + " Z: " + String.valueOf(accValues[2]));
                    redraw = true;
                }
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
//                magneticValues = event.values.clone();
//                Log.i("AccelerometerSummaryFragment", "Mag Values = X: " + magneticValues[0] + " Y: " + magneticValues[1] + " Z: " + magneticValues[2]);
                break;
            default:
                break;
        }

        if( accValues != null && magneticValues != null && redraw) {
            redraw = false;
            accelerometerGLview.reRender(accValues, magneticValues);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
