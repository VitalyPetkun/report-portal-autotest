package framework.utils;

import net.datafaker.Faker;


public class DataGenerator {
    private static final Faker faker = new Faker();

    private DataGenerator() {
    }

    public static String getString(int min, int max) {
        return faker.regexify(String.format(
                "[a-zA-Z0-9_]{%d}",
                (int) (Math.random() * (max - min)) + min
        ));
    }
}
