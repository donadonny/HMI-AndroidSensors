package sk.stuba.fei.hmi_androidsensors.Accelerometer;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import sk.stuba.fei.hmi_androidsensors.R;

/**
 * Created by mlaticek on 4/20/2016.
 */
public class AccelerometerActivity extends AppCompatActivity {

    private FrameLayout topFrameLayout;
    private View bottomFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer_activity);

        topFrameLayout = (FrameLayout)findViewById(R.id.accelerometer_activity_container_top);
        bottomFrameLayout = (FrameLayout)findViewById(R.id.accelerometer_activity_container_bottom);

//        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            public void onBackStackChanged() {
//                int backCount = getSupportFragmentManager().getBackStackEntryCount();
//                if (backCount == 0) {
//                    finish();
//                }
//            }
//        });

        Accelerometer accelerometer = new Accelerometer();


        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_bottom_right, accelerometer, "accelerometerCurrentValues");
        fragmentTransaction.commit();
    }
}
