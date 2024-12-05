package backend.academy.fractal.constant;

import backend.academy.fractal.image.correction.ImageCorrectionType;
import backend.academy.fractal.image.save.ImageFormat;
import backend.academy.fractal.render.RendererType;
import backend.academy.fractal.transformation.HeartTransformation;
import backend.academy.fractal.transformation.SphereTransformation;
import backend.academy.fractal.transformation.Transformation;
import java.util.List;
import static backend.academy.fractal.image.correction.ImageCorrectionType.GAMMA_CORRECTION;
import static backend.academy.fractal.image.save.ImageFormat.PNG;
import static backend.academy.fractal.render.RendererType.MULTI_THREAD;

public class FractalConstants {
    public static final int THREAD_COUNT = 4;

    public static final RendererType RENDERER_TYPE = MULTI_THREAD;

    public static final int AFFINE_COUNT = 23;

    public static final int SYMMETRY_COUNT = 5;

    public static final int IMAGE_WITH = 1920;
    public static final int IMAGE_HEIGHT = 1080;

    public static final double RECT_X = -4;
    public static final double RECT_Y = -3;
    public static final double RECT_WITH = 8;
    public static final double RECT_HEIGHT = 6;

    public static final int SAMPLES = 100;
    public static final int ITERATIONS = 10000;

    public static final List<Transformation> TRANSFORMATIONS = List.of(
        new SphereTransformation(),
//        new DiskTransformation()
        new HeartTransformation()
    );

    public static final double GAMMA = 2.2;
    public static final ImageCorrectionType CORRECTION_TYPE = GAMMA_CORRECTION;
    public static final ImageFormat IMAGE_FORMAT = PNG;



    public static final int STEPS_FOR_CORRECTION = 20;
    public static final int MAX_COLOR_NUMBER = 255;
    public static final String IMAGE_PATH = "src/main/java/backend/academy/fractal/fractal.";
}
