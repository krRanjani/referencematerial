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
 
	/* @Test(priority=2)
	public void AddNewCustomer() throws IOException
	{
		int lTestcaseID = 1;
		int acTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		oLoginLogoutController.Login();

		for(acTestcaseID=1;acTestcaseID<2;acTestcaseID++)
		{
			CustomerController oCustomerController = new CustomerController(acTestcaseID,driver,oGuruRepository);
			oCustomerController.addNewCustomer();
		}
	}

@Test(priority=3)
	public void EditCustomer() throws IOException
	{
		//Line numbers 1,4 and 5 are to be uncommented when EditCustomer function is executed alone
		//int lTestcaseID = 1;
		int ecTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();

		
		for(ecTestcaseID=1;ecTestcaseID<16;ecTestcaseID++)
		{
			CustomerController oCustomerController = new CustomerController(ecTestcaseID,driver,oGuruRepository);
			oCustomerController.editCustomer();
		}
	}

@Test(priority=13)
	public void DeleteCustomer() throws IOException
	{
		//Line numbers 1,4 and 5 are to be uncommented when DeleteCustomer function is executed alone
		int lTestcaseID = 1;
		int dcTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		oLoginLogoutController.Login();
		
		for(dcTestcaseID=1;dcTestcaseID<27;dcTestcaseID++)
			
		{
			CustomerController oCustomerController = new CustomerController(dcTestcaseID,driver,oGuruRepository);
			oCustomerController.deleteCustomer();
		}
		oGuruRepository.deleteCustidsFromExcel();
	}
*/
@Test(priority=4)
	public void AddNewAccount() throws IOException
	{
		//Line numbers 1,4 and 5 are to be uncommented when AddNewAccount function is executed alone
		int lTestcaseID = 1;
		int aaTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		oLoginLogoutController.Login();
		
		//for(aaTestcaseID=2;aaTestcaseID<27;aaTestcaseID++)
		for(aaTestcaseID=23;aaTestcaseID<25;aaTestcaseID++)
		{
			/*if(aaTestcaseID==1||aaTestcaseID==2||aaTestcaseID==3||aaTestcaseID==4||aaTestcaseID==5||aaTestcaseID==6||aaTestcaseID==12||aaTestcaseID==18||aaTestcaseID==19||aaTestcaseID==20)
				continue;*/
			AccountController oAccountController = new AccountController(aaTestcaseID,driver,oGuruRepository);
			oAccountController.addNewAcct();
		}
	}

/*
@Test(priority=5)
	public void EditAccount() throws IOException
	{
		//Line numbers 1,4 and 5 are to be uncommented when EditAccount function is executed alone
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
		//Line numbers 1,4 and 5 are to be uncommented when DeleteAccount function is executed alone
		//int lTestcaseID = 1;
		int daTestcaseID;
		IGuruRepository oGuruRepository = new ExcelRepository();
		//LoginLogoutController oLoginLogoutController = new LoginLogoutController(lTestcaseID,driver,oGuruRepository);
		//oLoginLogoutController.Login();
		
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
		//Line numbers 1,4 and 5 are to be uncommented when BalanceEnquiry function is executed alone
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
		//Line numbers 1,4 and 5 are to be uncommented when Deposit function is executed alone
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
		//Line numbers 1,4 and 5 are to be uncommented when Withdrawal function is executed alone
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
		//Line numbers 1,4 and 5 are to be uncommented when FundTransfer function is executed alone
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
		//Line numbers 1,4 and 5 are to be uncommented when MiniStatement function is executed alone
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
		//Line numbers 1,4 and 5 are to be uncommented when CustomizedStatement function is executed alone
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
		//oLoginLogoutController.Login(); This needs to be uncommented when only Login and Logout functions are executed
		oLoginLogoutController.Logout();
	}

@AfterTest
  public void afterTest() 
  {
	 //driver.close();
  }
*/

}
