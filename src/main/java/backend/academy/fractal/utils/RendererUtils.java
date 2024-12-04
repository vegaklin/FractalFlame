package backend.academy.fractal.utils;

import backend.academy.fractal.transformation.AffineTransformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static backend.academy.fractal.constant.FractalConstants.AFFINE_COUNT;
import static backend.academy.fractal.utils.AffineCoefficientUtils.generateRandomAffineCoefficients;

public class RendererUtils {

    public static List<AffineTransformation> generateRandomAffineTransformations() {
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        for (int i = 0; i < AFFINE_COUNT; i++) {
            AffineTransformation transformation = new AffineTransformation(generateRandomAffineCoefficients(ThreadLocalRandom.current()));
            affineTransformations.add(transformation);
        }
        return affineTransformations;
    }

    public static <T> T randomTransformation(List<T> list, ThreadLocalRandom random) {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
