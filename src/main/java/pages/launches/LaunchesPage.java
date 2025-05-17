package pages.launches;

import framework.elements.Button;
import org.openqa.selenium.By;
import pages.sidebar.SidebarForm;

public class LaunchesPage extends SidebarForm {
    private static final String ALL_LAUNCHES_BTN =
            "//div[contains(@class,'launchFiltersToolbar')]/div[starts-with(@class,'all')]/div";

    public LaunchesPage() {
        super(new Button(By.xpath(ALL_LAUNCHES_BTN), "'All launches' button"), "Launches page");
    }
}
