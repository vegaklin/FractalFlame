package backend.academy.fractal.transformation;

import backend.academy.fractal.model.AffineCoefficient;
import backend.academy.fractal.model.Point;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AffineTransformationTest {

    @Test
    void checkTransformationApply() {
        // given

        AffineCoefficient affineCoefficient = new AffineCoefficient(
            0.5, 0.5, 0.5, 0.5, 0.5, 0.5,
            new Color(1,1,1)
        );
        Transformation transformation = new AffineTransformation(affineCoefficient);
        double x = 5.67;
        double y = 9.12;
        Point input = new Point(x, y);

        double expectedX = x * affineCoefficient.a() + y * affineCoefficient.b() + affineCoefficient.c();
        double expectedY = x * affineCoefficient.d() + y * affineCoefficient.e() + affineCoefficient.f();

        // when

        Point result = transformation.apply(input);

        // then

        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }
}
