package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class SampleRobot {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://demo.guru99.com/V4/index.php";
	String title = "Guru99 Bank Manager HomePage";
	
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void sampleRobot() throws AWTException 
  {
	  driver.findElement(By.name("uid")).sendKeys("mngr120586");
	  driver.findElement(By.name("password")).sendKeys("EbarYpE");
	  
	  //Create an object of Robot class
	  Robot r = new Robot();
	  r.keyPress(KeyEvent.VK_ENTER);
	  r.keyRelease(KeyEvent.VK_ENTER);
	  
	  if(driver.getTitle().equals(title))
		  System.out.println("Logged in successfully");
	  else
		  System.out.println("Failed to login");
	  driver.findElement(By.xpath(".//a[contains(@href,'Logout')]")).click();
  
	  Alert alert = driver.switchTo().alert();
	  alert.accept();
  }

  @AfterTest
  public void closeBrowser() 
  {
	  driver.close();
  }

}
