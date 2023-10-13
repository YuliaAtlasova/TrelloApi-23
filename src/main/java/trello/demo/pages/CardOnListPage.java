package trello.demo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardOnListPage extends BasePage {

    private final String checkListIconLoc = "//div[contains(@data-testid, 'checklist-badge')]";
    private final String checkListCompletedLoc = "//div[contains(@class, 'is-complete')]";
    @FindBy(className = "list-card-title")
    private WebElement cardName;

    public CardOnListPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("isCheckListIconPresent")
    public boolean isCheckListIconPresent() {
        return isElementExists(checkListIconLoc);
    }

    @Step("isCheckListCompleted")
    public boolean isCheckListCompleted() {
        return isElementExists(checkListCompletedLoc);
    }
}
