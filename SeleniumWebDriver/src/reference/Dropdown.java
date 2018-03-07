package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class Dropdown {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";

  @BeforeTest
  public void launchBrowser()
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test(priority=1)
  public void dropdown1() 
  {
	 Select days = new Select(driver.findElement(By.id("select-demo")));
	 List<WebElement> daysList = days.getOptions();

	 System.out.println("First drop down values are:");
	 	for(int i=0;i<daysList.size();i++)
	 	{
	 		String value = daysList.get(i).getAttribute("value");
	 		System.out.println(value);
	 		if(value.equalsIgnoreCase("Monday"))
	 		{
	 			daysList.get(i).click();
	 		}
	 	}
	 	System.out.println(driver.findElement(By.xpath(".//p[@class='selected-value']")).getText());
  }
  
  @Test(priority=2)
  public void dropdown2() 
  {
	Select states = new Select(driver.findElement(By.id("multi-select")));
	List<WebElement> statesList = states.getOptions();
	
	 System.out.println("--------------------------");
	 System.out.println("Second drop down values are:");
	 	for(int i=0;i<statesList.size();i++)
	 	{
	 		String value = statesList.get(i).getAttribute("value");
	 		System.out.println(value);
	 	}
	 	
	 	System.out.println(states.isMultiple());
	 	states.selectByIndex(0);
	 	states.selectByValue("New Jersey");
	 	states.selectByVisibleText("Washington");
	 	
	 	states.deselectAll();
  }
  
  @AfterTest
  public void closeBrowser() 
  {
	  driver.close();
  }

}
