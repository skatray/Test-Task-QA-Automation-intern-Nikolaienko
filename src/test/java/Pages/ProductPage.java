package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    public WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    private By productNewPriceLocator = By.cssSelector("div.ut2-pb__price-actual span.ty-price-num");
    private By productOldPriceLocator = By.cssSelector("div.ty-product-detail  span[id^='old_price']");

    public void testPrice() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ut2-pb__price-actual span.ty-price-num")));

        //   driver.findElement(By.cssSelector("div.ut2-pb__price-actual span.ty-price-num"));
    }

    public void testNewPrice() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.ty-product-detail  span[id^='old_price']")));

        //   driver.findElement(By.cssSelector("div.ty-product-detail  span[id^='old_price']"));
    }
}
