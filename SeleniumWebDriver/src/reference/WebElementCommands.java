package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class WebElementCommands {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://demo.guru99.com/test/login.html";
	String Title= "Login Page";
	String actual;
	
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
	  actual = driver.getTitle();
  }
  
  @Test
  public void webelementCommands()
  {
	  WebElement email = driver.findElement(By.id("email"));
	  email.sendKeys("test");
	  email.clear();
	  email.sendKeys("abcd@gmail.com");
	  
	  WebElement pwd = driver.findElement(By.name("passwd"));
	  System.out.println("Is password element displayed - "+pwd.isDisplayed());
	  System.out.println("Is password element enabled - "+pwd.isEnabled());
	  System.out.println("Is password element selected - "+pwd.isSelected());
	  pwd.sendKeys("abcdefghijkl");
	  
	  WebElement button = driver.findElement(By.id("SubmitLogin"));
	  String btn = button.getText();
	  button.click();
	  //OR button.submit();

	  
	  WebElement test = driver.findElement(By.xpath(".//img[@src='http://mymail.guru99.com/logo-guru99.png']"));
	  System.out.println("Text is "+btn);
	  System.out.println("Tag name is "+test.getTagName());
	  System.out.println("CSS value is "+test.getCssValue(btn));
	  System.out.println("Attribute value is "+test.getAttribute(btn));
	  
	  Dimension dim =test.getSize();
	  System.out.println("Height: "+dim.height+ " and Width: "+dim.width);
	  
	  Point point = test.getLocation();
	  System.out.println("X co-ordinate is "+point.x+ " and Y co-ordinate is "+point.y);
	  
	  if(actual.equalsIgnoreCase(Title))
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
