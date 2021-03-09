package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class BasketPage {
    public WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    private By basketAmountLocator = By.cssSelector  ("td.ty-checkout-summary__item.ty-right > span > bdi > span");


    public int getSummaryItem(){

        WebElement element = (wait)
                .until(ExpectedConditions.presenceOfElementLocated(By.className("ty-checkout-summary__item")));
        String countProductInBasket = element.getText().replaceAll("[^\\d]", "");
        int intCountProductInBasket = Integer.valueOf(countProductInBasket);
        return intCountProductInBasket;
    }

    public double getBasketAmount() {
        String basketAmount = driver.findElement(basketAmountLocator).getText();
        basketAmount = basketAmount.replaceAll("[,]", "");
        return Double.valueOf(basketAmount);
    }

        public void deleteItemFromBasket(){
            actions.moveToElement(driver.findElement(By.xpath("//a[@data-ca-dispatch]"))).build().perform();
            driver.findElement(By.xpath("//a[@data-ca-dispatch]")).click();
        }

}
