package sk.stuba.fei.hmi_androidsensors;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mlaticek on 3/9/2016.
 */
public class HMIActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hmi);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                public void onBackStackChanged() {
                    int backCount = getSupportFragmentManager().getBackStackEntryCount();
                    if (backCount == 0) {
                        finish();
                    }
                }
            });

            if (savedInstanceState == null) {

                HMIGlobalView hmiGlobalView = new HMIGlobalView();

//            Accelerometer accelerometer = new Accelerometer();
//            accelerometer.setArguments(getIntent().getExtras());
//
//            LightSensor lightSensor = new LightSensor();
//            lightSensor.setArguments(getIntent().getExtras());
//
//            ProximitySensor proximitySensor = new ProximitySensor();
//            proximitySensor.setArguments(getIntent().getExtras());
//
//            MagneticFieldSensor magneticFieldSensor = new MagneticFieldSensor();
//            magneticFieldSensor.setArguments(getIntent().getExtras());

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, hmiGlobalView).addToBackStack(null);
//            fragmentTransaction.add(R.id.fragment_container, accelerometer, "draw");
//            fragmentTransaction.add(R.id.fragment_container, accelerometer);
//            fragmentTransaction.add(R.id.fragment_container, lightSensor);
//            fragmentTransaction.add(R.id.fragment_container, proximitySensor);
//            fragmentTransaction.add(R.id.fragment_container, magneticFieldSensor);

                fragmentTransaction.commit();
            }
        }
    }
}
