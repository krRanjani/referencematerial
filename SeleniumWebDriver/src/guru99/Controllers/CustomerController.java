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
		driver.findElement(By.name("name")).sendKeys(oNewCustInfo.custname);
		driver.findElement(By.name("name")).sendKeys(Keys.TAB);//25
		String error1 = driver.findElement(By.id("message")).getText();
		
		List<WebElement> gen = driver.findElements(By.name("rad1"));
		for(int i=0;i<=gen.size();i++)
		{
			if(gen.get(i).getAttribute("value").equalsIgnoreCase(oNewCustInfo.gender))
				gen.get(i).click();
		}
		
		driver.findElement(By.id("dob")).sendKeys(oNewCustInfo.dob);
		driver.findElement(By.id("dob")).sendKeys(Keys.TAB);
		String error2 = driver.findElement(By.id("message24")).getText();
		
		driver.findElement(By.name("addr")).sendKeys(oNewCustInfo.address);
		driver.findElement(By.name("addr")).sendKeys(Keys.TAB); //50
		String error3 = driver.findElement(By.id("message3")).getText();
		
		driver.findElement(By.name("city")).sendKeys(oNewCustInfo.city);
		driver.findElement(By.name("city")).sendKeys(Keys.TAB);//25
		String error4 = driver.findElement(By.id("message4")).getText();
		
		driver.findElement(By.name("state")).sendKeys(oNewCustInfo.state);
		driver.findElement(By.name("state")).sendKeys(Keys.TAB);//25
		String error5 = driver.findElement(By.id("message5")).getText();
		
		driver.findElement(By.name("pinno")).sendKeys(oNewCustInfo.pin);
		driver.findElement(By.name("pinno")).sendKeys(Keys.TAB);//6
		String error6 = driver.findElement(By.id("message6")).getText();
		
		driver.findElement(By.name("telephoneno")).sendKeys(oNewCustInfo.mobno);
		driver.findElement(By.name("telephoneno")).sendKeys(Keys.TAB);//15
		String error7 = driver.findElement(By.id("message7")).getText();
		
		driver.findElement(By.name("emailid")).sendKeys(oNewCustInfo.email);
		driver.findElement(By.name("emailid")).sendKeys(Keys.TAB);//30
		String error8 = driver.findElement(By.id("message9")).getText();
		
		driver.findElement(By.name("password")).sendKeys(oNewCustInfo.password);
		driver.findElement(By.name("password")).sendKeys(Keys.TAB);
		String error9 = driver.findElement(By.id("message18")).getText();
		
		error = error1+error2+error3+error4+error5+error6+error7+error8+error9;
		
		driver.findElement(By.name("sub")).submit();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			if(error.equalsIgnoreCase(oNewCustInfo.message) && msg.equalsIgnoreCase("please fill all fields"))
			{
				oGuruRepository.updateNewCustStatus(testcaseID, "Pass", custid);
			}
		} catch(Exception e)
		{
			
		}
	}
				
				
				
				
				
				
	}