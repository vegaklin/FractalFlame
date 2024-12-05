package backend.academy.fractal.image.correction;

import backend.academy.fractal.model.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
