package backend.academy.fractal.factory;

import backend.academy.fractal.fractal.FractalGenerator;
import backend.academy.fractal.image.correction.ImageProcessor;
import backend.academy.fractal.image.save.ImageSaver;
import lombok.experimental.UtilityClass;
import static backend.academy.fractal.constant.FractalConstants.CORRECTION_TYPE;
import static backend.academy.fractal.constant.FractalConstants.GAMMA;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_FORMAT;
import static backend.academy.fractal.factory.ImageCorrectionFactory.createImageProcessor;
import static backend.academy.fractal.factory.ImageSaveFactory.createImageSaver;

@UtilityClass
public class FractalGeneratorFactory {

    public static FractalGenerator createFractalGenerator() {
        ImageProcessor imageProcessor = createImageProcessor(CORRECTION_TYPE, GAMMA);
        ImageSaver imageSaver = createImageSaver(IMAGE_FORMAT);
        return new FractalGenerator(imageProcessor, imageSaver);
    }
}
