import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;



public class PriceCalculationTest extends WebDriverSettings  {

    @Test
    public void PriceCalculationTest() {
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.openRandPage(rand.nextInt(3)+1);
        homePage.countProductOnPage();
        for (int i =0; i<=5;i++) {
           homePage.testProduct(rand.nextInt(homePage.countProductOnPage));
        }

    }
}
