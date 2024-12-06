package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;

public class WavesTransformation implements Transformation {

    @Override
    public Point apply(Point p) {
        double x = p.x();
        double y = p.y();
        double newX = x + (Math.sin(y * y) / 2);
        double newY = y + (Math.sin(x * x) / 2);
        return new Point(newX, newY);
    }
}
