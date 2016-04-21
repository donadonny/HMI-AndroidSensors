package sk.stuba.fei.hmi_androidsensors.LightSensor;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import sk.stuba.fei.hmi_androidsensors.Accelerometer.Accelerometer;
import sk.stuba.fei.hmi_androidsensors.R;

/**
 * Created by mlaticek on 4/21/2016.
 */
public class LightSensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lightsensor_activity);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
