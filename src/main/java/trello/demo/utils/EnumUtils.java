package trello.demo.utils;

import java.util.Random;

public class EnumUtils {
    public static <T extends Enum<?>> T randomValue(Class<T> clazz){
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
