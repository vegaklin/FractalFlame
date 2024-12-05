package backend.academy.fractal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.awt.Color;

@Getter
@Setter
@AllArgsConstructor
public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;

    public void pixelProcessing(Color color) {
        if (hitCount == 0) {
            this.r = color.getRed();
            this.g = color.getGreen();
            this.b = color.getBlue();
        } else {
            r = (r + color.getRed()) / 2;
            g = (g + color.getGreen()) / 2;
            b = (b + color.getBlue()) / 2;
        }
        hitCount++;
    }
}
