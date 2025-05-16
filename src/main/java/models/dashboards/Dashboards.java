package models.dashboards;

import lombok.Data;
import models.Page;
import models.dashboards.dashboard.Dashboard;

import java.util.List;

@Data
public class Dashboards {
    private List<Dashboard> content;
    private Page page;
}
