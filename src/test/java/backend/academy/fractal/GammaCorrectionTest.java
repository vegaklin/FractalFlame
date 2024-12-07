package backend.academy.fractal;

import backend.academy.fractal.image.correction.GammaCorrection;
import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GammaCorrectionTest {

    @Test
    public void checkGammaCorrectionEmptyPixel() {
        // given

        FractalImage image = FractalImage.create(2, 2);

        // when

        GammaCorrection gammaCorrection = new GammaCorrection(2.2);
        gammaCorrection.process(image);

        // then

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                assertThat(pixel.r()).isEqualTo(0);
                assertThat(pixel.g()).isEqualTo(0);
                assertThat(pixel.b()).isEqualTo(0);
                assertThat(pixel.hitCount()).isEqualTo(0);
            }
        }
    }

    @Test
    public void checkGammaCorrectionOneFillPixel() {
        // given

        FractalImage image = FractalImage.create(2, 2);

        Pixel pixel = image.pixel(0, 0);
        pixel.r(100);
        pixel.g(100);
        pixel.b(100);
        pixel.hitCount(10000);

        // when

        GammaCorrection gammaCorrection = new GammaCorrection(0.5);
        gammaCorrection.process(image);

        // then

        pixel = image.pixel(0, 0);
        assertThat(pixel.normal()).isEqualTo(1);
        assertThat(pixel.hitCount()).isEqualTo(10000);
        assertThat(pixel.r()).isEqualTo(100);
        assertThat(pixel.g()).isEqualTo(100);
        assertThat(pixel.b()).isEqualTo(100);
    }

    @Test
    public void checkGammaCorrectionTwoFillPixel() {
        // given

        FractalImage image = FractalImage.create(2, 2);

        Pixel pixel = image.pixel(0, 0);
        pixel.r(100);
        pixel.g(100);
        pixel.b(100);
        pixel.hitCount(100);

        pixel = image.pixel(1, 1);
        pixel.r(100);
        pixel.g(100);
        pixel.b(100);
        pixel.hitCount(10000);

        // when

        GammaCorrection gammaCorrection = new GammaCorrection(0.5);
        gammaCorrection.process(image);

        // then

        pixel = image.pixel(0, 0);
        assertThat(pixel.normal()).isEqualTo(0.5);
        assertThat(pixel.hitCount()).isEqualTo(100);
        assertThat(pixel.r()).isEqualTo(25);
        assertThat(pixel.g()).isEqualTo(25);
        assertThat(pixel.b()).isEqualTo(25);
    }
}
