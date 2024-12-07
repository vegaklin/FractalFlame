package backend.academy.fractal.fractal;

import backend.academy.fractal.image.correction.ImageProcessor;
import backend.academy.fractal.image.save.ImageSaver;
import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.render.Renderer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FractalGenerator {

    private final FractalImage canvas;
    private final Rect world;

    private final Renderer renderer;

    private final ImageProcessor imageProcessor;
    private final ImageSaver imageSaver;

    public void start() {
        FractalImage image = renderer.render(world, canvas);

        imageProcessor.process(image);
        imageSaver.save(image);
    }
}
