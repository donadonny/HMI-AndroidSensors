package sk.stuba.fei.hmi_androidsensors;


import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.txusballesteros.SnakeView;

import sk.stuba.fei.hmi_androidsensors.R;

/**
 * Created by mlaticek on 4/20/2016.
 */
public class Plot extends Fragment {

    private Context context;
    private SnakeView snakeView;

    public SnakeView getSnakeView() {
        return snakeView;
    }

    public void setSnakeView(SnakeView snakeView) {
        this.snakeView = snakeView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View fragView = inflater.inflate(R.layout.chart_fragment, container, false);

        context = getActivity().getApplicationContext();
        snakeView = (SnakeView)fragView.findViewById(R.id.snake);
        snakeView.setMinValue(-15);
        snakeView.setMaxValue(15);
        snakeView.setMaximumNumberOfValues(20);

        return fragView;
    }
}
