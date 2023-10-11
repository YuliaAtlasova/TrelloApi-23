package trello.demo.pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static trello.demo.utils.TestData.testData;

public class ListOnBoardPage extends BasePage {

    private final String PAGE_URL = testData().getProperty("trelloBaseUiUrl") + testData().getProperty("userName") + "/boards";
    @FindBy(className = "list-card-title")
    private WebElement cardNameElement;

    public ListOnBoardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("getCardOnList")
    public CardOnListPage getCardOnList(String cardName) {
        until(ExpectedConditions.visibilityOf(cardNameElement));
        until(ExpectedConditions.textToBePresentInElement(cardNameElement, cardName));
        return new CardOnListPage(driver);
    }

    @Override
    @Step("openPage ListOnBoardPage")
    public ListOnBoardPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }
}
