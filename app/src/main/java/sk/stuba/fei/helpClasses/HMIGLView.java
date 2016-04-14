package sk.stuba.fei.helpClasses;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;

/**
 * Created by mlaticek on 4/14/2016.
 */
public class HMIGLView extends GLSurfaceView implements SensorEventListener {

    private final HMIGLRenderer HMIGLRenderer;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor magneticField;

    private float valueX = 0f;
    private float valueY = 0f;
    private float valueZ = 9.81f;

    private float valueXPrior = 0f;
    private float valueYPrior = 0f;
    private float valueZPrior = 9.81f;

    public HMIGLView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        HMIGLRenderer = new HMIGLRenderer();
        setRenderer(HMIGLRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(magneticField != null) {
            sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    private float[] accValues = new float[3];
    private float[] magneticValues = { 1.0f, 1.0f, 1.0f };
    boolean redraw = false;

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                if(Math.abs(accValues[0]-event.values[0]) > 0.5 ||
                        Math.abs(accValues[1] - event.values[1]) > 0.5) {
                    accValues = event.values.clone();
                    accValues[2] = 9.81f;
                    redraw = true;
                }
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
//                magneticValues = event.values.clone();
                break;
            default:
                break;
        }

        if( accValues != null && magneticValues != null && redraw) {
            redraw = false;
            float[] fR = new float[16];
            SensorManager.getRotationMatrix(fR, null, accValues, magneticValues);
            HMIGLRenderer.mRotationMatrix = fR.clone();
            requestRender();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
