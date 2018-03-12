package guru99.Controllers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import guru99.DataClasses.NewCustInfo;
import guru99.Interfaces.IGuruRepository;

public class CustomerController {
	
	int testcaseID;
	WebDriver driver;
	IGuruRepository oGuruRepository;
	NewCustInfo oNewCustInfo;
	String error;
	
	public CustomerController(int iTestcaseID,WebDriver wDriver,IGuruRepository objGuruRepository)
	{
		testcaseID = iTestcaseID;
		driver = wDriver;
		oGuruRepository = objGuruRepository;
	}
	
	public void addNewCustomer() throws IOException
	{
		oNewCustInfo = oGuruRepository.readNewCustInfo(testcaseID, driver);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//a[@href='addcustomerpage.php']")).click();
		
		
		
		
	}
 

}
