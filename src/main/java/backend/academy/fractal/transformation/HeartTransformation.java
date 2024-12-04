package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;

public class HeartTransformation implements Transformation {

    @Override
    public Point apply(Point p) {
        double x = p.x();
        double y = p.y();
        double firstPart = Math.sqrt(x * x + y * y);
        double secondPart = firstPart * Math.atan(y / x);
        double newX = firstPart * Math.sin(secondPart);
        double newY = -firstPart * Math.cos(secondPart);
        return new Point(newX, newY);
    }
}
