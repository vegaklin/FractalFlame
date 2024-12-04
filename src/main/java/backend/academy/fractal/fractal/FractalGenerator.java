package backend.academy.fractal.fractal;

import backend.academy.fractal.image.ImageFormat;
import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.render.FractalRenderer;
import backend.academy.fractal.render.MultiThreadGenerator;
import backend.academy.fractal.render.SingleThreadRenderer;
import backend.academy.fractal.utils.ImageUtils;
import lombok.SneakyThrows;
import java.nio.file.Path;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_HEIGHT;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_WITH;
import static backend.academy.fractal.constant.FractalConstants.IS_MULTI_THREAD;
import static backend.academy.fractal.constant.FractalConstants.ITERATIONS;
import static backend.academy.fractal.constant.FractalConstants.RECT_HEIGHT;
import static backend.academy.fractal.constant.FractalConstants.RECT_WITH;
import static backend.academy.fractal.constant.FractalConstants.RECT_X;
import static backend.academy.fractal.constant.FractalConstants.RECT_Y;
import static backend.academy.fractal.constant.FractalConstants.SAMPLES;
import static backend.academy.fractal.constant.FractalConstants.TRANSFORMATIONS;

public class FractalGenerator {

    @SneakyThrows
    public void start() {
        Rect world = createRect();
        FractalRenderer renderer = chooseFractalRenderer();

        FractalImage result = renderer.render(world, IMAGE_WITH, IMAGE_HEIGHT);
        ImageUtils.save(result, Path.of("fractal.png"), ImageFormat.PNG);
    }

    private Rect createRect() {
        return new Rect(RECT_X, RECT_Y, RECT_WITH, RECT_HEIGHT);
    }

    private FractalRenderer chooseFractalRenderer() {
        return IS_MULTI_THREAD ?
            new MultiThreadGenerator(TRANSFORMATIONS, SAMPLES, ITERATIONS) :
            new SingleThreadRenderer(TRANSFORMATIONS, SAMPLES, ITERATIONS);
    }
}
