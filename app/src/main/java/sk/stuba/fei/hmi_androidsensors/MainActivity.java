package sk.stuba.fei.hmi_androidsensors;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            Accelerometer accelerometer = new Accelerometer();
            accelerometer.setArguments(getIntent().getExtras());

            LightSensor lightSensor = new LightSensor();
            lightSensor.setArguments(getIntent().getExtras());

            ProximitySensor proximitySensor = new ProximitySensor();
            proximitySensor.setArguments(getIntent().getExtras());

            Compas compas = new Compas();
            compas.setArguments(getIntent().getExtras());

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, accelerometer);
            fragmentTransaction.add(R.id.fragment_container, lightSensor);
            fragmentTransaction.add(R.id.fragment_container, compas);
            fragmentTransaction.add(R.id.fragment_container, proximitySensor);

            fragmentTransaction.commit();
        }
    }
}
