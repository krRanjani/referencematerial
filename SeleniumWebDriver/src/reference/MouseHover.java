package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class MouseHover {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://store.demoqa.com/";
	String title = "iPhones | ONLINE STORE";
	
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void mouseHover()
  {
	  Actions act = new Actions(driver);
	  WebElement target = driver.findElement(By.linkText("Product Category"));
	  act.moveToElement(target).build().perform();
	  driver.findElement(By.linkText("iPhones")).click();
	  if(driver.getTitle().equalsIgnoreCase(title))
		  System.out.println("Executed successfully");
  }

  @AfterTest
  public void closeBrowser() 
  {
	  driver.close();
  }

}
