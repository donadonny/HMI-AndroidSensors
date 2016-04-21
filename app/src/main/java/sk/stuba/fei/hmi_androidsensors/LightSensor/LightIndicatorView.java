package sk.stuba.fei.hmi_androidsensors.LightSensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import sk.stuba.fei.helpClasses.Intensity;

/**
 * Created by mlaticek on 4/21/2016.
 */
public class LightIndicatorView extends View {

    private Paint indicatorLine = new Paint();
    private Path lightBulbPath;
    private int w;
    private int h;
    int ninthOfW;
    int seventhOfH;
    private Intensity intensity = Intensity.high    ;

    public Intensity getIntensity() {
        return intensity;
    }

    public void setIntensity(Intensity intensity) {
        this.intensity = intensity;
    }

    public LightIndicatorView(Context context) {
        super(context);

        this.intensity = intensity;
        indicatorLine.setColor(Color.DKGRAY);
        indicatorLine.setStyle(Paint.Style.STROKE);
        indicatorLine.setStrokeWidth(8f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        h = getHeight();
        w = getWidth();
        ninthOfW = w / 9;
        seventhOfH = h / 7;

        getLightBulbPath();
        canvas.drawPath(lightBulbPath, indicatorLine);

        switch (intensity) {
            case very_low:
                // no indicators
                break;
            case low:
                // 2 indicators
                canvas.drawLine(3 * ninthOfW, 0, 3 * ninthOfW + ninthOfW / 2, seventhOfH, indicatorLine);
                canvas.drawLine(6 * ninthOfW, 0, 6 * ninthOfW - ninthOfW / 2, seventhOfH, indicatorLine);
                break;
            case medium:
                canvas.drawLine(2 * ninthOfW, 0, 2 * ninthOfW + ninthOfW / 2, seventhOfH, indicatorLine);
                canvas.drawLine(4 * ninthOfW + ninthOfW / 2, 0, 4 * ninthOfW + ninthOfW / 2, seventhOfH, indicatorLine);
                canvas.drawLine(7 * ninthOfW, 0, 7 * ninthOfW - ninthOfW / 2, seventhOfH, indicatorLine);
                break;
            case high:
                canvas.drawLine(2 * ninthOfW-ninthOfW/4, seventhOfH/4, 2 * ninthOfW + ninthOfW / 2, seventhOfH+seventhOfH/4, indicatorLine);
                canvas.drawLine(3 * ninthOfW, 0, 3*ninthOfW+ninthOfW/2, seventhOfH, indicatorLine);
                canvas.drawLine(4 * ninthOfW + ninthOfW / 2, 0, 4 * ninthOfW + ninthOfW / 2, seventhOfH, indicatorLine);
                canvas.drawLine(6 * ninthOfW,0, 6* ninthOfW-ninthOfW/2, seventhOfH, indicatorLine);
                canvas.drawLine(7 * ninthOfW+ninthOfW/4, seventhOfH/4, 7 * ninthOfW - ninthOfW / 2, seventhOfH+seventhOfH/4, indicatorLine);
                break;
            case very_high:
                canvas.drawLine(ninthOfW/2, seventhOfH / 2, ninthOfW + ninthOfW / 2, seventhOfH + seventhOfH / 2, indicatorLine);
                canvas.drawLine(2 * ninthOfW-ninthOfW/4, seventhOfH/4, 2 * ninthOfW + ninthOfW / 2, seventhOfH+seventhOfH/4, indicatorLine);
                canvas.drawLine(3 * ninthOfW, 0, 3*ninthOfW+ninthOfW/2, seventhOfH, indicatorLine);
                canvas.drawLine(4 * ninthOfW + ninthOfW / 2, 0, 4 * ninthOfW + ninthOfW / 2, seventhOfH, indicatorLine);
                canvas.drawLine(6 * ninthOfW,0, 6* ninthOfW-ninthOfW/2, seventhOfH, indicatorLine);
                canvas.drawLine(7 * ninthOfW+ninthOfW/4, seventhOfH/4, 7 * ninthOfW - ninthOfW / 2, seventhOfH+seventhOfH/4, indicatorLine);
                canvas.drawLine(8*ninthOfW+ninthOfW/2, seventhOfH/2, 8*ninthOfW - ninthOfW/2, seventhOfH + seventhOfH/2, indicatorLine);
                break;
            default:
                break;
        }

//        canvas.drawLine(getWidth() / 4 - 10, 0, getWidth() / 4 - 10, 20, indicatorLine);
//        canvas.drawLine(getWidth() / 4 + 10, 0, getWidth() / 4 + 10, 20, indicatorLine);
    }

    private Path getLightBulbPath() {

        lightBulbPath = new Path();
        lightBulbPath.moveTo(4 * ninthOfW - ninthOfW / 2, 6 * seventhOfH);
        lightBulbPath.lineTo(3 * ninthOfW, 5 * seventhOfH);
        lightBulbPath.arcTo(new RectF(2 * ninthOfW, 2 * seventhOfH, 7 * ninthOfW, 5 * seventhOfH), 135, 270);
        lightBulbPath.lineTo(6 * ninthOfW, 5 * seventhOfH);
        lightBulbPath.lineTo(5 * ninthOfW + ninthOfW / 2, 6 * seventhOfH);
        lightBulbPath.lineTo(5 * ninthOfW + ninthOfW / 2, h - seventhOfH / 2);
        lightBulbPath.lineTo(4 * ninthOfW - ninthOfW / 2, h - seventhOfH / 2);
        lightBulbPath.lineTo(4 * ninthOfW - ninthOfW / 2, 6 * seventhOfH);
        lightBulbPath.lineTo(5 * ninthOfW + ninthOfW / 2, 6 * seventhOfH);

        return lightBulbPath;
    }

    public void redraw(Intensity lightIntensity) {
        this.intensity = lightIntensity;
        invalidate();
    }
}
