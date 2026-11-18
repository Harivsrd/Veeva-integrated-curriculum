import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class XpathLocators {
    public static void main(String[] args) {
        WebDriver driver = new EdgeDriver();

        driver.get("https://democart.com");

        //xpath with single attribute
        driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("Tshirts");

        //xpath with multiple attributes
        driver.findElement(By.xpath("//input[@name='search'][@placeholder='Search']")).sendKeys("Tshits");

        //xpath with and , or
        driver.findElement(By.xpath("//input[@name='search' and @placeholder='Search']")).sendKeys("Tshits");

        driver.findElement(By.xpath("//input[@name='search' or @placeholder='Search']")).sendKeys("Tshits");

        //xpath with inner text - text()

        driver.findElement(By.xpath("a[text()='Contact']")).click();
        // isDisplayed()

        //xpath with contains()
        driver.findElement(By.xpath("//input[contains(@placeholder,'Sea')]")).sendKeys("Something");

        //xpath with start-with()
        driver.findElement(By.xpath("//input[start-with(@placeholder,'Sea')]")).sendKeys("Something");

        //handling dynamic attributes
        //*[@id='Start' or @id='Stop']
        //*[contains(@id, 'st')]
        //*[starts-with(@id,'st')]

        //*[contains(text(), 'st')]


    }
}
