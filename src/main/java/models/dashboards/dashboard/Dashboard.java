package models.dashboards.dashboard;

import lombok.*;
import models.dashboards.dashboard.widget.Widget;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {
    private String owner;
    private int id;
    private String name;
    private List<Widget> widgets;
    private String description;

    public Dashboard(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Dashboard dashboard = (Dashboard) object;

        return Objects.equals(this.id, dashboard.getId()) &&
                Objects.equals(this.name, dashboard.getName()) &&
                Objects.equals(this.description, dashboard.getDescription());
    }
}
