package sk.stuba.fei.hmi_androidsensors;

import android.app.Fragment;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sk.stuba.fei.helpClasses.HMIGLView;

/**
 * Created by mlaticek on 4/14/2016.
 */
public class HMIGlobalView extends Fragment {

    private GLSurfaceView pointerGLView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        pointerGLView = new HMIGLView(this.getActivity());
        return pointerGLView;
    }
}
