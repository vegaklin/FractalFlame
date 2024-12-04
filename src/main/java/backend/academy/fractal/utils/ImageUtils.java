package backend.academy.fractal.utils;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import backend.academy.fractal.image.ImageFormat;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public class ImageUtils {

    public static void save(FractalImage image, Path path, ImageFormat format) throws IOException {
        double max = 0.0;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                if (image.pixel(x, y).hitCount() != 0) {
                    image.pixel(x, y).normal(Math.log10(image.pixel(x, y).hitCount()));
                    if (image.pixel(x, y).normal() > max) {
                        max = image.pixel(x, y).normal();
                    }
                }
            }
        }
        double gamma = 1.9;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                pixel.normal(image.pixel(x, y).normal() / max);
                pixel.red((int) (pixel.red() * Math.pow(pixel.normal(), (1.0 / gamma))));
                pixel.green((int) (pixel.green() * Math.pow(pixel.normal(), (1.0 / gamma))));
                pixel.blue((int) (pixel.blue() * Math.pow(pixel.normal(), (1.0 / gamma))));
            }
        }

        BufferedImage renderedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.red(), pixel.green(), pixel.blue());
                renderedImage.setRGB(x, y, color.getRGB());
            }
        }



        ImageIO.write(renderedImage, format.name().toLowerCase(), path.toFile());
    }
}
