package backend.academy.fractal;

import backend.academy.fractal.fractal.FractalGenerator;
import lombok.experimental.UtilityClass;
import static backend.academy.fractal.constant.FractalConstants.YAML_FILE_PATH;
import static backend.academy.fractal.factory.FractalGeneratorFactory.createFractalGenerator;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        FractalGenerator fractalGenerator = createFractalGenerator(YAML_FILE_PATH);
        fractalGenerator.start();
    }
}
