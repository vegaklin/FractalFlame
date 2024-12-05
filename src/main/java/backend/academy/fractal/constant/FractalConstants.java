package backend.academy.fractal.constant;

import backend.academy.fractal.image.correction.ImageCorrectionType;
import backend.academy.fractal.image.save.ImageFormat;
import backend.academy.fractal.render.RendererType;
import backend.academy.fractal.transformation.TransformationType;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FractalConstants {
    private static final String YAML_FILE_PATH = "application.yaml";

    private final ConfigParser configParser = new ConfigParser(YAML_FILE_PATH);

    public static final RendererType RENDERER_TYPE = RendererType.valueOf(
        configParser.get("renderer-type", String.class)
    );

    public static final int THREAD_COUNT = configParser.get("thread-count", Integer.class);

    public static final int AFFINE_COUNT = configParser.get("affine-count", Integer.class);
    public static final int SYMMETRY_COUNT = configParser.get("symmetry-count", Integer.class);

    public static final int IMAGE_WITH = configParser.get("image-width", Integer.class);
    public static final int IMAGE_HEIGHT = configParser.get("image-height", Integer.class);
    public static final ImageFormat IMAGE_FORMAT = ImageFormat.valueOf(configParser.get("image-format", String.class));

    public static final double RECT_X = configParser.get("rect-x", Double.class);
    public static final double RECT_Y = configParser.get("rect-y", Double.class);
    public static final double RECT_WITH = configParser.get("rect-width", Double.class);
    public static final double RECT_HEIGHT = configParser.get("rect-height", Double.class);

    public static final int SAMPLES = configParser.get("samples", Integer.class);
    public static final int ITERATIONS = configParser.get("iterations", Integer.class);

    public static final List<TransformationType> TRANSFORMATION_TYPES =
        configParser.getTransformationTypes("transformation-types");

    public static final ImageCorrectionType CORRECTION_TYPE =
        ImageCorrectionType.valueOf(configParser.get("correction-type", String.class));

    public static final double GAMMA = configParser.get("gamma", Double.class);

    public static final int STEPS_FOR_CORRECTION = configParser.get("steps-for-correction", Integer.class);
    public static final int MAX_COLOR_NUMBER = configParser.get("max-color-number", Integer.class);

    public static final String IMAGE_PATH = configParser.get("image-path", String.class);
}
