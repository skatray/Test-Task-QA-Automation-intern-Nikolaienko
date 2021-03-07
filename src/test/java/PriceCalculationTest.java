import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;



public class PriceCalculationTest {
    @Test
    public void main() {
        Discounts.openRandPage();
        for (int i =0; i<=5;i++) {
            testProduct();
        }
    }


    static public void testProduct() {
        WebElement product = Main.driver.findElement(By.cssSelector("#products_on_sale_pagination_contents > div:nth-child(" + String.valueOf(Main.random.nextInt(Main.countOfProduct) + 1) + ")"));


        WebElement productName = product.findElement(By.cssSelector("a.product-title"));
        WebElement productNewPrice = product.findElement(By.cssSelector("span.ty-price-num"));
        WebElement productOldPrice = product.findElement(By.cssSelector("span.ty-strike span"));
        WebElement productDiscount = product.findElement(By.cssSelector("div.ty-product-labels__item--discount  em"));

        productNewPrice.getAttribute("innerText");
        Double doubleProductOldPrice = Double.valueOf(productOldPrice.getAttribute("innerText").replaceAll("[^\\d\\.]", ""));
        Double doubleProductDiscount = Double.valueOf(productDiscount.getAttribute("innerText").replaceAll("[^\\d\\.]", ""));
        Double doubleProductNewPrice = Double.valueOf(productNewPrice.getAttribute("innerText").replaceAll("[^\\d\\.]", ""));
        System.out.println(productName.getAttribute(("innerText")));
        System.out.println("Price "+doubleProductOldPrice);
        System.out.println("Discount "+doubleProductDiscount);
        System.out.println("Price with discount "+doubleProductNewPrice);

        Double calculate = (100-doubleProductDiscount)/100 * doubleProductOldPrice;
        calculate = (double) (Math.round(calculate*100))/100;
        System.out.println("Calc price "+calculate);
        System.out.println("");

        Assert.assertEquals(calculate,doubleProductNewPrice, productName.getAttribute(("innerText")));
    }
}
