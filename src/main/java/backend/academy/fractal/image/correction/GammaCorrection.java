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
                Pixel pixel = image.pixel(x, y);
                if (pixel != null) {
                    if (pixel.hitCount() != 0) {
                        pixel.normal(Math.log10(pixel.hitCount()));
                        max = Math.max(max, pixel.normal());
                    }
                }
            }
        }

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel != null) {
                    pixel.normal(pixel.normal() / max);
                    pixel.r((int) (pixel.r() * Math.pow(pixel.normal(), (1.0 / gamma))));
                    pixel.g((int) (pixel.g() * Math.pow(pixel.normal(), (1.0 / gamma))));
                    pixel.b((int) (pixel.b() * Math.pow(pixel.normal(), (1.0 / gamma))));
                }
            }
        }
    }
}
