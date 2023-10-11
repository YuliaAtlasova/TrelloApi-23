package trello.demo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static trello.demo.utils.TestData.testData;

public class CardOnListPage extends BasePage {

    private final String PAGE_URL = testData().getProperty("trelloBaseUiUrl") + testData().getProperty("userName");
    @FindBy(className = "list-card-title")
    private WebElement cardName;
    private final String checkListIconLoc = "//div[contains(@data-testid, 'checklist-badge')]";
    private final String checkListCompletedLoc = "//div[contains(@class, 'is-complete')]";

    public CardOnListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getCardName() {
        until(ExpectedConditions.visibilityOf(cardName));
        return cardName.getText();
    }

    @Step("isCheckListIconPresent")
    public boolean isCheckListIconPresent() {
        try {
            driver.findElement(By.xpath(checkListIconLoc));
            return true;
        } catch (NoSuchElementException ignored) {
        }
        return false;
    }

    public boolean isCheckListCompleted() {
        try {
            driver.findElement(By.xpath(checkListCompletedLoc));
            return true;
        } catch (NoSuchElementException ignored) {
        }
        return false;
    }

    @Override
    @Step("openPage CardOnListPage")
    public CardOnListPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }
}
