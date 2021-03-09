import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WebDriverSettings {

    public WebDriver driver;
    public WebDriverWait wait;
    public Random rand;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        setMyMonitors();

        wait = new  WebDriverWait(driver, Duration.ofSeconds(10));
        rand = new Random();

    }

    public void setMyMonitors() {
        driver.manage().window().setPosition(new Point(1920, 0));
        driver.manage().window().setSize(new Dimension(1360 / 2, 768));
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
