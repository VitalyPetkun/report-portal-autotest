package framework.config;

import org.aeonbits.owner.ConfigFactory;

public class Data {

    private Data() {
    }

    public static DataProperties getValue() {
        DataProperties dataProperties = ConfigFactory.create(DataProperties.class);
        return dataProperties;
    }
}
