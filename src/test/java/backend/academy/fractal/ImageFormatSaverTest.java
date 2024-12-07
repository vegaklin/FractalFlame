package backend.academy.fractal;

import backend.academy.fractal.image.save.ImageFormat;
import backend.academy.fractal.image.save.ImageFormatSaver;
import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static backend.academy.fractal.constant.FractalConstants.IMAGE_PATH;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageFormatSaverTest {

    @AfterEach
    void cleanup() {
        for (ImageFormat format : ImageFormat.values()) {
            File file = new File(IMAGE_PATH + "." + format.name().toLowerCase());
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @Test
    void testSaveJPEG() throws IOException {
        testImageSavingGivenFormat(ImageFormat.JPEG);
    }

    @Test
    void testSavePNG() throws IOException {
        testImageSavingGivenFormat(ImageFormat.PNG);
    }

    @Test
    void testSaveBMP() throws IOException {
        testImageSavingGivenFormat(ImageFormat.BMP);
    }

    private void testImageSavingGivenFormat(ImageFormat format) throws IOException {
        // given

        FractalImage image = createTestImage(10, 5);
        ImageFormatSaver saver = new ImageFormatSaver(format);

        // when

        saver.save(image);

        // then

        File savedFile = new File(IMAGE_PATH + "." + format.name().toLowerCase());
        assertTrue(savedFile.exists());

        BufferedImage savedImage = javax.imageio.ImageIO.read(savedFile);
        assertThat(savedImage.getWidth()).isEqualTo(image.width());
        assertThat(savedImage.getHeight()).isEqualTo(image.height());
    }

    private FractalImage createTestImage(int width, int height) {
        FractalImage image = FractalImage.create(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixel = image.pixel(x, y);
                pixel.pixelProcessing(new Color(x * 2 % 255, y * 3 % 255, x * y * 4 % 255));
            }
        }
        return image;
    }
}
