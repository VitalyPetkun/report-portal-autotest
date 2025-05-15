package steps.launches;

import org.testng.Assert;
import pages.launches.LaunchesPage;

public class LaunchesPageSteps {
    private static LaunchesPage launchesPage = new LaunchesPage();

    private LaunchesPageSteps() {
    }

    public static void openDashboardsPage() {
        launchesPage.clickDashboardsBtn();
    }

    public static void assertIsOpen() {
        Assert.assertTrue(launchesPage.isFormOpen(), launchesPage.getFormName().concat(" isn't open"));
    }
}
