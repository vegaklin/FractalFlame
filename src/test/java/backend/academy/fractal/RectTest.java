package backend.academy.fractal;

import backend.academy.fractal.model.Point;
import backend.academy.fractal.model.Rect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RectTest {

    private Rect rect;

    @BeforeEach
    void setUp() {
        rect = new Rect(0, 0, 10, 10);
    }

    @Test
    void shouldDeterminePointInsideRectangle() {
        // given

        Point pont1 = new Point(5, 5);
        Point pont2 = new Point(0, 0);
        Point pont3 = new Point(9, 9);
        Point pont4 = new Point(3, 7);

        // when

        boolean result1 = rect.contains(pont1);
        boolean result2 = rect.contains(pont2);
        boolean result3 = rect.contains(pont3);
        boolean result4 = rect.contains(pont4);

        // then

        assertThat(result1).isTrue();
        assertThat(result2).isTrue();
        assertThat(result3).isTrue();
        assertThat(result4).isTrue();
    }

    @Test
    void shouldDeterminePointOnEdgeOfRectangle() {
        // given

        Point pont1 = new Point(-1, 5);
        Point pont2 = new Point(0, -1);
        Point pont3 = new Point(10, 10);
        Point pont4 = new Point(5, 10);

        // when

        boolean result1 = rect.contains(pont1);
        boolean result2 = rect.contains(pont2);
        boolean result3 = rect.contains(pont3);
        boolean result4 = rect.contains(pont4);

        // then

        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        assertThat(result4).isFalse();
    }
}
