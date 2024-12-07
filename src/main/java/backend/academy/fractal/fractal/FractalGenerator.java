package backend.academy.fractal.fractal;

import backend.academy.fractal.image.correction.ImageProcessor;
import backend.academy.fractal.image.save.ImageSaver;
import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.render.Renderer;
import lombok.AllArgsConstructor;
import lombok.Getter;

//@Getter
//@AllArgsConstructor
public record FractalGenerator(FractalImage canvas, Rect world, Renderer renderer, ImageProcessor imageProcessor,
                               ImageSaver imageSaver) {

    public void start() {
        FractalImage image = renderer.render(world, canvas);

        imageProcessor.process(image);
        imageSaver.save(image);
    }
}
