package backend.academy.fractal.utils;

import backend.academy.fractal.model.Point;
import backend.academy.fractal.model.Rect;
import java.util.concurrent.ThreadLocalRandom;

public class RectUtils {

    public static Point randomPointInRect(Rect rect, ThreadLocalRandom random) {
        return new Point(
            rect.x() + random.nextDouble() * rect.width(),
            rect.y() + random.nextDouble() * rect.height()
        );
    }

    public static Point rotatePoint(Rect rect, Point point, double angle) {
        double centerX = rect.x() + rect.width() / 2;
        double centerY = rect.y() + rect.height() / 2;
        double x = (point.x() - centerX) * Math.cos(angle)
            - (point.y() - centerY) * Math.sin(angle) + centerX;
        double y = (point.x() - centerX) * Math.sin(angle)
            + (point.y() - centerY) * Math.cos(angle) + centerY;
        return new Point(x, y);
    }
}
