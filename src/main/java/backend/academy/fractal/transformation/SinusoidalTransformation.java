package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;

public class SinusoidalTransformation implements Transformation {

    @Override
    public Point apply(Point p) {
        return new Point(Math.sin(p.x()), Math.sin(p.y()));
    }
}
