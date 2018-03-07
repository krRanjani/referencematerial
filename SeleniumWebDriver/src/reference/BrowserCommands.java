package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class BrowserCommands {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://store.demoqa.com/";
 
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void browserCommands()
  {
	  System.out.println("Page title is "+driver.getTitle()+" and page title length is "+driver.getTitle().length());
	  System.out.println("Current URL is "+driver.getCurrentUrl());
	  System.out.println("Page Source is "+driver.getPageSource());
  }

  @AfterTest
  public void afterTest() 
  {
	  driver.close();
	  driver.quit();
  }

}
