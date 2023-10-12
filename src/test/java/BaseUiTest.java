import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import trello.demo.driver.DriverSingleton;

import static trello.demo.utils.TestData.testData;

public class BaseUiTest {
    protected WebDriver driver;
    public static final String LOGIN = testData().getProperty("userLogin");
    public static final String PASSWORD = testData().getProperty("userPassword");

    @Before
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @After
    public void tearDown() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void mockTest(){
        System.out.println("here we are");
        System.out.println("here we are");
    }
}
