package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class CheckBox {
  
	WebDriver driver = new ChromeDriver();
	String URL = "http://toolsqa.com/automation-practice-form/";
	
  @BeforeTest
  public void launchBrowser()
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void checkBox() 
  {
	  List <WebElement> options = driver.findElements(By.name("tool"));	
	  System.out.println("Check box options are:");

	  for(int i=0;i<options.size();i++)
	  {
		 WebElement value = options.get(i);
		 System.out.println(value.getAttribute("value"));
		 if(value.getAttribute("value").equals("Selenium IDE"))
			 value.click();
	  }
  }

  @AfterTest
  public void afterTest()
  {
	  driver.close();
  }

}
