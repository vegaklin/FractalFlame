package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;

public class HyperbolicTransformation implements Transformation {

    @Override
    public Point apply(Point p) {
        double x = p.x();
        double y = p.y();
        double atanPart = Math.atan(x / y);
        double sqrtPart = Math.sqrt(x * x + y * y);
        double newX = Math.sin(atanPart) / sqrtPart;
        double newY = Math.cos(atanPart) * sqrtPart;
        return new Point(newX, newY);
    }
}
