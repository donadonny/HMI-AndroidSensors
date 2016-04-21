package sk.stuba.fei.hmi_androidsensors.LightSensor;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import sk.stuba.fei.helpClasses.Intensity;
import sk.stuba.fei.hmi_androidsensors.R;

/**
 * Created by mlaticek on 2/22/2016.
 */
public class LightSensorSummaryFragment extends Fragment implements SensorEventListener {

    private Context context;
    private SensorManager sensorManager;
    private Sensor sensor;

    private LinearLayout mainContainer;
    private LinearLayout lightSensorViewContainer;
    private LightIndicatorView lightIndicatorView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity().getApplicationContext();

        View fragView = inflater.inflate(R.layout.lightsensor_fragment, container, false);
        initView(fragView);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        return fragView;
    }

    private void initView(View view) {
        mainContainer = (LinearLayout) view.findViewById(R.id.lightSensorContainer);
        lightSensorViewContainer = (LinearLayout) view.findViewById(R.id.light_sensor_summary_canvas);
        lightIndicatorView = new LightIndicatorView(context);
        lightSensorViewContainer.addView(lightIndicatorView);
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
        float light = event.values[0];
        if(light <= 5f) {
            lightIndicatorView.redraw(Intensity.very_low);
            mainContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_border_red));
            return;
        }
        if(light <= 15f) {
            lightIndicatorView.redraw(Intensity.low);
            mainContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_border_amber));
            return;
        }
        if(light <= 25f) {
            lightIndicatorView.redraw(Intensity.medium);
            mainContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_border_grey));
            return;
        }
        if(light <= 35f) {
            lightIndicatorView.redraw(Intensity.high);
            mainContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_border_amber));
            return;
        }
        if(light >35f) {
            lightIndicatorView.redraw(Intensity.very_high);
            mainContainer.setBackground(ContextCompat.getDrawable(context, R.drawable.layout_border_red));
            return;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
