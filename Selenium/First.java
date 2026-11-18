import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class First {

    public static void main(String[] args) throws InterruptedException {
    	//Launch browser
//        WebDriver driver = new EdgeDriver();
        WebDriver driver = new ChromeDriver();
        // open url https://demo.opencart.com/
        driver.get("https://demo.opencart.com/");
        //validate the title should be "Your Store"
        String title = driver.getTitle();
        if(title.equals("Your Store")) {
            System.out.println("Test passes");
        }
        else{
            System.out.println("Test failed");
        }
        //close browser
        driver.close();

    }
}