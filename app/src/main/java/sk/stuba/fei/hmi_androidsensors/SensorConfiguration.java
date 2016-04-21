package sk.stuba.fei.hmi_androidsensors;

import android.hardware.Sensor;

import sk.stuba.fei.hmi_androidsensors.Accelerometer.AccelerometerActivity;
import sk.stuba.fei.hmi_androidsensors.Accelerometer.AccelerometerSummaryFragment;
import sk.stuba.fei.hmi_androidsensors.LightSensor.LightSensorActivity;
import sk.stuba.fei.hmi_androidsensors.LightSensor.LightSensorSummaryFragment;
import sk.stuba.fei.hmi_androidsensors.MagneticFieldSensor.MagneticFieldActivity;
import sk.stuba.fei.hmi_androidsensors.MagneticFieldSensor.MagneticFieldSensorSummaryFragment;
import sk.stuba.fei.hmi_androidsensors.ProximitySensor.ProximitySensorActivity;
import sk.stuba.fei.hmi_androidsensors.ProximitySensor.ProximitySensorSummaryFragment;

/**
 * Created by mlaticek on 4/20/2016.
 */
public class SensorConfiguration {

    private int sensorType;
    private boolean active;
    private Class summaryFragmentClass;
    private Class detailActivityClass;
    private Object summaryFragmentObject;

    public SensorConfiguration(int sensorType) {
        this.sensorType = sensorType;
        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                active = true;
                summaryFragmentClass = LightSensorSummaryFragment.class;
                summaryFragmentObject = new LightSensorSummaryFragment();
                detailActivityClass = LightSensorActivity.class;
                break;
            case Sensor.TYPE_PROXIMITY:
                active = true;
                summaryFragmentClass = ProximitySensorSummaryFragment.class;
                summaryFragmentObject = new ProximitySensorSummaryFragment();
                detailActivityClass = ProximitySensorActivity.class;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                active = true;
                summaryFragmentClass = MagneticFieldSensorSummaryFragment.class;
                summaryFragmentObject = new MagneticFieldSensorSummaryFragment();
                detailActivityClass = MagneticFieldActivity.class;
                break;
            case Sensor.TYPE_ACCELEROMETER:
                active = true;
                summaryFragmentClass = AccelerometerSummaryFragment.class;
                summaryFragmentObject = new AccelerometerSummaryFragment();
                detailActivityClass = AccelerometerActivity.class;
                break;
            default:
                active = false;
        }
    }

    public int getSensorType() {
        return sensorType;
    }

    public boolean isActive() {
        return active;
    }

    public Class getSummaryFragmentClass() {
        return summaryFragmentClass;
    }

    public Class getDetailActivityClass() {
        return detailActivityClass;
    }

    public Object getSummaryFragmentObject() {
        return summaryFragmentObject;
    }
}
