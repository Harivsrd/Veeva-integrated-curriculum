import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MouseActions {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://webdriveruniversity.com/Actions/index.html#");
        WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Actions actions = new Actions(driver);

        // locating the web elements to drag and drop
        WebElement dragElement = driver.findElement(By.id("draggable"));
        WebElement dropElement = driver.findElement(By.id("droppable"));
        // Drag and Drop
        actions.dragAndDrop(dragElement, dropElement).build().perform();

        WebElement doubleClickElement = driver.findElement(By.id("double-click"));
        // Double - Click
        actions.doubleClick(doubleClickElement).perform();

        //Mouse hover actions
        // Locating the hover buttons in list
        List<WebElement> hoverButtons = driver.findElements(By.className("dropbtn"));
        //Iterating through the buttons and hover and hover on links and clicking the links in menu
        // Closing the alerts before the next hover actions
        for(WebElement button : hoverButtons) {
            //Hover on the button to get the menu list
            actions.moveToElement(button).build().perform();
            // capturing the required link element
            WebElement link = mywait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Link 1"))));
            // hover to link and click
            actions.moveToElement(link).click().build().perform();
            // closing / accepting the alert box
            driver.switchTo().alert().accept();
        }
        //Click and Hold action
        WebElement clickAndHoldButton = driver.findElement(By.id("click-box"));
        actions.clickAndHold(clickAndHoldButton);

        Thread.sleep(3000);
        driver.close();
    }
}
