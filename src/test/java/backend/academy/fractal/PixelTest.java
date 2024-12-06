package backend.academy.fractal;

import backend.academy.fractal.model.Pixel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.assertj.core.api.Assertions.assertThat;

public class PixelTest {

    private Pixel pixel;

    @BeforeEach
    void setUp() {
        pixel = new Pixel(0, 0, 0, 0, 0);
    }

    @Test
    void checkPixelProcessingForEmptyPixel() {
        // given

        Color color = new Color(255, 100, 25);

        // when
        pixel.pixelProcessing(color);

        // then

        assertThat(pixel.r()).isEqualTo(255);
        assertThat(pixel.g()).isEqualTo(100);
        assertThat(pixel.b()).isEqualTo(25);
        assertThat(pixel.hitCount()).isEqualTo(1);
    }

    @Test
    void checkPixelProcessingForInitializedPixel() {
        // given

        pixel.r(100);
        pixel.g(123);
        pixel.b(222);
        pixel.hitCount(1);
        Color newColor = new Color(200, 100, 25);

        // when

        pixel.pixelProcessing(newColor);

        // then

        assertThat(pixel.r()).isEqualTo((100 + 200) / 2);
        assertThat(pixel.g()).isEqualTo((123 + 100) / 2);
        assertThat(pixel.b()).isEqualTo((222 + 25) / 2);
        assertThat(pixel.hitCount()).isEqualTo(2);
    }

    @Test
    void checkPixelProcessingSeveralTimesForPixel() {
        // given

        Color firstColor = new Color(255, 255, 255);
        Color secondColor = new Color(12, 23, 34);
        Color thirdColor = new Color(111, 111, 111);

        // when
        pixel.pixelProcessing(firstColor);
        pixel.pixelProcessing(secondColor);
        pixel.pixelProcessing(thirdColor);

        // then

        assertThat(pixel.r()).isEqualTo(((255 + 12) / 2 + 111) / 2);
        assertThat(pixel.g()).isEqualTo(((255 + 23) / 2 + 111) / 2);
        assertThat(pixel.b()).isEqualTo(((255 + 34) / 2 + 111) / 2);
        assertThat(pixel.hitCount()).isEqualTo(3);
    }
}
