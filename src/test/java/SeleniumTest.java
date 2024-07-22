import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.JavascriptExecutor;

public class SeleniumTest {

    private WebDriver webDriver;
    private String path;

    @BeforeEach
    public void setUp() {
        
         // Set up ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");

        // Get file
        File file = new File("src/main/java/index.html");
        path = "file://" + file.getAbsolutePath();

        // Create a new ChromeDriver instance
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        webDriver = new ChromeDriver(options);

        // Open the HTML file
        webDriver.get(path);
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        webDriver.quit();
    }  
    

    @Test
    public void testExercise1() {
        
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String  actual = (String) jsExecutor.executeScript("return exercise1(JSON.parse(arguments[0]));", "{\"food\":\"potato\"}");
        String expected = "{\"food\":\"potato\"}";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testExercise1Again() {
        
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        String actual = (String) jsExecutor.executeScript("return exercise1(JSON.parse(arguments[0]));", "{\"food\":\"potato\", \"animal\":\"koala\", \"movie\":\"cloverfield\"}");
        String expected = "{\"food\":\"potato\",\"animal\":\"koala\",\"movie\":\"cloverfield\"}";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testExercise2() {
        
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object actual = jsExecutor.executeScript("return exercise2(arguments[0]);", "{\"food\":\"potato\", \"animal\":\"koala\", \"movie\":\"cloverfield\"}");
        String expected = "{animal=koala, food=potato, movie=cloverfield}";
        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    public void testExercise2Again() {
       
        webDriver.get(path);

        JavascriptExecutor jsExecutor = (JavascriptExecutor) webDriver;
        Object actual = jsExecutor.executeScript("return exercise2(arguments[0]);", "{\"food\":\"banana\", \"animal\":\"cat\", \"movie\":\"up\"}");
        String expected = "{animal=cat, food=banana, movie=up}";
        Assertions.assertEquals(expected, actual.toString());
    }

    
}

