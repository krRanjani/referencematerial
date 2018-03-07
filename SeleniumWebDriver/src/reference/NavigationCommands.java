package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class NavigationCommands {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://store.demoqa.com/";
	String Title = "ONLINE STORE | Toolsqa Dummy Test site";
	
	@BeforeTest
	public void launchBrowser() 
	{ 
		driver.get(URL);
		driver.manage().window().maximize();
	}
	
	@Test
	public void navigationCommands() 
	{
		driver.findElement(By.partialLinkText("My Account")).click();
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().to(URL);
		driver.navigate().refresh();
		if(driver.getTitle().equalsIgnoreCase(Title))
			System.out.println("Executed successfully");
		else
			System.out.println("Failed to execute!!");
	}
	
	@AfterTest
	public void closeBrowser() 
	{
		driver.close();
	}

}
