package backend.academy.fractal.utils;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import backend.academy.fractal.model.Point;
import backend.academy.fractal.model.Rect;

public class ImageUtil {
    public static Pixel resolvePixel(Rect rect, Point point, FractalImage image) {
        if (!rect.contains(point)) {
            return null;
        }
        return image.pixel(
            (int) ((point.x() - rect.x()) / rect.width() * image.width()),
            (int) ((point.y() - rect.y()) / rect.height() * image.height())
        );
    }
}
