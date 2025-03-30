package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;

import java.util.List;

public class CustomerPage extends BasePage {

    @FindBy(css = "a[ng-click*=\"sortType = 'fName'\"]")
    private WebElement firstNameHeader;

    @FindBy(css = "tbody tr td:first-child")
    private List<WebElement> firstNames;

    @FindBy(xpath = "//tbody/tr//button[contains(@ng-click, 'deleteCust')]")
    private List<WebElement> deleteButtons;

    public CustomerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Сортировка клиентов по имени")
    public CustomerPage sortCustomersByFirstName() {
        WaitHelper.waitForElementToBeClickable(driver, firstNameHeader, 10);
        firstNameHeader.click();
        firstNameHeader.click();
        return this;
    }

    @Step("Получение списка имен клиентов")
    public List<String> getCustomerFirstNames() {
        WaitHelper.waitForVisibilityOfAllElements(driver, firstNames, 10);
        return firstNames.stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Удаление клиента с именем: {nameToDelete}")
    public CustomerPage deleteCustomerByName(String nameToDelete) {
        WaitHelper.waitForVisibilityOfAllElements(driver, firstNames, 10);

        for (int i = 0; i < firstNames.size(); i++) {
            String currentName = firstNames.get(i).getText();
            if (currentName.equals(nameToDelete)) {
                WebElement deleteButton = deleteButtons.get(i);
                WaitHelper.waitForElementToBeClickable(driver, deleteButton, 10);
                deleteButton.click();
                WaitHelper.waitForVisibilityOfAllElements(driver, firstNames, 10);
                break;
            }
        }
        return this;
    }
}