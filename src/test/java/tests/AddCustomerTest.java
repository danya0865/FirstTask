package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ManagerPage;
import utils.HelperUtils;

@Feature("Customer Management")
public class AddCustomerTest extends BaseTest {

    @Test
    @Story("Add Customer")
    @Description("Verify that a customer can be added successfully.")
    public void testAddCustomer() {
        openUrl("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/addCust");

        String postCode = HelperUtils.generatePostCode();
        String firstName = HelperUtils.generateFirstName(postCode);
        String lastName = "lastName";

        String alertText = new ManagerPage(driver)
                .fillCustomerDetails(firstName, lastName, postCode)
                .submitCustomerForm()
                .getAlertText();

        Assert.assertTrue(alertText.contains("Customer added successfully"), "Customer was not added.");
    }
}