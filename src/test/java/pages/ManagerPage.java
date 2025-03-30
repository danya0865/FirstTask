package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;

public class ManagerPage extends BasePage {
    @FindBy(xpath = "//input[@ng-model='fName']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@ng-model='lName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@ng-model='postCd']")
    private WebElement postCodeInput;

    @FindBy(css = "button[type='submit'].btn.btn-default")
    private WebElement submitButton;

    public ManagerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение данных клиента: имя = {0}, фамилия = {1}, почтовый индекс = {2}")
    public ManagerPage fillCustomerDetails(String firstName, String lastName, String postCode) {
        WaitHelper.waitForVisibilityOfElement(driver, firstNameInput, 10);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postCodeInput.sendKeys(postCode);
        return this;
    }

    @Step("Отправка формы добавления клиента")
    public ManagerPage submitCustomerForm() {
        submitButton.click();
        return this;
    }

    @Step("Получение текста алерта")
    public String getAlertText() {
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return text;
    }
}