import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class Procraft extends WebDriverSettings {

    static ArrayList<String> goodName = new ArrayList<String>();
    static ArrayList<String> badName = new ArrayList<String>();

    @Test(priority = 0)
    public void Procraft() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.openPage();
        homePage.openPageInstrumenty();
        homePage.openPagePerforatory();
        searchElements(false);
        homePage.changePage(2);
        searchElements(false);
        homePage.changePage(1);
        searchElements(true);
        homePage.changePage(3);
        searchElements(true);
        homePage.changePage(5);
        searchElements(true);

    }
     void searchElements(boolean check) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElements(By.xpath("//img[@alt='PROCRAFT']/../../../div[@class='ut2-gl__name']/a")).forEach(webElement -> {
            if (check) {
                if (webElement.getText().toLowerCase().contains("procraft")) {
                    goodName.add(webElement.getText());
                } else {
                    badName.add(webElement.getText());
                }
            } else {
                System.out.println(webElement.getText());
            }
        });
    }

    @Test(priority = 2)
     public void testPriceInCardProduct() {
         HomePage homePage = PageFactory.initElements(driver, HomePage.class);
         homePage.changePage(1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = driver.findElement(By.xpath("//img[@alt='PROCRAFT']/../../../div[@class='ut2-gl__name']/a"));
        element.click();
        WebElement price = driver.findElement(By.xpath("//span[@class='ty-price']/bdi/span"));
        Assert.assertNotEquals(price.getAttribute("innerText"), "");
    }

    @Test(priority = 1)
    static void goodAndBadNamesProcraft() {
        System.out.println("goodName");
        Object[] goodNameArray = goodName.toArray();
        for (Object goodName : goodNameArray) {
            System.out.println(goodName);
        }
        if (!badName.isEmpty())
            System.out.println("badName");
        Object[] badNameArray = badName.toArray();
        for (Object badName : badNameArray) {
            System.out.println(badName);
        }
        String msg = "Have bad names: " + badName.size();
        Assert.assertEquals(badName.isEmpty(), true, msg);
    }
}
