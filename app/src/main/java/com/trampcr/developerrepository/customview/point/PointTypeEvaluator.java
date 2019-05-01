package com.trampcr.developerrepository.customview.point;

import android.animation.TypeEvaluator;
import android.graphics.Point;

public class PointTypeEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float x = startValue.x + (endValue.x - startValue.x) * fraction;
        float y = startValue.y + (endValue.y - startValue.y) * fraction;
        return new Point((int) x, (int) y);
    }
}
