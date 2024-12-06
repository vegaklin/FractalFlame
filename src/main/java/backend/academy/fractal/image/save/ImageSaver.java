package backend.academy.fractal.image.save;

import backend.academy.fractal.model.FractalImage;

@FunctionalInterface
public interface ImageSaver {
    void save(FractalImage image);
}
