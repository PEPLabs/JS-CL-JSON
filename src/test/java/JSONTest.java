import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.JavascriptExecutor;

public class JSONTest {

    private WebDriver webDriver;

    @BeforeEach
    public void setUp() {
        String browserName = BrowserUtils.getWebDriverName();

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("headless");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("-headless");
                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                webDriver = new EdgeDriver(edgeOptions);
                break;

            case "ie":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.addCommandSwitches("-headless");
                webDriver = new InternetExplorerDriver(ieOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    @Test
    public void testExercise1() {
        File file = new File("src/main/java/index.html");
        String path = "file://" + file.getAbsolutePath();
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String  actual = (String) jsExecutor.executeScript("return exercise1(JSON.parse(arguments[0]));", "{\"food\":\"potato\"}");
        String expected = "{\"food\":\"potato\"}";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testExercise1Again() {
        File file = new File("src/main/java/index.html");
        String path = "file://" + file.getAbsolutePath();
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String actual = (String) jsExecutor.executeScript("return exercise1(JSON.parse(arguments[0]));", "{\"food\":\"potato\", \"animal\":\"koala\", \"movie\":\"cloverfield\"}");
        String expected = "{\"food\":\"potato\",\"animal\":\"koala\",\"movie\":\"cloverfield\"}";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testExercise2() {
        File file = new File("src/main/java/index.html");
        String path = "file://" + file.getAbsolutePath();
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object actual = jsExecutor.executeScript("return exercise2(arguments[0]);", "{\"food\":\"potato\", \"animal\":\"koala\", \"movie\":\"cloverfield\"}");
        String expected = "{animal=koala, food=potato, movie=cloverfield}";
        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    public void testExercise2Again() {
        File file = new File("src/main/java/index.html");
        String path = "file://" + file.getAbsolutePath();
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object actual = jsExecutor.executeScript("return exercise2(arguments[0]);", "{\"food\":\"banana\", \"animal\":\"cat\", \"movie\":\"up\"}");
        String expected = "{animal=cat, food=banana, movie=up}";
        Assertions.assertEquals(expected, actual.toString());
    }

    
}

class BrowserUtils {
    public static String getWebDriverName() {
        String[] browsers = { "chrome", "firefox", "edge", "ie" };

        for (String browser : browsers) {
            try {
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        new ChromeDriver().quit();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        new FirefoxDriver().quit();
                        break;
                    case "edge":
                        WebDriverManager.edgedriver().setup();
                        new EdgeDriver().quit();
                        break;
                    case "ie":
                        WebDriverManager.iedriver().setup();
                        new InternetExplorerDriver().quit();
                        break;
                }
                return browser;
            } catch (Exception e) {
                continue;
            }
        }
        return "Unsupported Browser";
    }
}

