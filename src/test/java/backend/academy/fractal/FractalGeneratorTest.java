package backend.academy.fractal;

import backend.academy.fractal.fractal.FractalGenerator;
import backend.academy.fractal.image.correction.ImageProcessor;
import backend.academy.fractal.image.save.ImageSaver;
import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.render.Renderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FractalGeneratorTest {

    @Mock
    private Renderer renderer;

    @Mock
    private ImageProcessor imageProcessor;

    @Mock
    private ImageSaver imageSaver;

    @Mock
    private FractalImage canvas;

    @Mock
    private Rect world;

    @InjectMocks
    private FractalGenerator generator;

    @Test
    void testStartMethod() {
        // given

        FractalImage renderedImage = mock(FractalImage.class);
        when(renderer.render(world, canvas)).thenReturn(renderedImage);

        // when

        generator.start();

        // then

        verify(renderer).render(world, canvas);
        verify(imageProcessor).process(renderedImage);
        verify(imageSaver).save(renderedImage);
    }
}
