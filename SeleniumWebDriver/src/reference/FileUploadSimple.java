package reference;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class FileUploadSimple {

	WebDriver driver = new ChromeDriver();
	String URL = "http://demo.guru99.com/test/upload/";
	String msg = "1 file\n" + 
			"has been successfully uploaded.";
	
  @BeforeTest
  public void launchBrowser() 
  {
	  driver.get(URL);
	  driver.manage().window().maximize();
  }

  @Test
  public void fileupload()
  {
	  driver.findElement(By.id("uploadfile_0")).sendKeys("C://Ranj//EmailDocs//New Bitmap Image.bmp");
	  driver.findElement(By.id("terms")).click();
	  driver.findElement(By.id("submitbutton")).click();
	  if(driver.findElement(By.xpath(".//h3[@id='res']/center")).getText().equalsIgnoreCase(msg))
		  System.out.println("Uploaded successfully!!");
	  else
		  System.out.println("Upload failed");
  }

  @AfterTest
  public void closeBrowser() 
  {
	  driver.close();
  }

}
