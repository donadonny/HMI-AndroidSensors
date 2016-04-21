package sk.stuba.fei.hmi_androidsensors.LightSensor;

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
import android.widget.FrameLayout;
import android.widget.TextView;

import sk.stuba.fei.hmi_androidsensors.R;

/**
 * Created by mlaticek on 4/21/2016.
 */
public class LightSensor extends Fragment implements SensorEventListener {

    private Context context;
    private SensorManager sensorManager;
    private Sensor sensor;

    private TextView lightReadingValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();

        View fragView = inflater.inflate(R.layout.lightsensor_detail_fragment, container, false);
        initView(fragView);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            lightReadingValue.setText("Sensor Not Available");
        }

        return fragView;
    }

    private void initView(View view) {
        lightReadingValue = (TextView) view.findViewById(R.id.lightSensValue);
        FrameLayout canvasContainer = (FrameLayout) view.findViewById(R.id.light_sensor_summary_canvas);
        canvasContainer.addView(new LightIndicatorView(context));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        lightReadingValue.setText(Float.toString(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
