package backend.academy.fractal.render;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import backend.academy.fractal.model.Point;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.transformation.AffineTransformation;
import backend.academy.fractal.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import static backend.academy.fractal.constant.FractalConstants.STEPS_FOR_CORRECTION;
import static backend.academy.fractal.utils.RendererUtils.calculateCoordinates;
import static backend.academy.fractal.utils.RendererUtils.generateRandomAffineTransformations;
import static backend.academy.fractal.utils.RendererUtils.generateSymmetricPoints;
import static backend.academy.fractal.utils.RendererUtils.randomPointInRect;
import static backend.academy.fractal.utils.RendererUtils.randomTransformation;

@AllArgsConstructor
public abstract class FractalRenderer implements Renderer {

    protected final List<Transformation> variations;

    protected final int samples;
    protected final int iterPerSample;

    private final int affineCount;
    private final int symmetryCount;

    @Override
    public FractalImage render(Rect world, FractalImage canvas) {
        FractalImage image = FractalImage.create(canvas.width(), canvas.height());
        List<AffineTransformation> affineTransformations = generateRandomAffineTransformations(affineCount);

        renderSamples(image, world, affineTransformations);
        return image;
    }

    public abstract void renderSamples(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    );

    protected void renderOneSample(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    ) {
        Point point = randomPointInRect(world, ThreadLocalRandom.current());

        for (int step = -STEPS_FOR_CORRECTION; step < iterPerSample; step++) {
            AffineTransformation affineTransformation = randomTransformation(
                affineTransformations,
                ThreadLocalRandom.current()
            );
            Transformation transformation = randomTransformation(variations, ThreadLocalRandom.current());

            point = affineTransformation.apply(point);
            point = transformation.apply(point);

            if (step > 0) {
                processSymmetricPoints(image, world, point, affineTransformation);
            }
        }
    }

    private void processSymmetricPoints(
        FractalImage image,
        Rect world,
        Point point,
        AffineTransformation affineTransformation
    ) {
        List<Point> symmetricPoints = generateSymmetricPoints(point, symmetryCount);
        for (Point symmetricPoint : symmetricPoints) {
            if (world.contains(symmetricPoint)) {
                Pixel pixel = calculateCoordinates(image, world, symmetricPoint);
                if (pixel != null) {
                    synchronized (pixel) {
                        pixel.pixelProcessing(affineTransformation.affineCoefficient().color());
                    }
                }
            }
        }
    }
}

