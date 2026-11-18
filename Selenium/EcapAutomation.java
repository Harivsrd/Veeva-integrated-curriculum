import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EcapAutomation {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();

        driver.get("https://sves.org.in/Ecap/");

        driver.findElement(By.id("txtId2")).sendKeys("23A81A6184");
        driver.findElement(By.id("txtPwd2")).sendKeys("HARI@9640959255");

        driver.findElement(By.id("imgBtn2")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("ATTENDANCE")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("radTillNow")).click();

        driver.findElement(By.id("btnShow")).click();
//        /html/body/div/div/form/input[1]

    }
}
