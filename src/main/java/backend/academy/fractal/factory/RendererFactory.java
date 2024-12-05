package backend.academy.fractal.factory;

import backend.academy.fractal.render.MultiThreadRenderer;
import backend.academy.fractal.render.OneThreadRenderer;
import backend.academy.fractal.render.Renderer;
import backend.academy.fractal.render.RendererType;
import backend.academy.fractal.transformation.Transformation;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RendererFactory {

    public static Renderer createRenderer(
        RendererType type,
        List<Transformation> variations,
        int samples,
        int iterPerSample
    ) {
        return switch (type) {
            case ONE_THREAD -> new OneThreadRenderer(
                variations,
                samples,
                iterPerSample
            );
            case MULTI_THREAD -> new MultiThreadRenderer(
                variations,
                samples,
                iterPerSample
            );
        };
    }
}
