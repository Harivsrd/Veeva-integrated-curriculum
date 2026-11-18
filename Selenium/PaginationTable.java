import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class PaginationTable {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.get("https://practice.expandtesting.com/dynamic-pagination-table");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        String text = driver.findElement(By.id("example_info")).getText();
        int starting = text.indexOf("of ");
//        System.out.println(starting);
        int ending = text.indexOf(" entries");
//        System.out.println(ending);

        int rows_count = Integer.parseInt(text.substring(starting+3, ending));
        System.out.println("Total no of rows :" +rows_count);

        while(true) {

            WebElement nextbtn = driver.findElement(By.xpath("//*[@id=\"example_next\"]/a"));
            WebElement table = driver.findElement(By.tagName("table"));


            List<WebElement> rows = table.findElements(By.tagName("tr"));

            for(WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));

                for(WebElement cell : cells) {
                    System.out.print(cell.getText()+" | ");
                }
                System.out.println();
            }
            if(nextbtn.findElement(By.xpath("..")).getAttribute("class").contains("disabled")) {
                break;
            }
            nextbtn.click();

        }


    }
}
