package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomerPage;

public class SortCustomersTest extends BaseTest {

    @Test
    @Epic("Banking Application")
    @Feature("Customer Management")
    @Story("Sort Customers")
    @Description("Verify that customers can be sorted by First Name.")
    public void testSortCustomersByFirstName() {
        openUrl("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list");

        CustomerPage customerPage = new CustomerPage(driver);

        Assert.assertEquals(
                customerPage.getCustomerFirstNamesAfterSorting(),
                customerPage.getCustomerFirstNames().stream().sorted().toList(),
                "Customers are not sorted correctly."
        );
    }
}