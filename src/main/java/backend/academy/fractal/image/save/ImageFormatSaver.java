package backend.academy.fractal.image.save;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import lombok.extern.log4j.Log4j2;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

@Log4j2
public class ImageFormatSaver implements ImageSaver {
    private final ImageFormat imageFormat;

    public ImageFormatSaver(ImageFormat imageFormat) {
        this.imageFormat = imageFormat;
    }

    @Override
    public void save(FractalImage image, Path filename) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.r(), pixel.g(), pixel.b());
                bufferedImage.setRGB(x, y, color.getRGB());
            }
        }

        try {
            ImageIO.write(bufferedImage, imageFormat.name().toLowerCase(), filename.toFile());
        } catch (IOException e) {
            log.error("File saving error: ", e);
        }
    }
}
