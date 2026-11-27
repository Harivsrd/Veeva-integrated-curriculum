package Selenium_tasks;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandlingAlertsPopups {
    WebDriver driver;

    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://testautomationpractice.blogspot.com/");
    }

    public void handleSimlpleAlerts() {
        driver.findElement(By.id("alertBtn")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    public void handleConfirmationAlerts() {
        driver.findElement(By.id("confirmBtn")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    public void handlePromptAlerts() throws InterruptedException {
        driver.findElement(By.id("promptBtn")).click();
        Alert alert = driver.switchTo().alert();

        alert.sendKeys("Hari");
        System.out.println(alert.getText());
        alert.accept();
    }

    public void stop() {
        driver.close();
    }

    public static void main(String[] args) throws InterruptedException {
        HandlingAlertsPopups alerts = new HandlingAlertsPopups();
        alerts.setUp();
        Thread.sleep(2000);
        alerts.handleSimlpleAlerts();
        Thread.sleep(2000);
        alerts.handleConfirmationAlerts();
        Thread.sleep(2000);
        alerts.handlePromptAlerts();
        alerts.stop();
    }

}
