package sk.stuba.fei.helpClasses;

/**
 * Created by mlaticek on 3/13/2016.
 */
public class Scale {
    private Range domain;
    private Range range;

    public Scale(Range domain) {
        this.domain = domain;
        this.range = new Range(0,1);
    }

    public Scale(Range domain, Range range) {
        this.domain = domain;
        this.range = range;
    }

    public Scale(float domainMin, float domainMax, float rangeMin, float rangeMax) {
        domain = new Range(domainMin, domainMax);
        range = new Range(rangeMin, rangeMax);
    }

    public Range getDomain() {
        return domain;
    }

    public void setDomain(Range domain) {
        this.domain = domain;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public float getRangeValue(float domainValue) {
        float rangeValue = (((range.getMax() - range.getMin())*(domainValue - domain.getMin()))/(domain.getMax()-domain.getMin())) + range.getMin();
        return rangeValue;
    }
}
