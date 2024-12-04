package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;

public class SphereTransformation implements Transformation {
    @Override
    public Point apply(Point p) {
        double x = p.x();
        double y = p.y();
        double newX = x / (x * x + y * y);
        double newY = y / (x * x + y * y);
        return new Point(newX, newY);
    }
}
