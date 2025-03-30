package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomerPage;
import java.util.List;

@Feature("Customer Management")
public class DeleteCustomerTest extends BaseTest {

    @Test
    @Story("Delete Customer")
    @Description("Verify that a customer can be deleted successfully.")
    public void testDeleteCustomer() {
        openUrl("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list");

        CustomerPage customerPage = new CustomerPage(driver);
        List<String> customerNames = customerPage.getCustomerFirstNames();
        Assert.assertFalse(customerNames.isEmpty(), "No customers found in the table.");

        String nameToDelete = findCustomerToDelete(customerNames);

        customerPage.deleteCustomerByName(nameToDelete);

        List<String> updatedCustomerNames = customerPage.getCustomerFirstNames();
        Assert.assertFalse(updatedCustomerNames.contains(nameToDelete), "Customer was not deleted.");
    }

    private String findCustomerToDelete(List<String> customerNames) {
        double averageLength = customerNames.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);

        return customerNames.stream()
                .min((name1, name2) -> {
                    double diff1 = Math.abs(name1.length() - averageLength);
                    double diff2 = Math.abs(name2.length() - averageLength);
                    return Double.compare(diff1, diff2);
                })
                .orElseThrow(() -> new AssertionError("Failed to find a customer to delete."));
    }
}