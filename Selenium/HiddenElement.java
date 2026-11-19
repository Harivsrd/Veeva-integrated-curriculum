import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HiddenElement {
    public static void closeHiddenandDismiss(WebDriver driver, String buttonId, String alertBoxId) {

        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor executor = (JavascriptExecutor)driver;

        WebElement button = driver.findElement(By.id(buttonId));
        if(!button.isDisplayed())
            executor.executeScript("arguments[0].click()", button);

        WebElement alertBox = mywait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(alertBoxId))));
        alertBox.findElement(By.tagName("button")).click();

    }
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://webdriveruniversity.com/Hidden-Elements/index.html");


        closeHiddenandDismiss(driver, "button1", "myModalClick");
        closeHiddenandDismiss(driver, "button2", "myModalJSClick");
        closeHiddenandDismiss(driver, "button3", "myModalMoveClick");

        Thread.sleep(5000);
        driver.close();
    }
}
