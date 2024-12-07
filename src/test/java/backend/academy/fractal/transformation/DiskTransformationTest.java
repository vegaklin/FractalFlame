package backend.academy.fractal.transformation;

import backend.academy.fractal.model.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiskTransformationTest {

    private final Transformation transformation = new DiskTransformation();

    @Test
    void checkTransformationApply() {
        // given

        double x = 5.67;
        double y = 9.12;
        Point input = new Point(x, y);

        double expectedX = (1 / Math.PI) * Math.atan(y / x) * Math.sin(Math.PI * Math.sqrt(x * x + y * y));
        double expectedY = (1 / Math.PI) * Math.atan(y / x) * Math.cos(Math.PI * Math.sqrt(x * x + y * y));

        // when

        Point result = transformation.apply(input);

        // then

        assertEquals(expectedX, result.x(), 1e-6);
        assertEquals(expectedY, result.y(), 1e-6);
    }
}
