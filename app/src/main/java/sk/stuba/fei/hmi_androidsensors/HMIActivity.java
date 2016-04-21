package sk.stuba.fei.hmi_androidsensors;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import sk.stuba.fei.hmi_androidsensors.Accelerometer.Accelerometer;
import sk.stuba.fei.hmi_androidsensors.Accelerometer.AccelerometerActivity;
import sk.stuba.fei.hmi_androidsensors.Accelerometer.AccelerometerSummaryFragment;
import sk.stuba.fei.hmi_androidsensors.LightSensor.LightSensorSummaryFragment;
import sk.stuba.fei.hmi_androidsensors.MagneticFieldSensor.MagneticFieldSensorSummaryFragment;
import sk.stuba.fei.hmi_androidsensors.ProximitySensor.ProximitySensorSummaryFragment;

/**
 * Created by mlaticek on 3/9/2016.
 */
public class HMIActivity extends AppCompatActivity {

    private ArrayList<Class> sensorClass = new ArrayList<>();
    private LinearLayout fragmentContainer;
    private ArrayList<Integer> selectedSensorTypes = new ArrayList<>();
    private ArrayList<SensorConfiguration> selectedSensorConfigs = new ArrayList<>();
    private ArrayList<Integer> fragmentContainers = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hmi);
        Intent intent = getIntent();
        selectedSensorTypes = intent.getIntegerArrayListExtra(AppConstants.SELECTED_SENSOR_TYPE_LIST);
        initializeLayout();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

//            getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//                public void onBackStackChanged() {
//                    int backCount = getSupportFragmentManager().getBackStackEntryCount();
//                    if (backCount == 0) {
//                        finish();
//                    }
//                }
//            });

            if (savedInstanceState == null) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                int i = 0;
                for (int sensorType : selectedSensorTypes) {
                    SensorConfiguration sensorConfig = new SensorConfiguration(sensorType);
                    selectedSensorConfigs.add(sensorConfig);
                    fragmentTransaction.add(fragmentContainers.get(i),(Fragment) sensorConfig.getSummaryFragmentObject(), "sensor" + i);
                    i++;
                }

                fragmentTransaction.commit();
            }
        }
    }

    private void initializeLayout() {
        switch (selectedSensorTypes.size()) {
            case 1:
                findViewById(R.id.fragment_container_top_right).setVisibility(View.GONE);
                findViewById(R.id.fragment_container_bottom_row).setVisibility(View.GONE);
                fragmentContainers.add(R.id.fragment_container_top_left);
                break;
            case 2:
                findViewById(R.id.fragment_container_top_right).setVisibility(View.GONE);
                findViewById(R.id.fragment_container_bottom_right).setVisibility(View.GONE);
                fragmentContainers.add(R.id.fragment_container_top_left);
                fragmentContainers.add(R.id.fragment_container_bottom_left);
                break;
            case 3:
                findViewById(R.id.fragment_container_bottom_right).setVisibility(View.GONE);
                fragmentContainers.add(R.id.fragment_container_top_left);
                fragmentContainers.add(R.id.fragment_container_bottom_left);
                fragmentContainers.add(R.id.fragment_container_top_right);
                break;
            default:
                fragmentContainers.add(R.id.fragment_container_top_left);
                fragmentContainers.add(R.id.fragment_container_bottom_left);
                fragmentContainers.add(R.id.fragment_container_top_right);
                fragmentContainers.add(R.id.fragment_container_bottom_right);
                break;
        }
    }

    public void startTopLeftDetail(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Top Left";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
//        Intent intent = new Intent(this, selectedSensorConfigs.get(0).getDetailActivityClass());
//        startActivity(intent);
    }

    public void startBottomLeftDetail(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Bottom Left";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
//        Intent intent = new Intent(this, selectedSensorConfigs.get(1).getDetailActivityClass());
//        startActivity(intent);

    }

    public void startTopRightDetail(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Top Right";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
//        Intent intent = new Intent(this, selectedSensorConfigs.get(2).getDetailActivityClass());
//        startActivity(intent);

    }

    public void startBottomRightDetail(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Bottom Right";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
//        Intent intent = new Intent(this, selectedSensorConfigs.get(3).getDetailActivityClass());
//        startActivity(intent);

    }
}
