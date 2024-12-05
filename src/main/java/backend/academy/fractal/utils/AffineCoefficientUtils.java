package backend.academy.fractal.utils;

import backend.academy.fractal.model.AffineCoefficient;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import static backend.academy.fractal.constant.FractalConstants.MAX_COLOR_NUMBER;

public class AffineCoefficientUtils {

    public static AffineCoefficient generateRandomAffineCoefficients(ThreadLocalRandom random) {
        double a = random.nextDouble(-1, 1);
        double b = random.nextDouble(-1, 1);
        double c = random.nextDouble(-1, 1);
        double d = random.nextDouble(-1, 1);
        double e = random.nextDouble(-1, 1);
        double f = random.nextDouble(-1, 1);

        while (!isCorrectAffineCoefficients(a, b, d, e)) {
            a = random.nextDouble(-1, 1);
            b = random.nextDouble(-1, 1);
            c = random.nextDouble(-1, 1);
            d = random.nextDouble(-1, 1);
            e = random.nextDouble(-1, 1);
            f = random.nextDouble(-1, 1);
        }
        return new AffineCoefficient(
            a,
            b,
            c,
            d,
            e,
            f,
            new Color(generateRandomRGB(random), generateRandomRGB(random), generateRandomRGB(random))
        );
    }

    private static boolean isCorrectAffineCoefficients(
        double a,
        double b,
        double d,
        double e
    ) {
        return ((a * a + d * d) < 1)
            && ((b * b + e * e) < 1)
            && ((a * a + b * b + d * d + e * e) < (1 + (a * e - b * d) * (a * e - b * d)));
    }

    private static int generateRandomRGB(ThreadLocalRandom random) {
        return random.nextInt(MAX_COLOR_NUMBER);
    }
}
