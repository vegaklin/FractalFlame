package backend.academy.fractal.render;

import backend.academy.fractal.model.FractalImage;
import backend.academy.fractal.model.Pixel;
import backend.academy.fractal.model.Point;
import backend.academy.fractal.model.Rect;
import backend.academy.fractal.transformation.AffineTransformation;
import backend.academy.fractal.transformation.Transformation;
import backend.academy.fractal.utils.ImageUtil;
import backend.academy.fractal.utils.RendererUtils;
import backend.academy.fractal.utils.RectUtils;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import static backend.academy.fractal.constant.FractalConstants.STEPS_FOR_CORRECTION;
import static backend.academy.fractal.utils.RendererUtils.generateRandomAffineTransformations;

public abstract class FractalRenderer implements Renderer {

    protected final List<Transformation> variations;
    protected final int samples;
    protected final int iterPerSample;

    public FractalRenderer(
        List<Transformation> variations,
        int samples,
        int iterPerSample
    ) {
        this.variations = variations;
        this.samples = samples;
        this.iterPerSample = iterPerSample;
    }

    @Override
    public FractalImage render(Rect world, int imageWidth, int imageHeight) {
        FractalImage image = FractalImage.create(imageWidth, imageHeight);
        List<AffineTransformation> affineTransformations = generateRandomAffineTransformations();

        renderSamples(image, world, affineTransformations);
        return image;
    }

    public abstract void renderSamples(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    );

    protected void renderOneSample(
        FractalImage canvas,
        Rect world,
        List<? extends AffineTransformation> affineTransformations
    ) {
        Point currentPoint = RectUtils.randomPointInRect(world, ThreadLocalRandom.current());

        for (int step = -STEPS_FOR_CORRECTION; step < iterPerSample; step++) {
            AffineTransformation affineTransformation = RendererUtils.randomTransformation(affineTransformations, ThreadLocalRandom.current());
            Transformation transformation = RendererUtils.randomTransformation(variations, ThreadLocalRandom.current());

            currentPoint = affineTransformation.apply(currentPoint);
            currentPoint = transformation.apply(currentPoint);

            if (step > 0 && world.contains(currentPoint)) {
                int x = mapRange(world.x(), world.width(), 0, canvas.width(), currentPoint.x());
                int y = mapRange(world.y(), world.height(), 0, canvas.height(), currentPoint.y());

                if (canvas.contains(x, y)) {
                    Color color = affineTransformation.affineCoefficient().color();
                    Pixel pixel = canvas.pixel(x, y);
                    pixel.saturateHitCount(color);

//                    double theta = 0.0;
//                    for (int chunk = 0; chunk < 6; theta += Math.PI * 2 / 6, ++chunk) {
//                        var point = RectUtils.rotatePoint(world, currentPoint, theta);
//                        int x = mapRange(world.x(), world.width(), 0, canvas.width(), point.x());
//                        int y = mapRange(world.y(), world.height(), 0, canvas.height(), point.y());
//                        if (canvas.contains(x, y)) {
//                            Color color = affine.affineCoefficient().color();
//                            Pixel pixel = canvas.pixel(x, y);
//                            pixel.saturateHitCount(color);
//                        }
                    }

            }
        }

//        return canvas;
    }

    private int mapRange(double srcMin, double srcMax, int dstMin, int dstMax, double value) {
//        return dstMax + (int) (((srcMax - value) / (srcMax - srcMin)) * dstMax);
        return (int) ((value - srcMin) / srcMax * dstMax);
    }

//    private void processPoint(Rect world, FractalImage image, Point point, AffineTransformation affine) {
//        if (!world.contains(point)) {
//            return;
//        }
//        Pixel pixel = ImageUtil.resolvePixel(world, point, image);
//        if (pixel != null) {
//            synchronized (pixel) {
//                Color color = affine.affineCoefficient().color();
//                pixel.saturateHitCount(color);
//            }
//        }
//    }
//    public FractalImage render(
//        FractalImage canvas,
//        Rect world,
//        List<Transformation> variations,
//        int samples,
//        int iterPerSample,
//        long seed
//    ) {
//        Random random = new Random(seed);
//
//        for (int num = 0; num < samples; ++num) {
//            Point point = randomPoint(world, random);
//
//            for (int step = 0; step < iterPerSample; ++step) {
//                Transformation transformation = variations.get(random.nextInt(variations.size()));
//                point = transformation.apply(point);
//
//                if (!world.contains(point)) continue;
//
//                int x = (int) mapRange(world.x(), world.width(), 0, canvas.width(), point.x());
//                int y = (int) mapRange(world.y(), world.height(), 0, canvas.height(), point.y());
//
//                if (canvas.contains(x, y)) {
//                    Pixel pixel = canvas.pixel(x, y);
//                    canvas.setPixel(x, y, pixel.addHit(10, 20, 30)); // Подмешивание цвета
//                }
//            }
//        }
//
//        return canvas;
//    }
//
//    private Point randomPoint(Rect world, Random random) {
//        double x = world.x() + world.width() * random.nextDouble();
//        double y = world.y() + world.height() * random.nextDouble();
//        return new Point(x, y);
//    }
//
//    private double mapRange(double srcMin, double srcMax, double dstMin, double dstMax, double value) {
//        return dstMin + (dstMax - dstMin) * ((value - srcMin) / (srcMax - srcMin));
//    }
}

