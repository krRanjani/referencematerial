package reference;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Guru99Demo {

	WebDriver driver = new ChromeDriver();
	String URL ="https://www.google.com";
	
@BeforeTest
public void launchBrowser()
{
	driver.get(URL);
	driver.manage().window().maximize();
}

@Test
public void openGoogle()
{
	//System.setProperty("webdriver.chrome.driver", "C:\\Ranj\\eclipse-workspace\\chromedriver.exe");

	WebElement search = driver.findElement(By.id("lst-ib"));
	search.sendKeys("First automation");
	search.sendKeys(Keys.ENTER);
	driver.findElement(By.partialLinkText("Automation - Wikipedia")).click();
	System.out.println("First heading in the page is: "+driver.findElement(By.id("firstHeading")).getText());	
}

@AfterTest
public void closeBrowser()
{
	driver.close();
}

}
