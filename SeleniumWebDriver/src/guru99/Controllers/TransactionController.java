package guru99.Controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
		oDepositInfo = oGuruRepository.readDepositInfo(testcaseID,driver);
		
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
			WebDriverWait wait = new WebDriverWait(driver,15);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			if(error.equalsIgnoreCase(oDepositInfo.message) && msg.equalsIgnoreCase(oDepositInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateDepositStatus(testcaseID, "Pass - "+oDepositInfo.warn,"0");
				
			}
			else if(error.equalsIgnoreCase(oDepositInfo.message) && msg.equalsIgnoreCase(oDepositInfo.fail))
			{
				alert.accept();
				oGuruRepository.updateDepositStatus(testcaseID, "Pass - "+oDepositInfo.fail, "0");
			}
			else if(msg.equalsIgnoreCase(oDepositInfo.message))
			{
				alert.accept();
				oGuruRepository.updateDepositStatus(testcaseID, "Pass - "+msg,"0");
			}
			else 
				//if((!error.equalsIgnoreCase(oDepositInfo.message)) ||(!msg.equalsIgnoreCase(oDepositInfo.warn))||(!msg.equalsIgnoreCase(oDepositInfo.fail)))
			{
				alert.accept();
				oGuruRepository.updateDepositStatus(testcaseID, "Fail - Message mismatch.."+msg,"0");
			}
		} catch(Exception Ex1)
		{
			
			String output = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")).getText();
			//.//table[@id='deposit']/tbody/tr[1]/td/p
			String currentbal = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[23]/td[2]")).getText();
			//.//table[@id='deposit']/tbody/tr[23]/td[2]
			
			System.out.println(output+" with current balance: "+currentbal);
			
			int ans = Integer.parseInt(oDepositInfo.balBefore) + Integer.parseInt(oDepositInfo.amount);
				if((output.equalsIgnoreCase(oDepositInfo.message+" "+oDepositInfo.acctnum)) && (ans==Integer.parseInt(currentbal)))
					{
						oGuruRepository.updateDepositStatus(testcaseID, "Pass",currentbal);
					}
				else if ((output.equalsIgnoreCase(oDepositInfo.message+" "+oDepositInfo.acctnum)) && ((oDepositInfo.amount).length()>6))
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
			WebDriverWait wait = new WebDriverWait(driver,15);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			if(error.equalsIgnoreCase(oWithdrawalInfo.message) && msg.equalsIgnoreCase(oWithdrawalInfo.warn))
			{
				alert.accept();
				System.out.println("First if "+error+" and "+msg);
				oGuruRepository.updateWithdrawalStatus(testcaseID, "Pass - "+oWithdrawalInfo.warn,"0");
				
			}
			
			else if(error.equalsIgnoreCase(oWithdrawalInfo.message) && msg.equalsIgnoreCase(oWithdrawalInfo.fail))
			{
				alert.accept();
				System.out.println("Second if "+error+" and "+msg);
				oGuruRepository.updateWithdrawalStatus(testcaseID, "Pass - "+oWithdrawalInfo.fail,"0");
				
			}
		
			else if(msg.equalsIgnoreCase(oWithdrawalInfo.message))
			{
				alert.accept();
				System.out.println("third if "+error+" and "+msg);
				oGuruRepository.updateWithdrawalStatus(testcaseID, "Pass - "+msg,"0");
			}
			
			else 
			{
				alert.accept();
				System.out.println("4th if "+error+" and "+msg);
				oGuruRepository.updateWithdrawalStatus(testcaseID, "Fail - Message Mismatch.."+msg,"0");
				//NEED TO check why ZERO is needed here!!?
			}
		} catch(Exception Ex2)
		{

			String output = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")).getText();
			//.//table[@id='withdraw']/tbody/tr[1]/td/p
			String currentbal = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[23]/td[2]")).getText();
			//.//table[@id='withdraw']/tbody/tr[23]/td[2]
			
			System.out.println(output+" with current balance: "+currentbal);
			
			int ans = Integer.parseInt(oWithdrawalInfo.balBefore) - Integer.parseInt(oWithdrawalInfo.amount);
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
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//ul[@class='menusubnav']/li[10]/a")).click();
		
		driver.findElement(By.name("payersaccount")).sendKeys(oFundTransferInfo.payersacctnum);
		driver.findElement(By.name("payersaccount")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message10")).getText();
		
		driver.findElement(By.name("payeeaccount")).sendKeys(oFundTransferInfo.payeesacctnum);
		driver.findElement(By.name("payeeaccount")).sendKeys(Keys.TAB);
		String error2 = driver.findElement(By.id("message11")).getText();

		driver.findElement(By.name("ammount")).sendKeys(oFundTransferInfo.amount);
		driver.findElement(By.name("ammount")).sendKeys(Keys.TAB);
		String error3 = driver.findElement(By.id("message1")).getText();
		
		driver.findElement(By.name("desc")).sendKeys(oFundTransferInfo.description);
		driver.findElement(By.name("desc")).sendKeys(Keys.TAB);
		String error4 = driver.findElement(By.id("message17")).getText();
		
		error = error1+error2+error3+error4;
		
		driver.findElement(By.name("AccSubmit")).click();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			System.out.println("Alert says "+msg);
			
			if(error.equalsIgnoreCase(oFundTransferInfo.message) && msg.equalsIgnoreCase(oFundTransferInfo.warn))
			{
				alert.accept();
				System.out.println("First if"+error+" and"+msg);
				oGuruRepository.updateFundTransferStatus(testcaseID, "Pass - "+error);
				
			}
			else if(msg.equalsIgnoreCase(oFundTransferInfo.message))
			{
				alert.accept();
				System.out.println("Second if "+msg);
				oGuruRepository.updateFundTransferStatus(testcaseID, "Pass - "+msg);
			}
			
			else 
			{
				alert.accept();
				System.out.println("Third if "+msg);
				oGuruRepository.updateFundTransferStatus(testcaseID, "Fail - Message Mismatch.."+msg);
			}
		} catch(Exception Ex3)
		{
			String output = driver.findElement(By.xpath("html/body/table/tbody/tr[1]/td/p")).getText();
			//.//table[@id='layout']/tbody/tr[1]/td/p
			
			System.out.println(output);
			
				if(output.equalsIgnoreCase(oFundTransferInfo.message))
					
						oGuruRepository.updateFundTransferStatus(testcaseID, "Pass - "+output);	
				else
					oGuruRepository.updateWithdrawalStatus(testcaseID, "Fail - "+Ex3,"");
		}
		
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
			{
				alert.accept();
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Fail - Message mismatch.."+msg,"");
			}
		}catch(Exception Ex4)
		{
			String balmsg = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")).getText();
			//.//table[@id='balenquiry']/tbody/tr[1]/td/p
			String balance = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[16]/td[2]")).getText();
			//.//table[@id='balenquiry']/tbody/tr[16]/td[2]
			
			if(balmsg.equalsIgnoreCase(oBalEnquiryInfo.message+" "+oBalEnquiryInfo.acctnum))
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Pass",balance);
			else
				oGuruRepository.updateBalEnquiryStatus(testcaseID, "Fail - "+Ex4,"");
		}
	}
	
	public void miniStatement() throws IOException
	{
		oBankStatementInfo = oGuruRepository.readBankStatementInfo(testcaseID, driver);
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//ul[@class='menusubnav']/li[13]/a")).click();
		driver.findElement(By.name("accountno")).sendKeys(oBankStatementInfo.Macctnum);
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
			
			if(error.equalsIgnoreCase(oBankStatementInfo.Mmessage) && msg.equalsIgnoreCase(oBankStatementInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateBankStatementStatus(testcaseID, "Pass - "+error);
			}
			else if(msg.equalsIgnoreCase(oBankStatementInfo.Mmessage))
			{
				alert.accept();
				oGuruRepository.updateBankStatementStatus(testcaseID, "Pass - "+msg);	
			}
			else
			{
				alert.accept();
				oGuruRepository.updateBankStatementStatus(testcaseID, "Fail - Message mismatch.."+msg);
			}
		}catch(Exception Ex5)
		{
			String transmsg = driver.findElement(By.xpath("html/body/table/tbody/tr[1]/td/p")).getText();
			//.//table[@id='layout']/tbody/tr[1]/td/p
			
			if(transmsg.equalsIgnoreCase(oBankStatementInfo.Mmessage+": "+oBankStatementInfo.Macctnum))
				oGuruRepository.updateBankStatementStatus(testcaseID, "Pass");
			else
				oGuruRepository.updateBankStatementStatus(testcaseID, "Fail - "+Ex5);
		}	
	}

	public void customisedStatement() throws IOException
	{
		 oBankStatementInfo = oGuruRepository.readBankStatementInfo(testcaseID, driver);
		 
		 	driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath(".//ul[@class='menusubnav']/li[14]/a")).click();
			
			driver.findElement(By.name("accountno")).sendKeys(oBankStatementInfo.Cacctnum);
			driver.findElement(By.name("accountno")).sendKeys(Keys.TAB);
			String error1 = driver.findElement(By.id("message2")).getText();
			
			driver.findElement(By.name("fdate")).sendKeys(oBankStatementInfo.fromdate);
			driver.findElement(By.name("fdate")).sendKeys(Keys.TAB);
			String error2 = driver.findElement(By.id("message26")).getText();
			
			
			driver.findElement(By.name("tdate")).sendKeys(oBankStatementInfo.todate);
			driver.findElement(By.name("tdate")).sendKeys(Keys.TAB);
			String error3 = driver.findElement(By.id("message27")).getText();
			
			driver.findElement(By.name("amountlowerlimit")).sendKeys(oBankStatementInfo.mintransvalue);
			driver.findElement(By.name("amountlowerlimit")).sendKeys(Keys.TAB);
			String error4 = driver.findElement(By.id("message12")).getText();
			
			driver.findElement(By.name("numtransaction")).sendKeys(oBankStatementInfo.numoftrans);
			driver.findElement(By.name("numtransaction")).sendKeys(Keys.TAB);
			String error5 = driver.findElement(By.id("message13")).getText();
			
			error = error1+error2+error3+error4+error5;
			
			driver.findElement(By.name("AccSubmit")).click();
			
			try
			{
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.alertIsPresent());
				
				Alert alert = driver.switchTo().alert();
				String msg = alert.getText();
				
				if(error.equalsIgnoreCase(oBankStatementInfo.Cmessage) && msg.equalsIgnoreCase(oBankStatementInfo.warn))
				{
					alert.accept();
					oGuruRepository.updateBankStatementStatus(testcaseID, "Pass - "+error);
				}
				else if(msg.equalsIgnoreCase(oBankStatementInfo.Cmessage))
				{
					alert.accept();
					oGuruRepository.updateBankStatementStatus(testcaseID, "Pass - "+msg);	
				}
				else
				{
					alert.accept();
					oGuruRepository.updateBankStatementStatus(testcaseID, "Fail - Message mismatch.."+msg);
				}
			}catch(Exception Ex6)
			{
				String transmsg = driver.findElement(By.xpath(".//table[@id='layout']/tbody/tr[1]/td/p")).getText();
				
				SimpleDateFormat sm = new SimpleDateFormat("yyyy-mm-dd");
				String strDate1 = sm.format(oBankStatementInfo.fromdate);
				String strDate2 = sm.format(oBankStatementInfo.todate);
				
				if(transmsg.equalsIgnoreCase(oBankStatementInfo.Cmessage+": "+oBankStatementInfo.Macctnum+" from Date: "+strDate1+" to: "+strDate2))
					oGuruRepository.updateBankStatementStatus(testcaseID, "Pass");
				else
					oGuruRepository.updateBankStatementStatus(testcaseID, "Fail - "+Ex6);
			}
	}							
				 
				 
}
