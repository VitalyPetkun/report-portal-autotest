package steps.dashbords.dashboard;

import org.testng.Assert;
import pages.dashboards.dashboard.DeleteWidgetForm;

public class DeleteWidgetFormSteps {
    private static final DeleteWidgetForm deleteWidgetForm = new DeleteWidgetForm();

    private DeleteWidgetFormSteps() {
    }

    public static void clickDeleteBtn() {
        deleteWidgetForm.clickDeleteBtn();
    }

    public static void assertIsOpen() {
        Assert.assertTrue(deleteWidgetForm.isFormOpen(), deleteWidgetForm.getFormName().concat(" isn't open"));
    }
}
