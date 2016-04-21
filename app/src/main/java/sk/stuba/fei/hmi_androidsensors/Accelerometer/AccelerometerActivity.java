package sk.stuba.fei.hmi_androidsensors.Accelerometer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import sk.stuba.fei.hmi_androidsensors.AccelerometerPlot;
import sk.stuba.fei.hmi_androidsensors.R;

/**
 * Created by mlaticek on 4/20/2016.
 */
public class AccelerometerActivity extends AppCompatActivity {

    private FrameLayout topFrameLayout;
    private FrameLayout middleFrameLayout;
    private LinearLayout bottomFrameLayout;

    private Accelerometer accelerometer;
    private AccelerometerPlot accelerometerPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer_activity);

//        topFrameLayout = (FrameLayout)findViewById(R.id.accelerometer_activity_container_top);
//        middleFrameLayout = (FrameLayout)findViewById(R.id.accelerometer_activity_container_middle);
//        bottomFrameLayout = (LinearLayout)findViewById(R.id.accelerometer_activity_container_bottom);

        accelerometer = new Accelerometer();
        accelerometerPlot = new AccelerometerPlot();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.accelerometer_activity_container_top, accelerometer, "accelerometerCurrentValues");
        fragmentTransaction.add(R.id.accelerometer_activity_container_middle, accelerometerPlot, "plot");
        fragmentTransaction.commit();
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
