package backend.academy.fractal.model;

import java.util.Arrays;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];
        for (int i = 0; i < height * width; i++) {
            data[i] = new Pixel(0, 0, 0, 0, 0);
        }
        return new FractalImage(data, width, height);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Pixel pixel(int x, int y) {
        if (!contains(x, y)) {
            return null;
        }
        return data[y * width + x];
    }

    // убрать
//    public void setPixel(int x, int y, Pixel pixel) {
//        if (contains(x, y)) {
//            data[y * width + x] = pixel;
//        }
//    }
}
