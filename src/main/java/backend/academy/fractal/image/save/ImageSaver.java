package backend.academy.fractal.image.save;

import backend.academy.fractal.model.FractalImage;
import java.nio.file.Path;

@FunctionalInterface
public interface ImageSaver {
    void save(FractalImage image, Path filename);
}
