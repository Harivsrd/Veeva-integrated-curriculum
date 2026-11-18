import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Second {
    public static void main(String args[]) {
        WebDriver driver = new ChromeDriver();

        driver.get("https://qaplayground.com/practice/input-fields");

        driver.findElement(By.id("movieNameInput")).sendKeys("The Avengers");
        driver.findElement(By.id("submitMovieBtn")).click();
        driver.findElement(By.id("appendInput")).click();
        driver.findElement(By.id("appendInput")).sendKeys("END GAME");
        driver.findElement(By.id("appendInput")).sendKeys(Keys.TAB);

        String value = driver.findElement(By.id("readValueInput")).getAttribute("value");
        System.out.println(value);

        driver.findElement(By.id("readValueBtn")).click();

        driver.findElement(By.id("clearInput")).clear();
        driver.findElement(By.id("clearFieldBtn")).click();

        WebElement disabledInput = driver.findElement(By.id("disabledInput"));

//        Assert.assertFalse(disabledInput.isEnabled());


    }
}
