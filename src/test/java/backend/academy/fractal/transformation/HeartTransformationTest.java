package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeartTransformationTest {

    private final Transformation transformation = new HeartTransformation();

    @Test
    void checkTransformationApply() {
        // given

        double x = 5.67;
        double y = 9.12;
        Point input = new Point(x, y);

        double expectedX = Math.sqrt(x * x + y * y) * Math.sin(Math.sqrt(x * x + y * y) * Math.atan(y / x));
        double expectedY = -Math.sqrt(x * x + y * y) * Math.cos(Math.sqrt(x * x + y * y) * Math.atan(y / x));

        // when

        Point result = transformation.apply(input);

        // then

        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }
}
