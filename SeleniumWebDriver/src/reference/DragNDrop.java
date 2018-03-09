package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class DragNDrop {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://demo.guru99.com/test/drag_drop.html";
	
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void dragNdrop() 
  {
	  WebElement bank = driver.findElement(By.xpath(".//li[@id='credit2']/a"));
	  WebElement dAcct = driver.findElement(By.xpath(".//ol[@id='bank']/li"));
	  WebElement sales = driver.findElement(By.xpath(".//li[@id='credit1']/a"));
	  WebElement cAcct = driver.findElement(By.xpath(".//ol[@id='loan']/li"));
	  WebElement amt = driver.findElement(By.xpath(".//li[@id='fourth']/a"));
	  WebElement dAmt = driver.findElement(By.xpath(".//ol[@id='amt7']/li"));
	  WebElement cAmt = driver.findElement(By.xpath(".//ol[@id='amt8']/li"));
	  
	  
	  Actions act = new Actions(driver);
	  act.dragAndDrop(bank, dAcct).build().perform();
	  act.dragAndDrop(sales, cAcct).build().perform();
	  act.dragAndDrop(amt, dAmt).build().perform();
	  act.dragAndDrop(amt, cAmt).build().perform();
	  System.out.println(driver.findElement(By.xpath(".//div[@id='equal']/a")).getText());
	  }

  @AfterTest
  public void closeBrowser() 
  {
	 driver.close();
  }

}
