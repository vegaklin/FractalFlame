package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;

public class SwirlTransformation implements Transformation {

    @Override
    public Point apply(Point p) {
        double x = p.x();
        double y = p.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x) + r * r;
        double newX = r * Math.cos(theta);
        double newY = r * Math.sin(theta);
        return new Point(newX, newY);
    }
}
