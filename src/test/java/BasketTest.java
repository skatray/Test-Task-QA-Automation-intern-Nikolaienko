import Pages.BasketPage;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasketTest extends WebDriverSettings {
    static int countOfProduct;
    int countOfTryAddProductToBasket = 3;
    Random random = new Random();

    // public   HomePage homePage = PageFactory.initElements(driver,HomePage.class);
    @Test
    public void BasketTest() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openPage();
        homePage.openPageInstrumenty();
        homePage.openPageDrely();
        countOfProduct = homePage.countProductOnPage();

        for (int i = 1; i <= countOfTryAddProductToBasket; i++) {
            if (i >= 2 && i <= 4) {
                homePage.changePage(i);

            }
            int r = random.nextInt(countOfProduct) + 1;
            homePage.addProduct(r);
        }

        homePage.openBasket();
    }

    @Test(priority = 1)
    public void testCountItemInBasket() {
        BasketPage basketPage = PageFactory.initElements(driver, BasketPage.class);
        Assert.assertEquals(countOfTryAddProductToBasket, basketPage.getSummaryItem(), "There are fewer items in the cart than expected");
    }

    @Test(priority = 2)
    public void testChangePriceBasketAfterDeleteItem() {
        BasketPage basketPage = PageFactory.initElements(driver, BasketPage.class);
        double fullBasketAmount = basketPage.getBasketAmount();
        basketPage.deleteItemFromBasket();
        double partBasketAmount = basketPage.getBasketAmount();
        System.out.println("Price of the whole basket: " + fullBasketAmount + ". Basket price without product: " + partBasketAmount);
        Assert.assertNotEquals(fullBasketAmount, partBasketAmount, "Prices are equal");
    }


}
