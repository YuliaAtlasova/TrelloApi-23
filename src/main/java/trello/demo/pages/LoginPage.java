package trello.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static trello.demo.utils.TestData.testData;

public class LoginPage extends BasePage{

    private final String PAGE_URL = testData().getProperty("trelloBaseUiUrl") + "/login";

    @FindBy(id = "user")
    private WebElement inputLogin;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "login")
    private WebElement usernameSubmit;

    @FindBy(id = "login-submit")
    private WebElement loginSubmit;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LoginPage openPage()
    {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public BoardsPage login(String username, String password)
    {
        inputLogin.sendKeys(username);
        usernameSubmit.click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.sendKeys(password);
        loginSubmit.click();
        return new BoardsPage(driver);
    }
}
