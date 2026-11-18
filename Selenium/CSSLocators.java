import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CSSLocators {
    public static void main(String args[]) {
        WebDriver driver = new EdgeDriver();

        driver.manage().window().maximize();
        // tag id
       // driver.findElement(By.cssSelector("input#name")).sendKeys("username");
        driver.findElement(By.cssSelector("#name")).sendKeys("username");

        // tag class name
        //driver.findElement(By.className("input.password")).sendKeys("Password123");
        driver.findElement(By.className(".password")).sendKeys("Password123");

        // tag attribute
        driver.findElement(By.cssSelector("input[placeholder='Search store']")).sendKeys("toys");
        driver.findElement(By.cssSelector("[placeholder='Search store']")).sendKeys("toys");

        //tag class attribute
        driver.findElement(By.cssSelector("input.search-box[name='q']")).clear();




    }
}
