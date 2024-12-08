package backend.academy.fractal;

import backend.academy.fractal.factory.FractalGeneratorFactory;
import backend.academy.fractal.fractal.FractalGenerator;
import backend.academy.fractal.image.correction.GammaCorrection;
import backend.academy.fractal.image.correction.ImageProcessor;
import backend.academy.fractal.image.save.ImageFormatSaver;
import backend.academy.fractal.image.save.ImageSaver;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.render.OneThreadRenderer;
import backend.academy.fractal.render.Renderer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FractalGeneratorFactoryTest {

    @Test
    void checkFractalGeneratorCorrectlyFromSampleConfigFile() {
        // given
        String configFilePath = "sample_application.yaml";

        // when
        FractalGenerator generator = FractalGeneratorFactory.createFractalGenerator(configFilePath);

        // then
        assertNotNull(generator);

        assertEquals(800, generator.canvas().width());
        assertEquals(100, generator.canvas().height());

        Rect world = generator.world();
        assertEquals(-2.0, world.x());
        assertEquals(-1.0, world.y());
        assertEquals(4.0, world.width());
        assertEquals(2.0, world.height());

        Renderer renderer = generator.renderer();
        assertNotNull(renderer);
        assertInstanceOf(OneThreadRenderer.class, renderer);

        ImageProcessor imageProcessor = generator.imageProcessor();
        assertNotNull(imageProcessor);
        assertInstanceOf(GammaCorrection.class, imageProcessor);

        ImageSaver imageSaver = generator.imageSaver();
        assertNotNull(imageSaver);
        assertInstanceOf(ImageFormatSaver.class, imageSaver);

        ImageFormatSaver formatSaver = (ImageFormatSaver) imageSaver;
        assertEquals("PNG", formatSaver.imageFormat().name());
    }
}
