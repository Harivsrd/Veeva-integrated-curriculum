import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DatePicker {
    public static void selectPastMonthandYear(WebElement datePicker, String Month, String Year) {
        while(true) {

            String currentMonth = datePicker.findElement(By.className("ui-datepicker-month")).getText();
            String currentYear = datePicker.findElement(By.className("ui-datepicker-year")).getText();

            if(currentMonth.equals(Month) && currentYear.equals(Year)) {
                break;
            }

            datePicker.findElement(By.className("ui-datepicker-prev")).click();

        }
    }
    public static void selectFutureMonthandYear(WebElement datePicker, String Month, String Year) {
        while(true) {

            String currentMonth = datePicker.findElement(By.className("ui-datepicker-month")).getText();
            String currentYear = datePicker.findElement(By.className("ui-datepicker-year")).getText();

            if(currentMonth.equals(Month) && currentYear.equals(Year)) {
                break;
            }

            datePicker.findElement(By.className("ui-datepicker-next")).click();

        }
    }
    public static void selectDate(WebDriver driver, String date) {
        List<WebElement> allDates = driver.findElements(By.xpath("//td/a"));

        for(WebElement currDate : allDates) {
            if(currDate.getText().equals(date)) {
                currDate.click();
                break;
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://testautomationcentral.com/demo/date_picker.html");
        driver.manage().window().maximize();

        WebDriverWait expWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Method - 1 :: If the date input is directly taking by sendKeys()
//        WebElement dateInput = driver.findElement(By.id("date-picker"));
//        dateInput.sendKeys("10/01/2020"); //-- DD/MM/YYYY

//        dateInput.sendKeys(Keys.ENTER);

        // Method - 2 :: Only clickable
        String Month = "January";
        String Year = "2026";
        String date = "10";

        WebElement dateInput = driver.findElement(By.id("date-picker"));
        dateInput.click();

        WebElement datePicker = driver.findElement(By.id("ui-datepicker-div"));

        expWait.until(ExpectedConditions.visibilityOf(datePicker));

        selectPastMonthandYear(datePicker, Month, Year);
        selectDate(driver, date);

        Thread.sleep(9000);
        driver.close();


    }
}
