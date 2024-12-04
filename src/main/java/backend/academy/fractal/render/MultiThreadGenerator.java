package backend.academy.fractal.render;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.transformation.AffineTransformation;
import backend.academy.fractal.transformation.Transformation;
import lombok.SneakyThrows;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiThreadGenerator  extends FractalRenderer {

    public MultiThreadGenerator(
        List<Transformation> variations,
        int samples,
        int iterPerSample
    ) {
        super(variations, samples, iterPerSample);
    }

    @SneakyThrows
    @Override
    public void renderAllImage(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    ) {
        var service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < samples; i++) {
            service.execute(
                () -> renderOneSample(image, world, affineTransformations));
        }
        service.shutdown();
        service.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }
}
