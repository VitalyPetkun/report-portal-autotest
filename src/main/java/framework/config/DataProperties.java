package framework.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:./src/main/resources/config.properties",
        "file:./src/test/resources/testData.properties"})
public interface DataProperties extends Config {
    String baseUrl();
    String browser();
    Long timeout();

    String login();
    String password();
}
