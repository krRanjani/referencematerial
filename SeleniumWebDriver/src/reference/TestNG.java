package reference;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG {
	WebDriver driver = new ChromeDriver();
	String URL = "https://www.google.com/";
	
  @BeforeTest
  public void launchBrowser()
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void checkBox() 
  {
	  System.out.println(driver.getCurrentUrl());
  }


}
