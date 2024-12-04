package backend.academy.fractal.render;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;

@FunctionalInterface
public interface Renderer {
    FractalImage render(Rect world, int imageWidth, int imageHeight);
}
