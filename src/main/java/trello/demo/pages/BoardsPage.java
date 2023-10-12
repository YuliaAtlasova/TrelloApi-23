package trello.demo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BoardsPage extends BasePage {

    public BoardsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("openBoardByName {boardName}")
    public OneBoardPage openBoardByName(String boardName) {
        String boardLocator = "//div[@title='" + boardName + "']";
        longWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(boardLocator)));
        driver.findElement(By.xpath(boardLocator)).click();
        return new OneBoardPage(driver);
    }
}
