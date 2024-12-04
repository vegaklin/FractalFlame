package backend.academy.fractal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.awt.Color;

// передел
//public record Pixel(int r, int g, int b, int hitCount) {
//    public Pixel addHit(int dr, int dg, int db) {
//        return new Pixel(
//            Math.min(255, this.r + dr),
//            Math.min(255, this.g + dg),
//            Math.min(255, this.b + db),
//            this.hitCount + 1
//        );
//    }
//}
@Getter
@Setter
@AllArgsConstructor
public class Pixel { // переделать
    private int red;
    private int green;
    private int blue;
    private int hitCount;
    private double normal;

    public void setRGB(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public void saturateHitCount(Color color) {
        if (hitCount == 0) {
            setRGB(color.getRed(), color.getGreen(), color.getBlue());
        } else {
            red = (red + color.getRed()) / 2;
            green = (green + color.getGreen()) / 2;
            blue = (blue + color.getBlue()) / 2;
        }
        hitCount++;
    }

//    public Pixel addHit(int dr, int dg, int db) {
//        return new Pixel(
//            Math.min(255, this.red + dr),
//            Math.min(255, this.green + dg),
//            Math.min(255, this.blue + db),
//            this.hitCount + 1
//        );
//    }
}
