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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import guru99.DataClasses.DeleteAcctInfo;
import guru99.DataClasses.NewAcctInfo;
import guru99.Interfaces.IGuruRepository;

public class AccountController {
	
	int testcaseID;
	WebDriver driver;
	IGuruRepository oGuruRepository;
	NewAcctInfo oNewAcctInfo;
	DeleteAcctInfo oDeleteAcctInfo;
	String error;
	
	public AccountController(int iTestcaseID,WebDriver wDriver,IGuruRepository objGuruRepository)
	{
		testcaseID = iTestcaseID;
		driver = wDriver;
		oGuruRepository = objGuruRepository;
	}
	
	public void addNewAcct() throws IOException
	{
		oNewAcctInfo = oGuruRepository.readNewAcctInfo(testcaseID, driver);
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//ul[@class='menusubnav']/li[5]/a")).click();
		driver.findElement(By.name("cusid")).sendKeys(oNewAcctInfo.custid);
		driver.findElement(By.name("cusid")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message14")).getText();
		
		Select type = new Select(driver.findElement(By.name("selaccount")));
		List<WebElement> typeList = type.getOptions();
		
		for(int i=0;i<typeList.size();i++)
		{
			String value = typeList.get(i).getAttribute("value");
				if(value.equalsIgnoreCase(oNewAcctInfo.acctType))
					typeList.get(i).click();
		}
		
		driver.findElement(By.name("inideposit")).sendKeys(oNewAcctInfo.initdepo);
		driver.findElement(By.name("inideposit")).sendKeys(Keys.TAB);
		String error2 = driver.findElement(By.id("message19")).getText();
		
		error = error1+error2;
		
		driver.findElement(By.name("button2")).click();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			if(error.equalsIgnoreCase(oNewAcctInfo.message) && msg.equalsIgnoreCase(oNewAcctInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateNewAcctStatus(testcaseID, "Pass - "+error, "");
			}
			else if(msg.equalsIgnoreCase(oNewAcctInfo.message))
			{
				alert.accept();
				oGuruRepository.updateNewAcctStatus(testcaseID, "Pass - "+msg, "");
			}
			else if(msg.equalsIgnoreCase(oNewAcctInfo.fail))
			{
				alert.accept();
				oGuruRepository.updateNewAcctStatus(testcaseID, "Fail - "+msg, "");
			}
			else 
				oGuruRepository.updateNewAcctStatus(testcaseID, "Fail - Message mismatch", "");
		}catch (Exception Ex1)
		
		{
			String output = driver.findElement(By.xpath(".//table[@id='account']/tbody/tr[1]/td/p")).getText();
			String acctnum = driver.findElement(By.xpath(".//table[@id='account']/tbody/tr[4]/td[2]")).getText();
			System.out.println(output+" for customer id: "+oNewAcctInfo.custid+" with account number - "+acctnum);
				if(output.equalsIgnoreCase(oNewAcctInfo.message))
					oGuruRepository.updateNewAcctStatus(testcaseID, "Pass - "+output, acctnum);
		}
	}

	public void editAcct()
	{
		
		
	}
	
	public void deleteAcct() throws IOException
	{
		oDeleteAcctInfo = oGuruRepository.readDeleteAcctInfo(testcaseID, driver);

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//ul[@class='menusubnav']/li[7]/a")).click();
		driver.findElement(By.name("accountno")).sendKeys(oDeleteAcctInfo.acctnum);
		driver.findElement(By.name("accountno")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message2")).getText();
		error = error1;
		driver.findElement(By.name("AccSubmit")).click();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String confirm = alert.getText();
			
			
			if(error.equalsIgnoreCase(oDeleteAcctInfo.message) && confirm.equalsIgnoreCase(oDeleteAcctInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateDeleteAcctStatus(testcaseID, "Pass - "+error);
				
			}
			else if(confirm.equalsIgnoreCase(oDeleteAcctInfo.conf))
			{
				alert.accept();
				
				WebDriverWait wait1 = new WebDriverWait(driver,5);
				wait1.until(ExpectedConditions.alertIsPresent());
				
				Alert delete = driver.switchTo().alert();
				String msg = delete.getText();
				if(msg.equalsIgnoreCase(oDeleteAcctInfo.message))
				{
					delete.accept();
					oGuruRepository.updateDeleteAcctStatus(testcaseID, "Pass - "+msg);

				}
				else
				{
					delete.accept();
					oGuruRepository.updateDeleteAcctStatus(testcaseID, "Fail - "+msg);
				}
				
			}
			else if(confirm.equalsIgnoreCase(oDeleteAcctInfo.fail))
			{
				oGuruRepository.updateDeleteAcctStatus(testcaseID, "Fail - "+confirm);
				alert.accept();
			}
			else 
				
				oGuruRepository.updateDeleteAcctStatus(testcaseID, "Fail - Message mismatch");
		} catch(Exception Ex4)
		{
			System.out.println("Exception is "+Ex4);
		}
		
		
	}
}
