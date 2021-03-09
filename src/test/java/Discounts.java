import Pages.HomePage;
import Pages.ProductPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Discounts extends WebDriverSettings {

    @Parameters({"COUNTPRODUCT"})
    @Test(invocationCount =  Config.COUNT_PRODUCT_FOR_TEST)
    public void Discounts() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);

        homePage.openRandPage(rand.nextInt(3) + 1);
        homePage.openRandProduct(rand.nextInt(homePage.countProductOnPage() + 1));
        productPage.testPrice();
        productPage.testNewPrice();
    }
}

