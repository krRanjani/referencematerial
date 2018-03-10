package reference;

import org.openqa.selenium.firefox.*; //FILE IS NOT GETTING DOWNLOADED
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class WgetDownload {
	
	WebDriver driver = new ChromeDriver();
	String URL = "http://demo.guru99.com/test/yahoo.html";
	
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void wgetDownload() 
  {
	  WebElement download = driver.findElement(By.id("messenger-download"));
	  String source = download.getAttribute("href");
	  String wget_command = "cmd /c C:\\Ranj\\Wget\\wget.exe -P C:\\Ranj --no-check-certificate"+ source;
	  
	  try {
		  	Process exec = Runtime.getRuntime().exec(wget_command);
		  	int exitVal = exec.waitFor();
		  	System.out.println("Exit value is "+exitVal);
	     }catch (InterruptedException | IOException ex)
	       {
	    	 System.out.println(ex.toString());
	       }
    }

  @AfterTest
  public void closeBrowser() 
  {
	  driver.close();
  }

}
