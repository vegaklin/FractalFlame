package backend.academy.fractal;

import backend.academy.fractal.factory.FractalGeneratorFactory;
import backend.academy.fractal.fractal.FractalGenerator;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        FractalGenerator fractalGenerator = FractalGeneratorFactory.createFractalGenerator();
        fractalGenerator.start();
    }
}
