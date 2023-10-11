package trello.demo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static trello.demo.utils.TestData.testData;

public class OneBoardPage extends BasePage {

    private final String PAGE_URL = testData().getProperty("trelloBaseUiUrl") + testData().getProperty("userName") +
            "/boards";

    public OneBoardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private String getListLocator(String listName) {
        return "//div[contains (@class, 'list-header') and textarea[contains(text(), '" + listName + "')]]";
    }

    @Step("getListByName")
    public ListOnBoardPage getListByName(String listName) {
        int listsNumber = getNumberOfListsByName(listName);
        assert listsNumber == 1;
        WebElement list = driver.findElement(By.xpath(getListLocator(listName)));
        return new ListOnBoardPage(driver);
    }

    @Step("getNumberOfListsByName")
    public int getNumberOfListsByName(String listName) {
        String listLoc = getListLocator(listName);
        until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(listLoc)));
        List<WebElement> lists = driver.findElements(By.xpath(listLoc));

        return lists.size();
    }

    @Override
    @Step("openPage OneBoardPage")
    public OneBoardPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }
}
