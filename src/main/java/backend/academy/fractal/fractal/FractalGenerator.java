package backend.academy.fractal.fractal;

import backend.academy.fractal.image.correction.ImageProcessor;
import backend.academy.fractal.image.save.ImageSaver;
import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.render.Renderer;
import backend.academy.fractal.transformation.Transformation;
import java.nio.file.Path;
import java.util.List;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_FORMAT;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_HEIGHT;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_PATH;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_WITH;
import static backend.academy.fractal.constant.FractalConstants.ITERATIONS;
import static backend.academy.fractal.constant.FractalConstants.RECT_HEIGHT;
import static backend.academy.fractal.constant.FractalConstants.RECT_WITH;
import static backend.academy.fractal.constant.FractalConstants.RECT_X;
import static backend.academy.fractal.constant.FractalConstants.RECT_Y;
import static backend.academy.fractal.constant.FractalConstants.RENDERER_TYPE;
import static backend.academy.fractal.constant.FractalConstants.SAMPLES;
import static backend.academy.fractal.constant.FractalConstants.TRANSFORMATION_TYPES;
import static backend.academy.fractal.factory.RendererFactory.createRenderer;
import static backend.academy.fractal.factory.TransformationFactory.createAllTransformations;

public class FractalGenerator {

    private final ImageProcessor imageProcessor;

    private final ImageSaver imageSaver;

    public FractalGenerator(ImageProcessor imageProcessor, ImageSaver imageSaver) {
        this.imageProcessor = imageProcessor;
        this.imageSaver = imageSaver;
    }

    public void start() {
        Rect world = createRect();
        List<Transformation> transformations = createAllTransformations(TRANSFORMATION_TYPES);

        Renderer renderer = createRenderer(
            RENDERER_TYPE,
            transformations,
            SAMPLES,
            ITERATIONS
        );

        FractalImage image = renderer.render(world, IMAGE_WITH, IMAGE_HEIGHT);

        imageProcessor.process(image);
        imageSaver.save(image, Path.of(IMAGE_PATH + IMAGE_FORMAT.name().toLowerCase()));
    }

    private Rect createRect() {
        return new Rect(RECT_X, RECT_Y, RECT_WITH, RECT_HEIGHT);
    }


}
