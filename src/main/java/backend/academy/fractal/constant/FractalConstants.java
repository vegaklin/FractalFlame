package backend.academy.fractal.constant;

import backend.academy.fractal.transformation.DiskTransformation;
import backend.academy.fractal.transformation.HeartTransformation;
import backend.academy.fractal.transformation.Transformation;
import java.util.List;

public class FractalConstants {
    public static final int THREAD_COUNT = 4;

    public static final boolean IS_MULTI_THREAD = true;

    public static final int AFFINE_COUNT = 15;

    public static final int IMAGE_WITH = 1920;
    public static final int IMAGE_HEIGHT = 1080;

    public static final int RECT_X = -1;
    public static final int RECT_Y = -1;
    public static final int RECT_WITH = 2;
    public static final int RECT_HEIGHT = 2;

    public static final int SAMPLES = 10;
    public static final int ITERATIONS = 100000;

    public static final List<Transformation> TRANSFORMATIONS = List.of(new DiskTransformation(), new HeartTransformation());



    public static final int STEPS_FOR_CORRECTION = 20;
}
