package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class ContextDoubleClick {
	
	WebDriver driver = new ChromeDriver();
	String URL = "https://www.facebook.com/";
	
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void contextClick() throws InterruptedException
  {
	  WebElement link = driver.findElement(By.linkText("Forgot account?"));
	  WebElement user = driver.findElement(By.id("email"));
	  Actions act = new Actions(driver);
	  act.moveToElement(user).click().keyDown(user,Keys.SHIFT).sendKeys(user,"facebook")
	  .keyUp(user,Keys.SHIFT).doubleClick(user).build().perform();
	  act.contextClick(link).build().perform();
	  act.sendKeys(Keys.DOWN).build().perform();
	  
	
  }

  @AfterTest
  public void closeBrowser() 
  {
	  //driver.close();
  }

}
