package trello.demo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardOnListPage extends BasePage {

    @FindBy(className = "list-card-title")
    private WebElement cardName;
    private final String checkListIconLoc = "//div[contains(@data-testid, 'checklist-badge')]";
    private final String checkListCompletedLoc = "//div[contains(@class, 'is-complete')]";

    public CardOnListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
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
}
