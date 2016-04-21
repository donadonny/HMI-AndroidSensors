package sk.stuba.fei.hmi_androidsensors.MagneticFieldSensor;

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
import android.widget.TextView;

import sk.stuba.fei.hmi_androidsensors.R;

/**
 * Created by mlaticek on 2/22/2016.
 */
public class MagneticFieldSensorSummaryFragment extends Fragment implements SensorEventListener {


    private Context context;
    private SensorManager sensorManager;
    private Sensor sensor;

    private TextView compassValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.magneticfieldsensor_fragment, container, false);
        initView(fragView);

        context = getActivity().getApplicationContext();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            compassValue.setText("Sensor Not Available");
        }

        return fragView;
    }

    private void initView(View view) {
        compassValue = (TextView) view.findViewById(R.id.compassValue);
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
    public void onSensorChanged(SensorEvent event) {
        compassValue.setText(Float.toString(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
