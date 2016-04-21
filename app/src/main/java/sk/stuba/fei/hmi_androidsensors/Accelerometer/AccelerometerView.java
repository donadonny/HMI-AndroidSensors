package sk.stuba.fei.hmi_androidsensors.Accelerometer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import sk.stuba.fei.helpClasses.Range;
import sk.stuba.fei.helpClasses.Scale;

/**
 * Created by mlaticek on 3/13/2016.
 */
public class AccelerometerView extends View {
    private Paint axis = new Paint();
    private Paint indicator = new Paint();
    private int xAxisYposition = 30;
    private int yAxisYposition = 60;
    private int zAxisYposition = 90;
    private int valueIndicatorHeight = 20;
    private float xValue = 0;
    private float yValue = 0;
    private float zValue = 0;

    private Scale scale;

    public AccelerometerView(Context context, Range domain) {
        super(context);

        axis.setColor(Color.DKGRAY);
        indicator.setStrokeWidth(8f);
        indicator.setColor(Color.BLACK);
        scale = new Scale(domain);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        scale.setRange(new Range(0, getWidth()));

        // X - axis
        canvas.drawLine(0, xAxisYposition, getWidth(), xAxisYposition, axis);
        // Y - axis
        canvas.drawLine(0, yAxisYposition, getWidth(), yAxisYposition, axis);
        // Z - axis
        canvas.drawLine(0, zAxisYposition, getWidth(), zAxisYposition, axis);
        float x, y, z;
        x = scale.getRangeValue(xValue);
        y = scale.getRangeValue(yValue);
        z = scale.getRangeValue(zValue);
        canvas.drawLine(x, xAxisYposition - (valueIndicatorHeight / 2), x, xAxisYposition + (valueIndicatorHeight / 2), indicator);
        canvas.drawLine(y, yAxisYposition - (valueIndicatorHeight / 2), y, yAxisYposition + (valueIndicatorHeight / 2), indicator);
        canvas.drawLine(z, zAxisYposition - (valueIndicatorHeight / 2), z, zAxisYposition + (valueIndicatorHeight / 2), indicator);
    }

    public void redraw(float xValue, float yValue, float zValue) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.zValue = zValue;

        invalidate();
    }
}

