package backend.academy.fractal.transformation;

import backend.academy.fractal.model.AffineCoefficient;
import backend.academy.fractal.model.Point;

public record AffineTransformation(AffineCoefficient affineCoefficient) implements Transformation {

    @Override
    public Point apply(Point p) {
        double x = p.x();
        double y = p.y();
        double newX = x * affineCoefficient.a() + y * affineCoefficient.b() + affineCoefficient.c();
        double newY = x * affineCoefficient.d() + y * affineCoefficient.e() + affineCoefficient.f();
        return new Point(newX, newY);
    }
}
