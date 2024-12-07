package backend.academy.fractal;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.render.MultiThreadRenderer;
import backend.academy.fractal.render.OneThreadRenderer;
import backend.academy.fractal.render.Renderer;
import backend.academy.fractal.transformation.Transformation;
import org.junit.jupiter.api.Test;
import java.util.List;
import static backend.academy.fractal.factory.TransformationFactory.createAllTransformations;
import static backend.academy.fractal.transformation.TransformationType.DISK;
import static backend.academy.fractal.transformation.TransformationType.HEART;
import static backend.academy.fractal.transformation.TransformationType.HYPERBOLIC;
import static backend.academy.fractal.transformation.TransformationType.POLAR;
import static backend.academy.fractal.transformation.TransformationType.SINUSOIDAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiThreadRendererTest {

    @Test
    void checkRendererEmptyAfterZeroIterations() {
        // given

        List<Transformation> emptyVariations = createAllTransformations(
            List.of(DISK)
        );
        Renderer renderer = new MultiThreadRenderer(emptyVariations, 10, 0, 5, 5, 8);
        Rect world = new Rect(-1, -1, 2, 2);
        FractalImage canvas = FractalImage.create(100, 100);

        // when

        FractalImage result = renderer.render(world, canvas);

        // then

        assertNotNull(result);
        assertThat(canvas.width()).isEqualTo(result.width());
        assertThat(canvas.height()).isEqualTo(result.height());

        for (Pixel pixel : result.data()) {
            assertThat(pixel.r()).isEqualTo(0);
            assertThat(pixel.g()).isEqualTo(0);
            assertThat(pixel.b()).isEqualTo(0);
            assertThat(pixel.hitCount()).isEqualTo(0);
        }
    }

    @Test
    void checkRendererFillImageAfterManyIterationsWithOneTransformation() {
        // given

        List<Transformation> emptyVariations = createAllTransformations(
            List.of(DISK)
        );
        Renderer renderer = new MultiThreadRenderer(emptyVariations, 10, 1000, 5, 5, 4);
        Rect world = new Rect(-1, -1, 2, 2);
        FractalImage canvas = FractalImage.create(100, 100);

        // when

        FractalImage result = renderer.render(world, canvas);

        // then

        assertNotNull(result);
        assertEquals(canvas.width(), result.width());
        assertEquals(canvas.height(), result.height());

        int allHintCount = 0;
        for (Pixel pixel : result.data()) {
            allHintCount += pixel.hitCount();
        }
        assertTrue(allHintCount > 0);
    }

    @Test
    void checkRendererFillImageAfterManyIterationsWithManyTransformation() {
        // given

        List<Transformation> emptyVariations = createAllTransformations(
            List.of(DISK, HEART, HYPERBOLIC, POLAR, SINUSOIDAL)
        );
        Renderer renderer = new MultiThreadRenderer(emptyVariations, 8, 10000, 3, 2, 8);
        Rect world = new Rect(-1, -1, 2, 2);
        FractalImage canvas = FractalImage.create(10, 100);

        // when

        FractalImage result = renderer.render(world, canvas);

        // then

        assertNotNull(result);
        assertEquals(canvas.width(), result.width());
        assertEquals(canvas.height(), result.height());

        int allHintCount = 0;
        for (Pixel pixel : result.data()) {
            allHintCount += pixel.hitCount();
        }
        assertTrue(allHintCount > 0);
    }
}
