package guru99.Controllers;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import guru99.DataClasses.LoginLogoutInfo;
import guru99.Interfaces.IGuruRepository;

public class LoginLogoutController {
	
	int testcaseID;
	WebDriver driver;
	IGuruRepository oGuruRepository;
	LoginLogoutInfo oLoginLogoutInfo;
	String error;

	public LoginLogoutController(int iTestcaseID,WebDriver wDriver,IGuruRepository objGuruRepository)
	{
		testcaseID = iTestcaseID;
		driver = wDriver;
		oGuruRepository = objGuruRepository;
	}
	
	public void Login() throws IOException
	{
		oLoginLogoutInfo = oGuruRepository.readLoginInfo(testcaseID, driver);
		
		driver.get(oLoginLogoutInfo.URL);
		driver.manage().window().maximize();
		
		driver.findElement(By.name("uid")).sendKeys(oLoginLogoutInfo.user);
		driver.findElement(By.name("uid")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message23")).getText();
		driver.findElement(By.name("password")).sendKeys(oLoginLogoutInfo.password);
		driver.findElement(By.name("password")).sendKeys(Keys.TAB);
		String error2 = driver.findElement(By.id("message18")).getText();
		driver.findElement(By.name("btnLogin")).submit();
		
		error = error1+error2;
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			alert.accept();

			if(error.equalsIgnoreCase(oLoginLogoutInfo.message) && msg.equalsIgnoreCase(oLoginLogoutInfo.message))
			{
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Pass - "+oLoginLogoutInfo.message);
			}
			else if(error.equalsIgnoreCase(oLoginLogoutInfo.message))
			{
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Pass - "+oLoginLogoutInfo.message);
			}
			else if(msg.equalsIgnoreCase(oLoginLogoutInfo.message))
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Pass - "+oLoginLogoutInfo.message);
		
			else
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Fail - Message mismatch");
			
		} catch(NoAlertPresentException Ex1)
		
		{
			if(driver.getTitle().equalsIgnoreCase(oLoginLogoutInfo.message))
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Pass - "+oLoginLogoutInfo.message);
			else
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Fail - "+Ex1);
		} catch (Exception Ex2)
		
		{
			if(driver.getTitle().equalsIgnoreCase(oLoginLogoutInfo.message))
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Pass - "+oLoginLogoutInfo.message);
			else
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Fail - "+Ex2);
		}
	}
  
	public void Logout() throws IOException
	{		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath(".//a[@href='Logout.php']")).click();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String success = alert.getText();
			
			if(success.equalsIgnoreCase(oLoginLogoutInfo.message))
			{
				alert.accept();
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Pass - "+success);
			}
			else 
				oGuruRepository.updateLoginLogoutStatus(testcaseID, "Fail - Message mismatch");
			
		} catch(Exception Ex3)
		{
			System.out.println("Exception is "+Ex3);
		}
		
	}
		
}
