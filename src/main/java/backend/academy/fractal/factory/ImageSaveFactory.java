package backend.academy.fractal.factory;

import backend.academy.fractal.image.save.ImageFormat;
import backend.academy.fractal.image.save.ImageFormatSaver;
import backend.academy.fractal.image.save.ImageSaver;

public class ImageSaveFactory {

    public static ImageSaver createImageSaver(ImageFormat format) {
        return switch (format) {
            case PNG -> new ImageFormatSaver(ImageFormat.PNG);
            case BMP -> new ImageFormatSaver(ImageFormat.BMP);
            case JPEG -> new ImageFormatSaver(ImageFormat.JPEG);
        };
    }
}
