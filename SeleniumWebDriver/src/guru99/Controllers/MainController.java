package guru99.Controllers;

import org.testng.annotations.*;

import guru99.Interfaces.IGuruRepository;
import guru99.Repository.ExcelRepository;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;


public class MainController {
	
	WebDriver driver = new ChromeDriver();

/*@Test(priority=1)
  public void Login() throws IOException 
 {
	int lTestcaseID;
	for(lTestcaseID=1;lTestcaseID<8;lTestcaseID++)
	{
	IGuruRepository oGuruRepository = new ExcelRepository();
	LoginController oLoginController = new LoginController(lTestcaseID,driver,oGuruRepository); 
	oLoginController.Login();
	}
 }*/

@Test(priority=2)
	public void AddNewCustomer() throws IOException
	{
		int lTestcaseID = 1;
		int aTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		LoginController oLoginController = new LoginController(lTestcaseID,driver,oGuruRepository);
		oLoginController.Login();
//14 21 26
		for(aTestcaseID=1;aTestcaseID<3;aTestcaseID++)
		{
			/*if(aTestcaseID==37 ||aTestcaseID==38||aTestcaseID==47||aTestcaseID==48||aTestcaseID==49)
				continue;*/
			CustomerController oCustomerController = new CustomerController(aTestcaseID,driver,oGuruRepository);
			oCustomerController.addNewCustomer();
		}
	}

@Test(priority=3)
	public void DeleteCustomer() throws IOException
	{
		int lTestcaseID = 1;
		int dTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		LoginController oLoginController = new LoginController(lTestcaseID,driver,oGuruRepository);
		oLoginController.Login();
		
		for(dTestcaseID=1;dTestcaseID<4;dTestcaseID++)
		{
			CustomerController oCustomerController = new CustomerController(dTestcaseID,driver,oGuruRepository);
			oCustomerController.deleteCustomer();

		}
		
	
	}

  @AfterTest
  public void afterTest() 
  {
	 // driver.close();
  }

}
