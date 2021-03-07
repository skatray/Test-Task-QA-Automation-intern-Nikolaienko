import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Discounts {

    static int countOfProduct;
    static WebElement productNewPrice;
    static WebElement productOldPrice;

    @Parameters({"COUNTPRODUCT"})
    @Test
    public static void main() {

        int COUNT_PRODUCT_FOR_TEST = Config.COUNT_PRODUCT_FOR_TEST; //Integer.valueOf(COUNTPRODUCT);

        openRandPage();
        for (int i = 0; i <= COUNT_PRODUCT_FOR_TEST; i++) {
            testProduct();
            testPrice();
            testPriceWithDiscount();
            testPrice();
            openRandPage();
        }

    }



    static void openRandPage() {
        Main.driver.navigate().to("https://shoptool.com.ua/index.php?dispatch=products.on_sale&page=" + String.valueOf(Main.random.nextInt(3) + 1));
        countOfProduct = Main.countProductOnPage();
    }

    static void testProduct() {
        WebElement product = Main.driver.findElement(By.cssSelector("#products_on_sale_pagination_contents > div:nth-child(" + String.valueOf(Main.random.nextInt(countOfProduct) + 1) + ")"));
        product.click();
        productNewPrice = Main.driver.findElement(By.cssSelector("div.ut2-pb__price-actual span.ty-price-num"));
        productOldPrice = Main.driver.findElement(By.cssSelector("div.ty-product-detail  span[id^='old_price']"));
    }

    static  void testPriceWithDiscount(){
        Assert.assertNotEquals("",productNewPrice.getAttribute("innerText"));
    }
    static  void testPrice(){
        Assert.assertNotEquals("",productOldPrice.getAttribute("innerText"));
    }

}
