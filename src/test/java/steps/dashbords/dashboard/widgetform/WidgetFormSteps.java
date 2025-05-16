package steps.dashbords.dashboard.widgetform;

import org.testng.Assert;
import pages.dashboards.dashboard.widgetform.WidgetForm;

public class WidgetFormSteps {
    private static final WidgetForm widgetForm = new WidgetForm();

    private WidgetFormSteps() {
    }

    public static void addNewWidget(String widgetType, String widgetFilterName, String widgetName) {
        widgetForm
                .clickWidgetTypeLbl(widgetType)
                .clickNextStepBtn()
                .clickFilterLbl(widgetFilterName)
                .clickNextStepBtn()
                .inputWidgetNameTxtBox(widgetName)
                .clickAddBtn();
    }

    public static void assertIsOpen() {
        Assert.assertTrue(widgetForm.isFormOpen(), widgetForm.getFormName().concat(" isn't open"));
    }
}
