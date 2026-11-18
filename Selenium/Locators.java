import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;
public class Locators {
    public static void main(String[] args) throws InterruptedException{
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        //id
//        boolean logoDisplayed = driver.findElement(By.className("login_logo")).isDisplayed();
//        if(logoDisplayed) {
//            System.out.println("Logo displayed!!");
//        }
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //name
        driver.findElement(By.name("password")).sendKeys("secret_sauce");

        //Linktext and partialLinktext
//        driver.findElement(By.linkText("About")).click();
//        driver.findElement(By.partialLinkText(("Abo"))).click();

        //class
        List<WebElement> elements = driver.findElements(By.className("input_error"));
        System.out.println(elements.size());
        //tagname
        List<WebElement> inputElements = driver.findElements(By.tagName("input"));
        System.out.println(inputElements.size());

        driver.findElement(By.name("login-button")).click();


    }
}
