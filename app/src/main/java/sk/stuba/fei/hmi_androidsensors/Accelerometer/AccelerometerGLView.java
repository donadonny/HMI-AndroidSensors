package sk.stuba.fei.hmi_androidsensors.Accelerometer;

import android.content.Context;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;

/**
 * Created by mlaticek on 4/14/2016.
 */
public class AccelerometerGLView extends GLSurfaceView {

    private final AccelerometerGLRenderer HMIGLRenderer;

    public AccelerometerGLView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        HMIGLRenderer = new AccelerometerGLRenderer();
        setRenderer(HMIGLRenderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void reRender(float[] accelerometer, float[] magneticSens) {
        float[] fR = new float[16];
        SensorManager.getRotationMatrix(fR, null, accelerometer, magneticSens);
        HMIGLRenderer.mRotationMatrix = fR.clone();
        requestRender();
    }
}
