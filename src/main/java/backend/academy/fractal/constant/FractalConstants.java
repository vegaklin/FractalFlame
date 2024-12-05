package backend.academy.fractal.constant;

import backend.academy.fractal.transformation.DiskTransformation;
import backend.academy.fractal.transformation.HeartTransformation;
import backend.academy.fractal.transformation.PolarTransformation;
import backend.academy.fractal.transformation.SphereTransformation;
import backend.academy.fractal.transformation.Transformation;
import java.util.List;

public class FractalConstants {
    public static final int THREAD_COUNT = 4;

    public static final boolean IS_MULTI_THREAD = true;

    public static final int AFFINE_COUNT = 23;

    public static final int SYMMETRY_COUNT = 5;

    public static final int IMAGE_WITH = 1920;
    public static final int IMAGE_HEIGHT = 1080;

    public static final double RECT_X = -4;
    public static final double RECT_Y = -3;
    public static final double RECT_WITH = 9;
    public static final double RECT_HEIGHT = 6;

    public static final int SAMPLES = 100;
    public static final int ITERATIONS = 10000;

    public static final List<Transformation> TRANSFORMATIONS = List.of(
        new SphereTransformation(),
//        new DiskTransformation()
        new HeartTransformation()
    );



    public static final int STEPS_FOR_CORRECTION = 20;
    public static final int MAX_COLOR_NUMBER = 255;
}
