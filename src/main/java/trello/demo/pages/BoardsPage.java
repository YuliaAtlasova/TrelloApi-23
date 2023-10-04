package trello.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;

import static trello.demo.utils.TestData.testData;

public class BoardsPage extends BasePage{

    private final String PAGE_URL = testData().getProperty("trelloBaseUiUrl") + testData().getProperty("userName") +
            "/boards";

    public BoardsPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public OneBoardPage openBoardByName(String boardName){
        try {
            String boardLocator = "//div[@title='"+ boardName +"']";
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(boardLocator)));
            driver.findElement(By.xpath(boardLocator)).click();
        } catch (AssertionError Ae) {
            Ae.printStackTrace();
        }
        return new OneBoardPage(driver);
    }

    @Override
    public BoardsPage openPage()
    {
        driver.navigate().to(PAGE_URL);
        return this;
    }
}
