package trello.demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestData {
    private static final String resourcesPath = "src/resources/test.properties";

    public static Properties testData() {
        Properties testProperties = new Properties();
        try (FileInputStream fin = new FileInputStream(resourcesPath)) {
            testProperties.load(fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testProperties;
    }
}
