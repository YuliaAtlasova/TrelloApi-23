package trello.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static trello.demo.utils.TestData.testData;

public class BoardsPage extends BasePage {

    private final String PAGE_URL = testData().getProperty("trelloBaseUiUrl") + testData().getProperty("userName") +
            "/boards";

    public BoardsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public OneBoardPage openBoardByName(String boardName) {
        String boardLocator = "//div[@title='" + boardName + "']";
        until(ExpectedConditions.visibilityOfElementLocated(By.xpath(boardLocator)));
        driver.findElement(By.xpath(boardLocator)).click();
        return new OneBoardPage(driver);
    }

    @Override
    public BoardsPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }
}
