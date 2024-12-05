package backend.academy.fractal.fractal;

import backend.academy.fractal.image.correction.ImageProcessor;
import backend.academy.fractal.image.save.ImageSaver;
import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.render.FractalRenderer;
import backend.academy.fractal.render.MultiThreadGenerator;
import backend.academy.fractal.render.OneThreadRenderer;
import lombok.SneakyThrows;
import java.nio.file.Path;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_FORMAT;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_HEIGHT;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_PATH;
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

    private final ImageProcessor imageProcessor;

    private final ImageSaver imageSaver;

    public FractalGenerator(ImageProcessor imageProcessor, ImageSaver imageSaver) {
        this.imageProcessor = imageProcessor;
        this.imageSaver = imageSaver;
    }

    public void start() {
        Rect world = createRect();
        FractalRenderer renderer = chooseFractalRenderer();

        FractalImage image = renderer.render(world, IMAGE_WITH, IMAGE_HEIGHT);

        imageProcessor.process(image);
        imageSaver.save(image, Path.of(IMAGE_PATH + IMAGE_FORMAT.name().toLowerCase()));
    }

    private Rect createRect() {
        return new Rect(RECT_X, RECT_Y, RECT_WITH, RECT_HEIGHT);
    }

    private FractalRenderer chooseFractalRenderer() {
        return IS_MULTI_THREAD ?
            new MultiThreadGenerator(TRANSFORMATIONS, SAMPLES, ITERATIONS) :
            new OneThreadRenderer(TRANSFORMATIONS, SAMPLES, ITERATIONS);
    }
}
