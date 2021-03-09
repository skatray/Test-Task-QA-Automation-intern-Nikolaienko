package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    public WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        actions = new Actions(driver);
    }

    @FindBy(css = "#products_on_sale_pagination_contents > div")
    List<WebElement> products;
    List<WebElement> badProducts = new ArrayList<>();

    private By menuPageInstrumenty = By.xpath("//bdi[text()='Электроинструмент']/../../..");
    private By menuPageDrely = By.xpath("//a[text()='Дрели']");
    private By menuPagePerforatory = By.xpath("//a[text()='Перфораторы']");
    private By countOfProductLocator = By.xpath("//*[@class=\"ty-column3\"]/div/..");
    private By productLocator = By.className("ty-column3");
    public int countProductOnPage;

    public void openPage() {
        driver.get("https://shoptool.com.ua/");
    }

    public void openRandPage(int page) {
        driver.navigate().to("https://shoptool.com.ua/index.php?dispatch=products.on_sale&page=" + page);
    }

    public void openPageInstrumenty() {
        driver.findElement(menuPageInstrumenty).click();
    }

    public void openPageDrely() {
        driver.findElement(menuPageDrely).click();
    }

    public void openPagePerforatory(){
        driver.findElement(menuPagePerforatory).click();
    }

    public int countProductOnPage() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(countOfProductLocator));
        return countProductOnPage = (int) driver.findElements(countOfProductLocator).size();
    }

    public void changePage(int page) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ty-pagination__items > a:nth-child(" + page + ")")));
            driver.findElement(By.cssSelector(".ty-pagination__items > a:nth-child(" + page + ")")).click();
        }catch (Exception e){
            Assert.fail("Page "+page+ " not found");
        }
    }

    public void addProduct(int i) {
        wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("#categories_view_pagination_contents > div:nth-child(" + i + ")"))));
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.moveToElement(driver.findElement(By.cssSelector("#categories_view_pagination_contents > div:nth-child(" + i + ")"))).build().perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement elBtn = driver.findElement(By.cssSelector("#categories_view_pagination_contents > div:nth-child(" + i + ") [id^='button_cart']"));
        elBtn.click();
        WebElement elPopup = wait
                .until(ExpectedConditions.elementToBeClickable(By.className("cm-notification-close")));
        elPopup.click();
        wait.until(ExpectedConditions.stalenessOf(elPopup));
    }

    public void openBasket() {
        driver.findElement(By.className("ut2-top-cart-content")).click();
        driver.findElement(By.cssSelector("div.ty-float-right > a")).click();
    }

    public void openRandProduct(int productNumber) {
        WebElement product = driver.findElement(By.cssSelector("#products_on_sale_pagination_contents > div:nth-child(" + productNumber + ")"));
        product.click();
    }

    public void testProduct(int count) {
        WebElement product = products.get(count);

        WebElement productName = product.findElement(By.cssSelector("a.product-title"));
        WebElement productNewPrice = product.findElement(By.cssSelector("span.ty-price-num"));
        WebElement productOldPrice = product.findElement(By.cssSelector("span.ty-strike span"));
        WebElement productDiscount = null;
        try {
            productDiscount = product.findElement(By.cssSelector("div.ty-product-labels__item--discount  em"));
        }catch (Exception e) {
            Assert.fail("This Product don't have discount label " + productName.getText());
        }



        Double doubleProductOldPrice = Double.valueOf(productOldPrice.getText().replaceAll("[^\\d\\.]", ""));
        Double doubleProductDiscount = Double.valueOf(productDiscount.getText().replaceAll("[^\\d\\.]", ""));
        Double doubleProductNewPrice = Double.valueOf(productNewPrice.getText().replaceAll("[^\\d\\.]", ""));
        //
        System.out.println(productName.getText());
        System.out.println("Price " + doubleProductOldPrice);
        System.out.println("Discount " + doubleProductDiscount);
        System.out.println("Price with discount " + doubleProductNewPrice);
        //
        Double calculate = (100 - doubleProductDiscount) / 100 * doubleProductOldPrice;
        calculate = (double) (Math.round(calculate * 100)) / 100;
        System.out.println("Calc price " + calculate);
        System.out.println("");

        Assert.assertEquals(calculate, doubleProductNewPrice, productName.getText());
    }




}
