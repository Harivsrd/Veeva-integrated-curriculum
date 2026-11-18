import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DropdownsPractice {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//        driver.get("https://practice.expandtesting.com/dropdown");
//
//        driver.findElement(By.id("country")).click();
//
//        driver.findElement(By.id("country")).sendKeys("ind"


    }
}
