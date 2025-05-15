package framework.utils;

import net.datafaker.Faker;

public class DataGenerator {
    private static final Faker faker = new Faker();

    private DataGenerator() {
    }

    public static String getTitle() {
        return faker.name().title();
    }
}
