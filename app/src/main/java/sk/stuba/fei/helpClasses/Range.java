package sk.stuba.fei.helpClasses;

/**
 * Created by mlaticek on 3/13/2016.
 */
public class Range {
    private float min;
    private float max;

    public Range(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }
}