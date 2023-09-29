import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.junit.Before;
import trello.demo2.driver.DriverSingleton;

public class BaseUiTest {
    protected WebDriver driver;

    @Before
    public void setUp()
    {
        driver = DriverSingleton.getDriver();
    }

    @After
    public void tearDown()
    {
        DriverSingleton.closeDriver();
    }
}
