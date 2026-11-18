import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class staticTable {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.tutorialspoint.com/selenium/practice/webtables.php");

        WebElement table = driver.findElement(By.className("table"));

        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for(WebElement headers : rows.get(0).findElements(By.tagName("th"))){
            System.out.print(headers.getText()+" | ");
        }
        System.out.println();
        for(WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            for(WebElement cell : cells) {
                System.out.print(cell.getText() + " | ");
            }
            System.out.println();
        }
        int totalSalary = 0;
        for(int i = 1; i < rows.size(); i++) {
            int salary = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr["+i+"]/td[5]")).getText());
            totalSalary += salary;
        }

        System.out.println("Total salary :" + totalSalary);

        driver.close();

    }
}
