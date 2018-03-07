package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class LinkUsage {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://demo.guru99.com/test/newtours/";
	
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void clickLink()  
  {
	driver.findElement(By.linkText("SIGN-ON")).click();
	driver.findElement(By.linkText("SUPPORT")).click();
	driver.navigate().back();
	driver.findElement(By.partialLinkText("regist")).click();
	List<WebElement> allLinks = driver.findElements(By.tagName("a"));
	for(WebElement link:allLinks)
	{
		if(link.getText().equals("Flights"))
			link.click();
	}
	
  }

  @AfterTest
  public void closeBrowser() 
  {
	  driver.close();
  }

}
