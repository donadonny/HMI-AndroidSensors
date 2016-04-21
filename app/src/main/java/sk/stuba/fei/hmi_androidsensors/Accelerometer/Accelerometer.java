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
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import sk.stuba.fei.helpClasses.Range;
import sk.stuba.fei.hmi_androidsensors.R;

/**
 * Created by mlaticek on 2/22/2016.
 */
public class Accelerometer extends Fragment implements SensorEventListener {

    private Context context;
    private LinearLayout accelerometerLayout;
    private LinearLayout accelerometerGraphics;
    private AccelerometerView accVisual;

    private SensorManager sensorManager;
    private Sensor sensor;

    private float valueX = 0f;
    private float valueY = 0f;
    private float valueZ = 9.81f;

    private float valueXPrior = 0f;
    private float valueYPrior = 0f;
    private float valueZPrior = 9.81f;

    private TextView valueXui;
    private TextView valueYui;
    private TextView valueZui;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragView = inflater.inflate(R.layout.accelerometer_detail_fragment, container, false);
        context = getActivity().getApplicationContext();
        initView(fragView);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            valueXui.setText("Sensor Not Available");
        }

        // add canvas
        accVisual = new AccelerometerView(context, new Range(-sensor.getMaximumRange()/3, sensor.getMaximumRange()/3));
        accelerometerGraphics.addView(accVisual);
        return fragView;
    }

    private void initView(View view) {
        accelerometerLayout = (LinearLayout) view.findViewById(R.id.accelerometerSensorContainer);
        accelerometerGraphics = (LinearLayout) view.findViewById(R.id.accelerometer_graphics);
        valueXui = (TextView) view.findViewById(R.id.accValueX);
        valueYui = (TextView) view.findViewById(R.id.accValueY);
        valueZui = (TextView) view.findViewById(R.id.accValueZ);
    }

    private void render() {

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
        float canvWidth = accVisual.getWidth();

        DecimalFormat df = new DecimalFormat("0.0000");
        df.setRoundingMode(RoundingMode.CEILING);

        valueX = event.values[0];
        valueY = event.values[1];
        valueZ = event.values[2];

        valueXui.setText(df.format(event.values[0]));
        valueYui.setText(df.format(event.values[1]));
        valueZui.setText(df.format(event.values[2]));

        if (Math.abs((valueX - valueXPrior)) > 0.3 ||
                Math.abs((valueY - valueYPrior)) > 0.3 ||
                Math.abs((valueZ - valueZPrior)) > 0.3) {
            accVisual.redraw(valueX, valueY, valueZ);

            valueXPrior = valueX;
            valueYPrior = valueY;
            valueZPrior = valueZ;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
