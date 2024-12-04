package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {}
