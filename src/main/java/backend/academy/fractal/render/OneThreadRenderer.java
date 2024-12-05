package backend.academy.fractal.render;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.transformation.AffineTransformation;
import backend.academy.fractal.transformation.Transformation;
import java.util.List;

public class OneThreadRenderer extends FractalRenderer {

    public OneThreadRenderer(
        List<Transformation> variations,
        int samples,
        int iterPerSample
    ) {
        super(variations, samples, iterPerSample);
    }

    @Override
    public void renderSamples(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    ) {
        for (int i = 0; i < samples; i++) {
            renderOneSample(image, world, affineTransformations);
        }
    }
}
