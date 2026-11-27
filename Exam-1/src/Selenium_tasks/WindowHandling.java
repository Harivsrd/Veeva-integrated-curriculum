package Selenium_tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class WindowHandling {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/windows");

        String parentWindow = driver.getWindowHandle();

        System.out.println("Parent Window ID: " + parentWindow);

        driver.findElement(By.linkText("Click Here")).click();

        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println("Child Page Title: " + driver.getTitle());
                driver.close();
            }
        }

        driver.switchTo().window(parentWindow);
        System.out.println("Back to Parent Title: " + driver.getTitle());
        driver.quit();
    }
}