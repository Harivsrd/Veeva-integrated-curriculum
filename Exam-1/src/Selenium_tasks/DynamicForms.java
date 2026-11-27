package Selenium_tasks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DynamicForms {
    WebDriver driver;
    WebDriverWait explicWait;

    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.vikasqa.com/p/blog-page.html");
    }

    public void fillForm(String username, String email, String password, String gender, String country, String comments) {

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);

        WebElement genderRadioBtn = driver.findElement(By.xpath("//*[@name='gender' and @value='"+gender+"']"));
        if(!genderRadioBtn.isSelected()) {
            genderRadioBtn.click();
        }

        WebElement terms = driver.findElement(By.id("terms"));
        if(!terms.isSelected()) {
            terms.click();
        }

        Select countrySelect = new Select(driver.findElement(By.id("country")));

        countrySelect.selectByVisibleText(country);

        driver.findElement(By.id("comments")).sendKeys(comments);

        driver.findElement(By.xpath("//*[@id=\"seleniumForm\"]/button[1]")).click();

        driver.switchTo().alert().accept();
        
        driver.close();
    }

    public static void main(String[] args) {
        DynamicForms form = new DynamicForms();
        form.setUp();
        String username = "Jack Son";
        String email = "jack@gmail.com";
        String password = "Jack@3299";
        String gender = "Male";
        String country = "USA";
        String comments = "No comments";
        form.fillForm(username,email,password,gender,country,comments);
    }

}
