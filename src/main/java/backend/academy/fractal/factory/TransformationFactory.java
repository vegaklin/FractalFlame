package backend.academy.fractal.factory;

import backend.academy.fractal.transformation.AffineTransformation;
import backend.academy.fractal.transformation.DiskTransformation;
import backend.academy.fractal.transformation.HeartTransformation;
import backend.academy.fractal.transformation.PolarTransformation;
import backend.academy.fractal.transformation.SinusoidalTransformation;
import backend.academy.fractal.transformation.SphereTransformation;
import backend.academy.fractal.transformation.Transformation;
import backend.academy.fractal.transformation.TransformationType;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import static backend.academy.fractal.utils.AffineCoefficientUtils.generateRandomAffineCoefficients;

@UtilityClass
public class TransformationFactory {

    public static Transformation createTransformation(TransformationType type) {
        return switch (type) {
            case DISK -> new DiskTransformation();
            case HEART -> new HeartTransformation();
            case POLAR -> new PolarTransformation();
            case SINUSOIDAL -> new SinusoidalTransformation();
            case SPHERE -> new SphereTransformation();
        };
    }

    public static List<Transformation> createAllTransformations(List<TransformationType> types) {
        if (types == null || types.isEmpty()) {
            return List.of(new AffineTransformation(
                generateRandomAffineCoefficients(ThreadLocalRandom.current())
            ));
        }
        return types.stream()
            .map(TransformationFactory::createTransformation)
            .collect(Collectors.toList());
    }
}
