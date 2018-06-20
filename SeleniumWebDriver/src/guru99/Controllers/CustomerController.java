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

import guru99.DataClasses.DeleteCustInfo;
import guru99.DataClasses.EditCustInfo;
import guru99.DataClasses.NewCustInfo;
import guru99.Interfaces.IGuruRepository;

public class CustomerController {
	
	int testcaseID;
	WebDriver driver;
	IGuruRepository oGuruRepository;
	NewCustInfo oNewCustInfo;
	EditCustInfo oEditCustInfo;
	DeleteCustInfo oDeleteCustInfo;
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
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//a[@href='addcustomerpage.php']")).click();
		driver.findElement(By.name("name")).sendKeys(oNewCustInfo.custname);
		driver.findElement(By.name("name")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message")).getText();
		
		List<WebElement> gen = driver.findElements(By.name("rad1"));
		for(int i=0;i<gen.size();i++)
		{
			if(gen.get(i).getAttribute("value").equalsIgnoreCase(oNewCustInfo.gender))
				gen.get(i).click();
		}
		
		driver.findElement(By.id("dob")).sendKeys(oNewCustInfo.dob);
		driver.findElement(By.id("dob")).sendKeys(Keys.TAB);
		String error2 = driver.findElement(By.id("message24")).getText();
		
		driver.findElement(By.name("addr")).sendKeys(oNewCustInfo.address);
		driver.findElement(By.name("addr")).sendKeys(Keys.TAB);
		String error3 = driver.findElement(By.id("message3")).getText();
		
		driver.findElement(By.name("city")).sendKeys(oNewCustInfo.city);
		driver.findElement(By.name("city")).sendKeys(Keys.TAB);
		String error4 = driver.findElement(By.id("message4")).getText();
		
		driver.findElement(By.name("state")).sendKeys(oNewCustInfo.state);
		driver.findElement(By.name("state")).sendKeys(Keys.TAB);
		String error5 = driver.findElement(By.id("message5")).getText();
		
		driver.findElement(By.name("pinno")).sendKeys(oNewCustInfo.pin);
		driver.findElement(By.name("pinno")).sendKeys(Keys.TAB);
		String error6 = driver.findElement(By.id("message6")).getText();
		
		driver.findElement(By.name("telephoneno")).sendKeys(oNewCustInfo.mobno);
		driver.findElement(By.name("telephoneno")).sendKeys(Keys.TAB);
		String error7 = driver.findElement(By.id("message7")).getText();
		
		driver.findElement(By.name("emailid")).sendKeys(oNewCustInfo.email);
		driver.findElement(By.name("emailid")).sendKeys(Keys.TAB);
		String error8 = driver.findElement(By.id("message9")).getText();
		
		driver.findElement(By.name("password")).sendKeys(oNewCustInfo.password);
		driver.findElement(By.name("password")).sendKeys(Keys.TAB);
		String error9 = driver.findElement(By.id("message18")).getText();
		
		error = error1+error2+error3+error4+error5+error6+error7+error8+error9;
		
		driver.findElement(By.name("sub")).click();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			if(error.equalsIgnoreCase(oNewCustInfo.message) && msg.equalsIgnoreCase(oNewCustInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateNewCustStatus(testcaseID, "Pass - "+error, "");
				
			}
			else if(msg.equalsIgnoreCase(oNewCustInfo.message))
			{
				alert.accept();
				oGuruRepository.updateNewCustStatus(testcaseID, "Pass - "+msg, "");
			}
			else if(msg.equalsIgnoreCase(oNewCustInfo.fail))
			{
				alert.accept();
				oGuruRepository.updateNewCustStatus(testcaseID, "Fail - "+msg, "");
			}
			else 
			{	
				alert.accept();
				oGuruRepository.updateNewCustStatus(testcaseID, "Fail - Message mismatch.."+msg, "");

			}
		} catch(Exception Ex1)
		{
			String output = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")).getText();
					//.//table[@id='customer']/tbody/tr[1]/td/p"
			String custid = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]")).getText();
					//.//table[@id='customer']/tbody/tr[4]/td[2]"
			System.out.println(custid+" -"+output);
				if(output.equalsIgnoreCase(oNewCustInfo.message))
					oGuruRepository.updateNewCustStatus(testcaseID, "Pass - "+output, custid);
				else
					oGuruRepository.updateNewCustStatus(testcaseID, "Fail - "+Ex1, "");

		}
	}
	
	public void editCustomer() throws IOException
	{
		oEditCustInfo = oGuruRepository.readEditCustInfo(testcaseID, driver);
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//a[@href='EditCustomer.php']")).click();
		driver.findElement(By.name("cusid")).sendKeys(oEditCustInfo.custid);
		driver.findElement(By.name("cusid")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message14")).getText();
		error = error1;
		driver.findElement(By.name("AccSubmit")).click();
	
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			
			if(error.equalsIgnoreCase(oEditCustInfo.message) && msg.equalsIgnoreCase(oEditCustInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateEditCustStaus(testcaseID, "Pass - "+error);
			}
			else if(msg.equalsIgnoreCase(oEditCustInfo.message))
			{
				alert.accept();
				oGuruRepository.updateEditCustStaus(testcaseID, "Pass - "+msg);
			}
			else if(msg.equalsIgnoreCase(oEditCustInfo.fail))
			{
				alert.accept();
				oGuruRepository.updateEditCustStaus(testcaseID, "Fail - "+msg);
			}
			else 
			{
				alert.accept();
				oGuruRepository.updateEditCustStaus(testcaseID, "Fail - Message mismatch.."+msg);
			}
		} catch(Exception Ex2)
		{
			driver.findElement(By.name("addr")).clear();
			driver.findElement(By.name("addr")).sendKeys(oEditCustInfo.address);
			driver.findElement(By.name("city")).clear();
			driver.findElement(By.name("city")).sendKeys(oEditCustInfo.city);
			driver.findElement(By.name("state")).clear();
			driver.findElement(By.name("state")).sendKeys(oEditCustInfo.state);
			driver.findElement(By.name("pin")).clear();
			driver.findElement(By.name("pin")).sendKeys(oEditCustInfo.pin);
			driver.findElement(By.name("tel")).clear();
			driver.findElement(By.name("tel")).sendKeys(oEditCustInfo.mobno);
			driver.findElement(By.name("email")).clear();
			driver.findElement(By.name("email")).sendKeys(oEditCustInfo.email);
			driver.findElement(By.name("sub")).click();
			
			try
			{
				WebDriverWait wait2 = new WebDriverWait(driver,10);
				wait2.until(ExpectedConditions.alertIsPresent());
				
				Alert none = driver.switchTo().alert();
				String msg = none.getText();
				
				if(msg.equalsIgnoreCase(oEditCustInfo.message))
				{
					none.accept();
					oGuruRepository.updateEditCustStaus(testcaseID, "Pass - "+msg);
				}
				else
				{
					none.accept();
					oGuruRepository.updateEditCustStaus(testcaseID, "Fail - Message mismatch.."+msg);
				}
					

			}catch(Exception Ex3)
			{
				String output = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")).getText();
				//.//table[@id='customer']/tbody/tr[1]/td/p"
				String custid = driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]")).getText();
				//.//table[@id='customer']/tbody/tr[4]/td[2]"
			
				System.out.println(custid+" -"+output);
			
				if(output.equalsIgnoreCase(oEditCustInfo.message))
					oGuruRepository.updateEditCustStaus(testcaseID, "Pass - "+output);
				else
					oGuruRepository.updateEditCustStaus(testcaseID, "Fail - "+Ex3);

			}
		}
	}
	
	public void deleteCustomer() throws IOException //NEED TO CHECK tomrrow
	{
		oDeleteCustInfo = oGuruRepository.readDeleteCustInfo(testcaseID, driver);

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//a[@href='DeleteCustomerInput.php']")).click();
		driver.findElement(By.name("cusid")).sendKeys(oDeleteCustInfo.custid);
		driver.findElement(By.name("cusid")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message14")).getText();
		error = error1;
		
		driver.findElement(By.name("AccSubmit")).click();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String confirm = alert.getText();
			
			
			if(error.equalsIgnoreCase(oDeleteCustInfo.message) && confirm.equalsIgnoreCase(oDeleteCustInfo.warn))
			{
				alert.accept();
				oGuruRepository.updateDeleteCustStatus(testcaseID, "Pass - "+error);
				
			}
			else if(confirm.equalsIgnoreCase(oDeleteCustInfo.conf))
			{
				alert.accept();
				
				WebDriverWait wait1 = new WebDriverWait(driver,5);
				wait1.until(ExpectedConditions.alertIsPresent());
				
				Alert delete = driver.switchTo().alert();
				String msg = delete.getText();
				if(msg.equalsIgnoreCase(oDeleteCustInfo.message))
				{
					delete.accept();
					oGuruRepository.updateDeleteCustStatus(testcaseID, "Pass - "+msg);

				}
				else
				{
					delete.accept();
					oGuruRepository.updateDeleteCustStatus(testcaseID, "Fail - "+msg);
				}
			}
			else if(confirm.equalsIgnoreCase(oDeleteCustInfo.fail))
			{
				alert.accept();
				oGuruRepository.updateDeleteCustStatus(testcaseID, "Fail - "+confirm);
			}
			else 
			{
				alert.accept();
				oGuruRepository.updateDeleteCustStatus(testcaseID, "Fail - Message mismatch.."+confirm);
			}
		} catch(Exception Ex4)
		{
			System.out.println("Exception is "+Ex4);
		}
		
	}
					
}