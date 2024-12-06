package backend.academy.fractal.factory;

import backend.academy.fractal.constant.ConfigParser;
import backend.academy.fractal.fractal.FractalGenerator;
import backend.academy.fractal.image.correction.ImageCorrectionType;
import backend.academy.fractal.image.correction.ImageProcessor;
import backend.academy.fractal.image.save.ImageFormat;
import backend.academy.fractal.image.save.ImageSaver;
import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.render.Renderer;
import backend.academy.fractal.render.RendererType;
import lombok.experimental.UtilityClass;
import static backend.academy.fractal.factory.ImageCorrectionFactory.createImageProcessor;
import static backend.academy.fractal.factory.ImageSaveFactory.createImageSaver;
import static backend.academy.fractal.factory.RendererFactory.createRenderer;
import static backend.academy.fractal.factory.TransformationFactory.createAllTransformations;

@UtilityClass
public class FractalGeneratorFactory {

    public static FractalGenerator createFractalGenerator(String configFilePath) {
        ConfigParser configParser = new ConfigParser(configFilePath);

        FractalImage canvas = FractalImage.create(
            configParser.get("image-width", Integer.class),
            configParser.get("image-height", Integer.class)
        );

        Rect world = new Rect(
            configParser.get("rect-x", Double.class),
            configParser.get("rect-y", Double.class),
            configParser.get("rect-width", Double.class),
            configParser.get("rect-height", Double.class)
        );

        Renderer renderer = createRenderer(
            RendererType.valueOf(configParser.get("renderer-type", String.class)),
            createAllTransformations(
                configParser.getTransformationTypes("transformation-types")
            ),
            configParser.get("samples", Integer.class),
            configParser.get("iterations", Integer.class),
            configParser.get("affine-count", Integer.class),
            configParser.get("symmetry-count", Integer.class),
            configParser.get("thread-count", Integer.class)
        );


        ImageProcessor imageProcessor = createImageProcessor(
            ImageCorrectionType.valueOf(configParser.get("correction-type", String.class)),
            configParser.get("gamma", Double.class)
        );

        ImageSaver imageSaver = createImageSaver(
            ImageFormat.valueOf(configParser.get("image-format", String.class))
        );

        return new FractalGenerator(
            canvas,
            world,
            renderer,
            imageProcessor,
            imageSaver
        );
    }
}
