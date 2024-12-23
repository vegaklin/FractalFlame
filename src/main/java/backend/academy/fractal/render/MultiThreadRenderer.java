package backend.academy.fractal.render;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.transformation.AffineTransformation;
import backend.academy.fractal.transformation.Transformation;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j2;
import static backend.academy.fractal.constant.FractalConstants.THREAD_WAITING_TIME;

@Log4j2
public class MultiThreadRenderer extends FractalRenderer {
    private final int threadsCount;

    public MultiThreadRenderer(
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int affineCount,
        int symmetryCount,
        int threadsCount
    ) {
        super(variations, samples, iterPerSample, affineCount, symmetryCount);
        this.threadsCount = threadsCount;
    }

    @Override
    public void renderSamples(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    ) {
        var executor = Executors.newFixedThreadPool(threadsCount);
        try {
            for (int i = 0; i < samples; i++) {
                executor.execute(() -> renderOneSample(image, world, affineTransformations));
            }
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(THREAD_WAITING_TIME, TimeUnit.MINUTES)) {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                        log.error("The threads didn't finish on time!");
                    }
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
                log.error("The threads were interrupted", e);
            }
        }
    }
}
