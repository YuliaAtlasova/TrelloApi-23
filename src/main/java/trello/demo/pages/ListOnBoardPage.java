package trello.demo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ListOnBoardPage extends BasePage {

    @FindBy(className = "list-card-title")
    private WebElement cardNameElement;

    public ListOnBoardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("getCardOnList")
    public CardOnListPage getCardOnList(String cardName) {
        longWait().until(ExpectedConditions.visibilityOf(cardNameElement));
        longWait().until(ExpectedConditions.textToBePresentInElement(cardNameElement, cardName));
        return new CardOnListPage(driver);
    }
}
