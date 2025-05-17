package framework.utils;

import net.datafaker.Faker;

public class DataGenerator {
    private static Faker faker;

    private DataGenerator() {
        faker = new Faker();
    }

    private static Faker getFaker() {
        if (faker == null)
            new DataGenerator();
        return faker;
    }

    public static String getString(int min, int max) {
        return getFaker().regexify(String.format(
                "[a-zA-Z0-9_]{%d}",
                (int) (Math.random() * (max - min)) + min
        ));
    }
}
