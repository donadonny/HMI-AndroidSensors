package sk.stuba.fei.hmi_androidsensors.Accelerometer;


import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.util.concurrent.TimeUnit;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by mlaticek on 4/14/2016.
 */
public class AccelerometerGLRenderer implements GLSurfaceView.Renderer {

    /** Rotation increment per frame. */
    private static final float CUBE_ROTATION_INCREMENT = 1.0f;

    /** The refresh rate, in frames per second. */
    private static final int REFRESH_RATE_FPS = 60;

    /** The duration, in milliseconds, of one frame. */
    private static final float FRAME_TIME_MILLIS = TimeUnit.SECONDS.toMillis(1) / REFRESH_RATE_FPS;

    private final float[] mMVPMatrix;
    private final float[] mProjectionMatrix;
    private final float[] mViewMatrix;
    public float[] mRotationMatrix;
    private final float[] mFinalMVPMatrix;

    private BlockGL blockGL;
    private float mCubeRotation;

    public float getmCubeRotation() {
        return mCubeRotation;
    }

    public void setmCubeRotation(float mCubeRotation) {
        this.mCubeRotation = mCubeRotation;
    }

    private long mLastUpdateMillis;

    public AccelerometerGLRenderer() {
        mMVPMatrix = new float[16];
        mProjectionMatrix = new float[16];
        mViewMatrix = new float[16];
        mRotationMatrix = new float[16];
        mFinalMVPMatrix = new float[16];

        // Set the fixed camera position (View matrix).
        Matrix.setLookAtM(mViewMatrix, 0, 0.0f, 0.0f, -4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
    }

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LEQUAL);
        blockGL = new BlockGL();
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        float ratio = (float) width / height;

        GLES20.glViewport(0, 0, width, height);
        // This projection matrix is applied to object coordinates in the onDrawFrame() method.
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1.0f, 1.0f, 3.0f, 7.0f);
        // modelView = projection x view
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Apply the rotation.
//        Matrix.setRotateM(mRotationMatrix, 0, mCubeRotation, 1.0f, 0.0f, 0.0f);
//        Matrix.setRotateEulerM(mRotationMatrix, 0, xRotation, yRotation, zRotation);
        // Combine the rotation matrix with the projection and camera view
        Matrix.multiplyMM(mFinalMVPMatrix, 0, mMVPMatrix, 0, mRotationMatrix, 0);

        // Draw cube.
        blockGL.draw(mFinalMVPMatrix);
    }
}
