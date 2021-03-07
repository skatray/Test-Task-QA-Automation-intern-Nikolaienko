import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;


public class Main {

    static final Random random = new Random();
    public static final WebDriver driver = new ChromeDriver();
    static int countOfProduct;


    public static void main() {
       // System.setProperty("webdriver.chrome.driver", "chromedriver");
    }

    public static int countProductOnPage() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countOfProduct = (int) Main.driver.findElements(By.xpath("//*[@class=\"ty-column3\"]/div/..")).size();
    return countOfProduct;
    }

}
