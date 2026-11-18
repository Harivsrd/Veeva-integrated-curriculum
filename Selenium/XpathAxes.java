import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class XpathAxes {

        public static void main(String[] args) {

            WebDriver driver = new EdgeDriver();

            // get() - open the url on the browser
            driver.get("https://www.democart.com");
            //getTitle()
            System.out.println(driver.getTitle());
            //getCurrentUrl()
            System.out.println(driver.getCurrentUrl());
            //getPageSource()
            System.out.println(driver.getPageSource());
            //getWindowHnadle()
            System.out.println(driver.getWindowHandle());
            //getWindowhandles()
            System.out.println(driver.getWindowHandles());


        }
}
