package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomerPage;
import java.util.List;

@Feature("Customer Management")
public class SortCustomersTest extends BaseTest {

    @Test
    @Story("Sort Customers")
    @Description("Verify that customers can be sorted by First Name.")
    public void testSortCustomersByFirstName() {
        openUrl("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list");

        List<String> namesAfterSorting = new CustomerPage(driver)
                .sortCustomersByFirstName()
                .getCustomerFirstNames();

        Assert.assertEquals(
                namesAfterSorting,
                namesAfterSorting.stream().sorted().toList(),
                "Customers are not sorted correctly."
        );
    }
}