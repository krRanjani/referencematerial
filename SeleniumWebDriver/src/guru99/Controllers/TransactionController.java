package guru99.Controllers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
		
		driver.findElement(By.name("ammount")).sendKeys(oDepositInfo.amount);
		driver.findElement(By.name("ammount")).sendKeys(Keys.TAB);
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
				oGuruRepository.updateDepositStatus(testcaseID, "Pass - "+error,"");
				
			}
			else if(msg.equalsIgnoreCase(oDepositInfo.message))
			{
				alert.accept();
				oGuruRepository.updateDepositStatus(testcaseID, "Pass - "+msg,"");
			}
			
			else 
				oGuruRepository.updateDepositStatus(testcaseID, "Fail - Message mismatch","");
		} catch(Exception Ex1)
		{
			String output = driver.findElement(By.xpath(".//table[@id='deposit']/tbody/tr[1]/td/p")).getText();
			String currentbal = driver.findElement(By.xpath(".//table[@id='deposit']/tbody/tr[23]/td[2]")).getText();
			
			System.out.println(output+" with current balance: "+currentbal);
			
			int ans = Integer.parseInt(oDepositInfo.balBefore) + Integer.parseInt(oDepositInfo.amount);
				if((output.equalsIgnoreCase(oDepositInfo.message+" "+oDepositInfo.acctnum)) && (ans==Integer.parseInt(currentbal)))
					{
						oGuruRepository.updateDepositStatus(testcaseID, "Pass",currentbal);
					}
				else if (output.equalsIgnoreCase(oDepositInfo.message+" "+oDepositInfo.acctnum))
					{
						oGuruRepository.updateDepositStatus(testcaseID, "Fail - Current balance is not matching the sum of amount and balBefore",currentbal);
					}
				else
					oGuruRepository.updateDepositStatus(testcaseID, "Fail - "+Ex1,"");
		}
	}

	public void withdrawal() throws IOException
	{
		oWithdrawalInfo = oGuruRepository.readWithdrawalInfo(testcaseID, driver);
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//ul[@class='menusubnav']/li[9]/a")).click();
		driver.findElement(By.name("accountno")).sendKeys(oWithdrawalInfo.acctnum);
		driver.findElement(By.name("accountno")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message2")).getText();
		
		driver.findElement(By.name("ammount")).sendKeys(oWithdrawalInfo.amount);
		driver.findElement(By.name("ammount")).sendKeys(Keys.TAB);
		String error2 = driver.findElement(By.id("message1")).getText();
		
		driver.findElement(By.name("desc")).sendKeys(oWithdrawalInfo.description);
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
			
			if(error.equalsIgnoreCase(oWithdrawalInfo.message) && msg.equalsIgnoreCase(oWithdrawalInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateWithdrawalStatus(testcaseID, "Pass - "+error,"");
				
			}
			else if(msg.equalsIgnoreCase(oWithdrawalInfo.message))
			{
				alert.accept();
				oGuruRepository.updateWithdrawalStatus(testcaseID, "Pass - "+msg,"");
			}
			
			else 
				oGuruRepository.updateWithdrawalStatus(testcaseID, "Fail - Message mismatch","");
		} catch(Exception Ex2)
		{
			String output = driver.findElement(By.xpath(".//table[@id='withdraw']/tbody/tr[1]/td/p")).getText();
			String currentbal = driver.findElement(By.xpath(".//table[@id='deposit']/tbody/tr[23]/td[2]")).getText();
			
			System.out.println(output+" with current balance: "+currentbal);
			
			int ans = Integer.parseInt(oWithdrawalInfo.balBefore) + Integer.parseInt(oWithdrawalInfo.amount);
				if((output.equalsIgnoreCase(oWithdrawalInfo.message+" "+oWithdrawalInfo.acctnum)) && (ans==Integer.parseInt(currentbal)))
					{
						oGuruRepository.updateWithdrawalStatus(testcaseID, "Pass",currentbal);
					}
				else if (output.equalsIgnoreCase(oWithdrawalInfo.message+" "+oWithdrawalInfo.acctnum))
					{
						oGuruRepository.updateWithdrawalStatus(testcaseID, "Fail - Current balance is not matching the sum of amount and balBefore",currentbal);
					}
				else
					oGuruRepository.updateWithdrawalStatus(testcaseID, "Fail - "+Ex2,"");
		}
	}

	public void fundTransfer() throws IOException
	{
		oFundTransferInfo = oGuruRepository.readFundTransferInfo(testcaseID, driver);
		
	}

	public void balanceEnquiry() throws IOException
	{
		oBalEnquiryInfo = oGuruRepository.readBalEnquiryInfo(testcaseID, driver);
		
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
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Pass - "+error,"");
			}
			else if(msg.equalsIgnoreCase(oBalEnquiryInfo.message))
			{
				alert.accept();
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Pass - "+msg,"");	
			}
			else
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Fail - Message mismatch","");
		}catch(Exception e)
		{
			String balmsg = driver.findElement(By.xpath(".//table[@id='balenquiry']/tbody/tr[1]/td/p")).getText();
			String balance = driver.findElement(By.xpath(".//table[@id='balenquiry']/tbody/tr[16]/td[2]")).getText();
			
			
			if(balmsg.equalsIgnoreCase(oBalEnquiryInfo.message+" "+oBalEnquiryInfo.acctnum))
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Pass",balance);
			else
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Fail - "+e,"");
		}
		
		
	}
	
	public void miniStatement() throws IOException
	{
		oBankStatementInfo = oGuruRepository.readBankStatementInfo(testcaseID, driver);
	}

	public void customisedStatement() throws IOException
	{
		 oBankStatementInfo = oGuruRepository.readBankStatementInfo(testcaseID, driver);
	}							
				 
				 
}
