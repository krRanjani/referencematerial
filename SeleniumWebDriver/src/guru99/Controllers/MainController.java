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

@Test(priority=1)
  public void Login() throws IOException 
 {
	int lTestcaseID;
	for(lTestcaseID=1;lTestcaseID<2;lTestcaseID++)
	{
	//IGuruRepository oGuruRepository = new ExcelRepository();
	//LoginController oLoginController = new LoginController(lTestcaseID,driver,oGuruRepository); 
	//oLoginController.Login();
	}
 }

@Test
	public void AddNewCustomer() throws IOException
	{
		int lTestcaseID = 1;
		int aTestcaseID = 1;
		IGuruRepository oGuruRepository = new ExcelRepository();
		CustomerController oCustomerController = new CustomerController(aTestcaseID,driver,oGuruRepository);
		LoginController oLoginController = new LoginController(lTestcaseID,driver,oGuruRepository);
		oLoginController.Login();
		oCustomerController.addNewCustomer();
	}

  @AfterTest
  public void afterTest() 
  {
	  driver.close();
  }

}
