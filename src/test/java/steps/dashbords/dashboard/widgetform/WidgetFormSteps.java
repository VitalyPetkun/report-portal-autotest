package steps.dashbords.dashboard.widgetform;

import framework.utils.DataGenerator;
import org.testng.Assert;
import pages.dashboards.dashboard.widgetform.WidgetForm;

public class WidgetFormSteps {
    private static final WidgetForm widgetForm = new WidgetForm();

    private WidgetFormSteps() {
    }

    public static String addNewWidget(String widgetType, String widgetFilterName) {
        String widgetName = DataGenerator.getTitle();

        widgetForm
                .clickWidgetTypeLbl(widgetType)
                .clickNextStepBtn()
                .clickFilterLbl(widgetFilterName)
                .clickNextStepBtn()
                .inputWidgetNameTxtBox(widgetName)
                .clickAddBtn();

        return widgetName;
    }

    public static void assertIsOpen() {
        Assert.assertTrue(widgetForm.isFormOpen(), widgetForm.getFormName().concat(" isn't open"));
    }
}
