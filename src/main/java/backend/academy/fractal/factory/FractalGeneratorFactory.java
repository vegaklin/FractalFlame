package backend.academy.fractal.factory;

import backend.academy.fractal.fractal.FractalGenerator;

public class FractalGeneratorFactory {
    public static FractalGenerator createFractalGenerator() {
        return new FractalGenerator();
    }
}
