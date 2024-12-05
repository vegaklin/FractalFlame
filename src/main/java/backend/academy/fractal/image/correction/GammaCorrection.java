package backend.academy.fractal.image.correction;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;

public class GammaCorrection implements ImageProcessor {
    private final double gamma;

    public GammaCorrection(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                if (image.pixel(x, y).hitCount() != 0) {
                    image.pixel(x, y).normal(Math.log10(image.pixel(x, y).hitCount()));
                    max = Math.max(max, image.pixel(x, y).normal());
                }
            }
        }
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                pixel.normal(image.pixel(x, y).normal() / max);
                pixel.r((int) (pixel.r() * Math.pow(pixel.normal(), (1.0 / gamma))));
                pixel.g((int) (pixel.g() * Math.pow(pixel.normal(), (1.0 / gamma))));
                pixel.b((int) (pixel.b() * Math.pow(pixel.normal(), (1.0 / gamma))));
            }
        }
    }
}
