package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class RadioButton {
  
	WebDriver driver = new ChromeDriver();
	String URL = "http://www.seleniumeasy.com/test/basic-radiobutton-demo.html";
	
	@BeforeTest
	public void launchBrowser() 
	{
		driver.get(URL);
		driver.manage().window().maximize();
	}
	
	@Test
	public void radioButton() 
	{
		driver.findElements(By.name("gender")).get(1).click();
		List<WebElement> options = driver.findElements(By.name("ageGroup"));
		for(int i=0;i<options.size();i++)
		{
			String values = options.get(i).getAttribute("value");
			System.out.println(values);
			if(values.equals("15 - 50"))
				options.get(i).click();
		}
		System.out.println("-------------");
		driver.findElement(By.xpath(".//div[@class='panel-body']/button")).click();
		System.out.println(driver.findElement(By.xpath(".//p[@class='groupradiobutton']")).getText());	
	}
  

	@AfterTest
	public void closeBrowser() 
	{
	  driver.close();
	}

}
