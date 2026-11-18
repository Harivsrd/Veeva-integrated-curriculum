import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DropDown {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.tutorialspoint.com/selenium/practice/auto-complete.php");

        driver.findElement(By.className("ui-autocomplete-input")).sendKeys("jav");

        mywait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ui-id-1"))));

        List<WebElement> items = driver.findElements(By.className("ui-menu-item"));

        for(WebElement item : items) {
            System.out.println(item.getText());
        }

        for(WebElement item : items) {
            if(item.getText().equals("JavaScript")) {
                item.click();
            }
        }


    }
}
