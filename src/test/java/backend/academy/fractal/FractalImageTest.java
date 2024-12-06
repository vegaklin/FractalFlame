package backend.academy.fractal;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FractalImageTest {

    private FractalImage fractalImage;

    @BeforeEach
    void setUp() {
        fractalImage = FractalImage.create(5, 3);
    }

    @Test
    void checkFractalImageCorrectDimensionsAndValues() {
        // given-when-then

        assertThat(fractalImage.width()).isEqualTo(5);
        assertThat(fractalImage.height()).isEqualTo(3);
        assertThat(fractalImage.data()).hasSize(5 * 3);

        for (Pixel pixel : fractalImage.data()) {
            assertThat(pixel).isNotNull();
            assertThat(pixel.r()).isEqualTo(0);
            assertThat(pixel.g()).isEqualTo(0);
            assertThat(pixel.b()).isEqualTo(0);
            assertThat(pixel.hitCount()).isEqualTo(0);
            assertThat(pixel.normal()).isEqualTo(0);
        }
    }

    @Test
    void checkFractalImageReturnTrueForValidCoordinates() {
        // given-when-then

        assertThat(fractalImage.contains(0, 0)).isTrue();
        assertThat(fractalImage.contains(4, 2)).isTrue();
        assertThat(fractalImage.contains(2, 1)).isTrue();
    }

    @Test
    void checkFractalImageReturnFalseForInvalidCoordinates() {
        // given-when-then

        assertThat(fractalImage.contains(-1, 0)).isFalse();
        assertThat(fractalImage.contains(5, 0)).isFalse();
        assertThat(fractalImage.contains(0, -2)).isFalse();
        assertThat(fractalImage.contains(5, 3)).isFalse();
    }

    @Test
    void checkFractalImageReturnPixelForValidCoordinates() {
        // given-when

        Pixel pixel = fractalImage.pixel(1, 1);

        // then

        assertThat(pixel).isNotNull();
        assertThat(pixel.r()).isEqualTo(0);
        assertThat(pixel.g()).isEqualTo(0);
        assertThat(pixel.b()).isEqualTo(0);
        assertThat(pixel.hitCount()).isEqualTo(0);
        assertThat(pixel.normal()).isEqualTo(0);
    }

    @Test
    void checkFractalImageReturnNullForInvalidCoordinates() {
        // given-when-then

        assertThat(fractalImage.pixel(-1, 0)).isNull();
        assertThat(fractalImage.pixel(5, 0)).isNull();
        assertThat(fractalImage.pixel(0, -2)).isNull();
        assertThat(fractalImage.pixel(5, 3)).isNull();
    }
}
