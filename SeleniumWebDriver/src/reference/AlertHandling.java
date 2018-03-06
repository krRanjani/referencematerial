package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

/* Alert Handling class*/
public class AlertHandling {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://demo.guru99.com/test/delete_customer.php";
	
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void alertHandling() 
  {
	  driver.findElement(By.name("cusid")).sendKeys("1234");
	  driver.findElement(By.name("submit")).click();
	  Alert qstn = driver.switchTo().alert();
	  qstn.dismiss();
	  driver.findElement(By.name("cusid")).clear();
	  driver.findElement(By.name("cusid")).sendKeys("6787");
	  driver.findElement(By.name("submit")).click();
	  System.out.println("Question is: "+qstn.getText());
	  qstn.accept();
	  
	  Alert conf = driver.switchTo().alert();
	  System.out.println("Message is: "+conf.getText());
	  conf.accept();
  }

  @AfterTest
  public void closeBrowser() 
  {
	  driver.close();
  }

}
