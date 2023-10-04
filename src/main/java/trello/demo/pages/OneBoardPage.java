package trello.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static trello.demo.utils.TestData.testData;

public class OneBoardPage extends BasePage{

    private final String PAGE_URL = testData().getProperty("trelloBaseUiUrl") + testData().getProperty("userName") +
            "/boards";

    @FindBy(id = "login_field")
    private WebElement inputLogin;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@value='Sign in']")
    private WebElement buttonSubmit;



    public OneBoardPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }



    @Override
    public OneBoardPage openPage()
    {
        driver.navigate().to(PAGE_URL);
        return this;
    }
}
