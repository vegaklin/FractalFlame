package backend.academy.fractal.utils;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import backend.academy.fractal.model.Point;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.transformation.AffineTransformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.experimental.UtilityClass;
import static backend.academy.fractal.constant.FractalConstants.AFFINE_COUNT;
import static backend.academy.fractal.utils.AffineCoefficientUtils.generateRandomAffineCoefficients;

@UtilityClass
public class RendererUtils {

    public static Point randomPointInRect(Rect world, ThreadLocalRandom random) {
        return new Point(
            world.x() + random.nextDouble() * world.width(),
            world.y() + random.nextDouble() * world.height()
        );
    }

    public static List<AffineTransformation> generateRandomAffineTransformations() {
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        for (int i = 0; i < AFFINE_COUNT; i++) {
            AffineTransformation transformation = new AffineTransformation(
                generateRandomAffineCoefficients(ThreadLocalRandom.current())
            );
            affineTransformations.add(transformation);
        }
        return affineTransformations;
    }

    public static <T> T randomTransformation(List<T> transformations, ThreadLocalRandom random) {
        int randomIndex = random.nextInt(transformations.size());
        return transformations.get(randomIndex);
    }

    public static Pixel calculateCoordinates(
        FractalImage image,
        Rect world,
        Point point
    ) {
        return image.pixel(
            (int) ((point.x() - world.x()) / world.width() * image.width()),
            (int) ((point.y() - world.y()) / world.height() * image.height())
        );
    }

    public static List<Point> generateSymmetricPoints(Point point, int symmetryCount) {
        List<Point> symmetricPoints = new ArrayList<>();
        double angleStep = 2 * Math.PI / symmetryCount;

        for (int i = 0; i < symmetryCount; i++) {
            double currentAngle = i * angleStep;
            double x = point.x() * Math.cos(currentAngle) - point.y() * Math.sin(currentAngle);
            double y = point.x() * Math.sin(currentAngle) + point.y() * Math.cos(currentAngle);
            symmetricPoints.add(new Point(x, y));
        }

        return symmetricPoints;
    }
}
