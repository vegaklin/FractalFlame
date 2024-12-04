package backend.academy.fractal.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TransformationUtils {
    public static <T> T random(List<T> list, ThreadLocalRandom random) {
        int index = random.nextInt(list.size());
        return list.get(index);
    }
}
