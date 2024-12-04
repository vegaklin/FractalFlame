package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;

public class PolarTransformation implements Transformation {

    @Override
    public Point apply(Point p) {
        double x = p.x();
        double y = p.y();
        double newX = Math.atan(y / x) / Math.PI;
        double newY = Math.sqrt(x * x + y * y) - 1;
        return new Point(newX, newY);
    }
}
