import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class Procraft {


    static ArrayList<String> goodName = new ArrayList<String>();
    static ArrayList<String> badName = new ArrayList<String>();
    @Test
    public static void main() {
        openPage();
        navMenu();
        searchElements(false);
        changePage(2);
        searchElements(false);
        changePage(1);
        searchElements(true);
        changePage(3);
        searchElements(true);
        changePage(5);
        searchElements(true);

    }

    static void openPage() {
        Main.driver.get("https://shoptool.com.ua/");
    }

    static void navMenu() {
        Main.driver.findElement(By.xpath("//bdi[text()='Электроинструмент']/../../..")).click();
        Main.driver.findElement(By.xpath("//a[text()='Перфораторы']")).click();
    }

    static void searchElements(boolean check) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Main.driver.findElements(By.xpath("//img[@alt='PROCRAFT']/../../../div[@class='ut2-gl__name']/a")).forEach(webElement -> {
            if (check) {
                if (webElement.getAttribute("innerText").toLowerCase().contains("procraft")) {
                    goodName.add(webElement.getAttribute("innerText"));
                } else {
                    badName.add(webElement.getAttribute("innerText"));
                }
            } else {
                System.out.println(webElement.getAttribute("innerText"));
            }
        });
    }
@Test
    static public void testPriceInCardProduct(){
        changePage(1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = Main.driver.findElement(By.xpath("//img[@alt='PROCRAFT']/../../../div[@class='ut2-gl__name']/a"));
        element.click();
        WebElement price = Main.driver.findElement(By.xpath("//span[@class='ty-price']/bdi/span"));
        Assert.assertNotEquals(price.getAttribute("innerText"),"");
    }

    static void changePage(int page) {
        Main.driver.findElement(By.cssSelector(".ty-pagination__items > a:nth-child(" + page + ")")).click();
    }
    @AfterTest
    static void goodAndBadNamesProcraft(){
        System.out.println("goodName");
        Object[] goodNameArray = goodName.toArray();
        for (Object goodName : goodNameArray) {
            System.out.println(goodName);
        }
        if(!badName.isEmpty())
        System.out.println("badName");
        Object[] badNameArray = badName.toArray();
        for (Object badName : badNameArray) {
            System.out.println(badName);
        }
        String msg= "Have bad names: " +badName.size();
        Assert.assertEquals(badName.isEmpty(),true,msg );
    }
}
