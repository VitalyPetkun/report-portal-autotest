package framework.config;

import lombok.Getter;

@Getter
public enum JsonSchema {
    CREATE_DASHBOARD_RESPONSE("Create dashboard response.json"),
    DELETE_DASHBOARD_RESPONSE("Delete dashboard response.json"),
    DASHBOARDS("Dashboards.json"),
    ERROR_RESPONSE("Error response.json");

    private String jsonSchema;

    JsonSchema(String jsonSchema) {
        this.jsonSchema = jsonSchema;
    }
}
