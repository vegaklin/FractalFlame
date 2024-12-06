package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;

public class DiskTransformation implements Transformation {

    @Override
    public Point apply(Point p) {
        double x = p.x();
        double y = p.y();
        double firstPart = (1 / Math.PI) * Math.atan(y / x);
        double secondPart = Math.PI * Math.sqrt(x * x + y * y);
        double newX = firstPart * Math.sin(secondPart);
        double newY = firstPart * Math.cos(secondPart);
        return new Point(newX, newY);
    }
}
