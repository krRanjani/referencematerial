package guru99.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import guru99.DataClasses.BalEnquiryInfo;
import guru99.DataClasses.BankStatementInfo;
import guru99.DataClasses.DepositInfo;
import guru99.DataClasses.FundTransferInfo;
import guru99.DataClasses.WithdrawalInfo;
import guru99.Interfaces.IGuruRepository;

public class TransactionController {

	
	int testcaseID;
	WebDriver driver;
	IGuruRepository oGuruRepository;
	DepositInfo oDepositInfo;
	WithdrawalInfo oWithdrawalInfo;
	FundTransferInfo oFundTransferInfo;
	BalEnquiryInfo oBalEnquiryInfo;
	BankStatementInfo oBankStatementInfo;
	String error;
	
	public TransactionController(int iTestcaseID,WebDriver wDriver,IGuruRepository objGuruRepository)
	{
		testcaseID = iTestcaseID;
		driver = wDriver;
		oGuruRepository = objGuruRepository;
	}
	
	public void deposit() throws IOException
	{
		oDepositInfo = oGuruRepository.readDepositInfo(testcaseID, driver);
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//ul[@class='menusubnav']/li[8]/a")).click();
		driver.findElement(By.name("accountno")).sendKeys(oDepositInfo.acctnum);
		driver.findElement(By.name("accountno")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message2")).getText();
		
		driver.findElement(By.id("ammount")).sendKeys(oDepositInfo.amount);
		driver.findElement(By.id("ammount")).sendKeys(Keys.TAB);
		String error2 = driver.findElement(By.id("message1")).getText();
		
		driver.findElement(By.name("desc")).sendKeys(oDepositInfo.description);
		driver.findElement(By.name("desc")).sendKeys(Keys.TAB);
		String error3 = driver.findElement(By.id("message17")).getText();
		
		error = error1+error2+error3;
		
		driver.findElement(By.name("AccSubmit")).click();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			if(error.equalsIgnoreCase(oDepositInfo.message) && msg.equalsIgnoreCase(oDepositInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateDepositStatus(testcaseID, "Pass - "+error);
				
			}
			else if(msg.equalsIgnoreCase(oDepositInfo.message))
			{
				alert.accept();
				oGuruRepository.updateDepositStatus(testcaseID, "Pass - "+msg);
			}
			
			else 
				oGuruRepository.updateDepositStatus(testcaseID, "Fail - Message mismatch");
		} catch(Exception Ex1)
		{
			String output = driver.findElement(By.xpath(".//table[@id='customer']/tbody/tr[1]/td/p")).getText();
			String custid = driver.findElement(By.xpath(".//table[@id='customer']/tbody/tr[4]/td[2]")).getText();
			System.out.println(custid+" -"+output);
				if(output.equalsIgnoreCase(oDepositInfo.message))
					oGuruRepository.updateDepositStatus(testcaseID, "Pass - "+output);
		}
	}

	public void withdrawal()
	{
		WithdrawalInfo oWithdrawalInfo = new WithdrawalInfo();
	}

	public void fundTransfer()
	{
		FundTransferInfo oFundTransferInfo = new FundTransferInfo();
		
	}

	public void balanceEnquiry() throws IOException
	{
		BalEnquiryInfo oBalEnquiryInfo = new BalEnquiryInfo();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//ul[@class='menusubnav']/li[12]/a")).click();
		driver.findElement(By.name("accountno")).sendKeys(oBalEnquiryInfo.acctnum);
		driver.findElement(By.name("accountno")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message2")).getText();
		
		error = error1;
		
		driver.findElement(By.name("AccSubmit")).click();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			if(error.equalsIgnoreCase(oBalEnquiryInfo.message) && msg.equalsIgnoreCase(oBalEnquiryInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Pass - "+error);
			}
			else if(msg.equalsIgnoreCase(oBalEnquiryInfo.message))
			{
				alert.accept();
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Pass - "+msg);	
			}
			else
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Fail - Message mismatch");
		}catch(Exception e)
		{
			String balmsg = driver.findElement(By.xpath(".//table[@id='balenquiry']/tbody/tr[1]/td/p")).getText();
			String balance = driver.findElement(By.xpath(".//table[@id='balenquiry']/tbody/tr[4]/td[2]")).getText();
			
			if(balmsg.equalsIgnoreCase(oBalEnquiryInfo.message+" "+oBalEnquiryInfo.acctnum))
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Pass - "+balmsg+"> "+balance);
			else
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Fail - "+e);
		}
		
		
	}
	
	public void miniStatement()
	{
		BankStatementInfo oBankStatementInfo = new BankStatementInfo();

	
	}

	public void customisedStatement()
	{
		BankStatementInfo oBankStatementInfo = new BankStatementInfo();
	}

}
