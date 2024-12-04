package backend.academy.fractal.image;

import backend.academy.fractal.model.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
