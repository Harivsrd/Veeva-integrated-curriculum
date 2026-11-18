import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class FramesPractice {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.hyrtutorials.com/p/frames-practice.html");

        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("In Main page 1");

        driver.switchTo().frame(0);

        WebElement selectNav = driver.findElement(By.id("selectnav1"));
        Select select = new Select(selectNav);
        select.selectByIndex(1);

        driver.switchTo().defaultContent();

        driver.switchTo().frame(1);
        selectNav = driver.findElement(By.id("selectnav1"));
        select = new Select(selectNav);
        select.selectByContainsVisibleText("-- TestNG");

        driver.switchTo().defaultContent();

        driver.switchTo().frame(2);

        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Done");

        driver.switchTo().frame(1);

        WebElement dropdown = driver.findElement(By.id("selectnav2"));
        Select dropdn = new Select(dropdown);

        dropdn.selectByContainsVisibleText("Contact");

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys("Done");

        driver.close();
    }
}
