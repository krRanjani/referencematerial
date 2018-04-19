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
	LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository); 
	oLoginLogoutController.Login();
	}
 }*/
 
 @Test(priority=2)
	public void AddNewCustomer() throws IOException
	{
		int lTestcaseID = 1;
		int acTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		oLoginLogoutController.Login();

		for(acTestcaseID=1;acTestcaseID<2;acTestcaseID++)
		{
			//if(acTestcaseID==37 ||acTestcaseID==38||acTestcaseID==47||acTestcaseID==48||acTestcaseID==49)
				//continue;
			CustomerController oCustomerController = new CustomerController(acTestcaseID,driver,oGuruRepository);
			oCustomerController.addNewCustomer();
		}
	}

@Test(priority=3)
	public void EditCustomer() throws IOException
	{
		//int lTestcaseID = 1;
		int ecTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
		for(ecTestcaseID=1;ecTestcaseID<2;ecTestcaseID++)
		{
			CustomerController oCustomerController = new CustomerController(ecTestcaseID,driver,oGuruRepository);
			oCustomerController.editCustomer();
		}
	}

@Test(priority=13)
	public void DeleteCustomer() throws IOException
	{
		//int lTestcaseID = 1;
		int dcTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
		for(dcTestcaseID=1;dcTestcaseID<2;dcTestcaseID++)
		{
			CustomerController oCustomerController = new CustomerController(dcTestcaseID,driver,oGuruRepository);
			oCustomerController.deleteCustomer();
		}
		oGuruRepository.deleteCustidsFromExcel();
	}

@Test(priority=4)
	public void AddNewAccount() throws IOException
	{
		//int lTestcaseID = 1;
		int aaTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
		for(aaTestcaseID=1;aaTestcaseID<2;aaTestcaseID++)
		{
			AccountController oAccountController = new AccountController(aaTestcaseID,driver,oGuruRepository);
			oAccountController.addNewAcct();
		}
	}


@Test(priority=5)
	public void EditAccount() throws IOException
	{
		//int lTestcaseID = 1;
		int eaTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
		for(eaTestcaseID=1;eaTestcaseID<2;eaTestcaseID++)
		{
			AccountController oAccountController = new AccountController(eaTestcaseID,driver,oGuruRepository);
			oAccountController.editAcct();
		}
		
	}

@Test(priority=12)
	public void DeleteAccount() throws IOException
	{
		int lTestcaseID = 1;
		int daTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		oLoginLogoutController.Login();
		
		for(daTestcaseID=1;daTestcaseID<2;daTestcaseID++)
		{
			AccountController oAccountController = new AccountController(daTestcaseID,driver,oGuruRepository);
			oAccountController.deleteAcct();
		}
		
		oGuruRepository.deleteAcctnumsFromExcel();
	}

@Test(priority=7)
	public void BalanceEnquiry() throws IOException
	{
		//int lTestcaseID = 1;
		int bTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
		for(bTestcaseID=1;bTestcaseID<3;bTestcaseID++)
		{
			TransactionController oTransactionController = new TransactionController(bTestcaseID,driver,oGuruRepository);
			oTransactionController.balanceEnquiry();
		}
		
	}

@Test(priority=6)
	public void Deposit() throws IOException
	{
		//int lTestcaseID = 1;
		int deTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
		for(deTestcaseID=1;deTestcaseID<3;deTestcaseID++)
		{
			TransactionController oTransactionController = new TransactionController(deTestcaseID,driver,oGuruRepository);
			oTransactionController.deposit();
		}
	}

@Test(priority=8)
	public void Withdrawal() throws IOException
	{
		//int lTestcaseID = 1;
		int wTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
		for(wTestcaseID=1;wTestcaseID<2;wTestcaseID++)
		{
			if(wTestcaseID==14)
				continue;
			TransactionController oTransactionController = new TransactionController(wTestcaseID,driver,oGuruRepository);
			oTransactionController.withdrawal();
		}
	}

@Test(priority=9)
	public void FundTransfer() throws IOException
	{
		//int lTestcaseID = 1;
		int fTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
		for(fTestcaseID=1;fTestcaseID<2;fTestcaseID++)
		{
			TransactionController oTransactionController = new TransactionController(fTestcaseID,driver,oGuruRepository);
			oTransactionController.fundTransfer();
		}
	}

@Test(priority=10)
	public void MiniStatement() throws IOException
	{
		//int lTestcaseID = 1;
		int mTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
		for(mTestcaseID=1;mTestcaseID<2;mTestcaseID++)
		{
			TransactionController oTransactionController = new TransactionController(mTestcaseID,driver,oGuruRepository);
			oTransactionController.miniStatement();
		}
	}

@Test(priority=11)
public void CustomizedStatement() throws IOException
{
	//int lTestcaseID = 1;
	int cTestcaseID;
	IGuruRepository oGuruRepository = new ExcelRepository();
	//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
	//oLoginLogoutController.Login();
	
	for(cTestcaseID=1;cTestcaseID<2;cTestcaseID++)
	{
		TransactionController oTransactionController = new TransactionController(cTestcaseID,driver,oGuruRepository);
		oTransactionController.customisedStatement();
		
	}
}

@Test(priority=14)
	public void Logout() throws IOException
	{
		int lTestcaseID = 8;
		IGuruRepository oGuruRepository = new ExcelRepository();
		LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		oLoginLogoutController.Logout();
		
	}

  @AfterTest
  public void afterTest() 
  {
	 //driver.close();
  }

}
