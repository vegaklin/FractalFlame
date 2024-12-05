package backend.academy.fractal.factory;

import backend.academy.fractal.image.correction.GammaCorrection;
import backend.academy.fractal.image.correction.ImageCorrectionType;
import backend.academy.fractal.image.correction.ImageProcessor;

public class ImageCorrectionFactory {

    public static ImageProcessor createImageProcessor(ImageCorrectionType type, double gamma) {
        return switch (type) {
            case GAMMA_CORRECTION -> new GammaCorrection(gamma);
        };
    }
}
