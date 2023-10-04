import org.openqa.selenium.WebDriver;
import trello.demo.driver.DriverSingleton;
import org.junit.Before;
import org.junit.After;

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
