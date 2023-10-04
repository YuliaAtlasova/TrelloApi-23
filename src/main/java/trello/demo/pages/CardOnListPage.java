package trello.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static trello.demo.utils.TestData.testData;

public class CardOnListPage extends BasePage{

    private final String PAGE_URL = testData().getProperty("trelloBaseUiUrl") + testData().getProperty("userName") +
            "/boards";

    public CardOnListPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ListOnBoardPage getListByName(String listName){
        int listsNumber = getNumberOfListsByName(listName);
        assert listsNumber == 1;
        WebElement list = driver.findElement(By.xpath(getListLocator(listName)));
        return new ListOnBoardPage(driver);
    }
    public int getNumberOfListsByName(String listName){
        String listLoc = getListLocator(listName);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(listLoc)));
        } catch (AssertionError Ae) {
            Ae.printStackTrace();
        }
            List<WebElement> lists = driver.findElements(By.xpath(listLoc));

        return lists.size();
    }

    private String getListLocator(String listName){
        return "//div[contains (@class, 'list-header') and textarea[contains(text(), '" + listName + "')]]";
    }
    @Override
    public CardOnListPage openPage()
    {
        driver.navigate().to(PAGE_URL);
        return this;
    }
}
