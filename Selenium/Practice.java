import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Practice {
    public static void main(String args[]) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);
        driver.get("https://www.letskodeit.com/practice");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();
        //radio button
        driver.findElement(By.xpath("//*[@id=\"bmwradio\"]")).click();

        //checkbox multiple selection
        List<WebElement> checkboxes = driver.findElements(By.xpath("//*[@name='cars' and @type='checkbox']"));

        for(WebElement checkBox : checkboxes) {
            checkBox.click();
        }

        driver.findElement(By.id("openwindow")).click();

        //get parent window address
        String parentId = driver.getWindowHandle();

        String childId = "";
        //get all window addresses
        Set<String> windowIds = driver.getWindowHandles();
        for(String windowId : windowIds) {
            if(!windowId.equals(parentId)){
                childId = windowId;
                break;
            }
        }

        driver.switchTo().window(childId);
        driver.close();
        driver.switchTo().window(parentId);

        driver.findElement(By.id("opentab")).click();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(parentId)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        driver.close();
        driver.switchTo().window(parentId);

        WebElement dropdown = driver.findElement(By.id("carselect"));

        Select select = new Select(dropdown);

        select.selectByContainsVisibleText("Benz");


        WebElement multipleDropdown = driver.findElement(By.id("multiple-select-example"));
        Select select1 = new Select(multipleDropdown);

        if(select1.isMultiple()){

            select1.selectByContainsVisibleText("Apple");
            select1.selectByContainsVisibleText("Orange");

            for(WebElement option : select1.getAllSelectedOptions()){
                System.out.println(option.getText());
            }
        }

        driver.findElement(By.id("disabled-button")).click();
        WebElement field = driver.findElement(By.id("enabled-example-input"));
        System.out.println(field.isEnabled());
        driver.findElement(By.id("enabled-button")).click();
        System.out.println(field.isEnabled());
        driver.findElement(By.id("displayed-text")).isDisplayed();

        driver.findElement(By.id("hide-textbox")).click();
        WebElement inputBox = driver.findElement(By.id("displayed-text"));
        System.out.println(inputBox.isDisplayed());
        driver.findElement(By.id("show-textbox")).click();
        System.out.println(inputBox.isDisplayed());

        driver.findElement(By.xpath("//*[@id='name' and @type='text']")).sendKeys("Welcome");

        driver.findElement(By.id("alertbtn")).click();
//        Thread.sleep(1000);
        driver.switchTo().alert().accept();
//        Thread.sleep(2000);
//        driver.switchTo().window(parentId);
        driver.findElement(By.id("confirmbtn")).click();
        driver.switchTo().alert().accept();

        driver.switchTo().frame(0);
//        driver.switchTo().frame

        WebElement categoryWE = driver.findElement(By.name("categories"));
        Select category = new Select(categoryWE);
        category.selectByIndex(4);


        driver.switchTo().defaultContent();
        WebElement mouseElement = driver.findElement(By.id("mousehover"));

        actions.moveToElement(mouseElement).perform();
        driver.findElement(By.xpath("//*[@id=\"mouse-hover-example-div\"]/div[1]/fieldset/div/div/a[1]")).click();

        WebElement table = driver.findElement(By.id("product"));

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for(WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for(WebElement cell : cells) {
                System.out.println(cell.getText());
            }
        }


        driver.close();

    }
}
