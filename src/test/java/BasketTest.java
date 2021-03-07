import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasketTest {
    static int countOfProduct;
    static int countOfTryAddProductToBasket = 3;
    static Actions actions = new Actions(Main.driver);

    @Test
    public static void main() {

        openPage();
        navMenu();

        for (int i = 1; i <= countOfTryAddProductToBasket; i++) {
            if (i >= 2 && i<=4) {
                changePage(i);
            }
            addProduct(Main.random.nextInt(countOfProduct) + 1);
        }

        Basket();


    }

    static void openPage() {
        Main.driver.get("https://shoptool.com.ua/");

    }

    static void navMenu() {
        Main.driver.findElement(By.xpath("//bdi[text()='Электроинструмент']/../../..")).click();
        Main.driver.findElement(By.xpath("//a[text()='Дрели']")).click();
        countOfProduct = Main.countProductOnPage();
    }

    static void addProduct(int i) {

        try {
            Thread.sleep(2500);
            actions.moveToElement(Main.driver.findElement(By.cssSelector("#categories_view_pagination_contents > div:nth-child(" + i + ")"))).build().perform();


            WebElement elBtn = (new WebDriverWait(Main.driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#categories_view_pagination_contents > div:nth-child(" + i + ") [id^='button_cart']"))));
            elBtn.click();


            WebElement elPopup = (new WebDriverWait(Main.driver, Duration.ofSeconds(20))
                    .until(ExpectedConditions.elementToBeClickable(By.className("cm-notification-close"))));
            elPopup.click();
       //

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static void changePage(int page) {
        Main.driver.findElement(By.cssSelector(".ty-pagination__items > a:nth-child(" + page + ")")).click();
        countOfProduct = Main.countProductOnPage();
    }

    static void Basket() {
        Main.driver.findElement(By.className("ut2-top-cart-content")).click();
        Main.driver.findElement(By.cssSelector("div.ty-float-right > a")).click();
        WebElement element = (new WebDriverWait(Main.driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("ty-checkout-summary__item"))));

        // regex end Search count product in basket
        String countProductInBasket = element.getAttribute("innerText").replaceAll("[^\\d]","");
        int intCountProductInBasket = Integer.valueOf(countProductInBasket);

        Assert.assertEquals(countOfTryAddProductToBasket, intCountProductInBasket,"There are fewer items in the cart than expected");

        Assert.assertEquals(countOfTryAddProductToBasket, intCountProductInBasket);
        double fullBasketAmount = getBasketAmount();

        actions.moveToElement(Main.driver.findElement(By.xpath("//a[@data-ca-dispatch]"))).build().perform();
        Main.driver.findElement(By.xpath("//a[@data-ca-dispatch]")).click();

        double partBasketAmount = getBasketAmount();
        System.out.println("Price of the whole basket:"+fullBasketAmount+ ". Basket price without product: " +partBasketAmount);
        if (fullBasketAmount != partBasketAmount) {
            System.out.println("Prices are not equal");
        } else {
            Assert.fail("Prices are equal");
        }
        Assert.assertNotEquals(fullBasketAmount, partBasketAmount, "Prices are equal");
    }

    static double getBasketAmount() {
        String basketAmount = Main.driver.findElement(By.cssSelector("td.ty-checkout-summary__item.ty-right > span > bdi > span")).getAttribute("innerText");
        basketAmount = basketAmount.replaceAll("[,]", "");
        return Double.valueOf(basketAmount);
    }

}
