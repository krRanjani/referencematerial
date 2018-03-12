package guru99.Controllers;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import guru99.DataClasses.LoginInfo;
import guru99.Interfaces.IGuruRepository;

public class LoginController {
	
	int testcaseID;
	WebDriver driver;
	IGuruRepository oGuruRepository;
	LoginInfo oLoginInfo;
	String error;

	public LoginController(int iTestcaseID,WebDriver wDriver,IGuruRepository objGuruRepository)
	{
		testcaseID = iTestcaseID;
		driver = wDriver;
		oGuruRepository = objGuruRepository;
	}
	
	public void Login() throws IOException
	{
		oLoginInfo = oGuruRepository.readLoginInfo(testcaseID, driver);
		
		driver.get(oLoginInfo.URL);
		driver.manage().window().maximize();
		
		driver.findElement(By.name("uid")).sendKeys(oLoginInfo.user);
		driver.findElement(By.name("uid")).sendKeys(Keys.TAB);
		String error1 = driver.findElement(By.id("message23")).getText();
		driver.findElement(By.name("password")).sendKeys(oLoginInfo.password);
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

			if(error.equalsIgnoreCase(oLoginInfo.message) && msg.equalsIgnoreCase("User or Password is not valid"))
			{
				oGuruRepository.updateLoginStatus(testcaseID, "Pass - "+oLoginInfo.message);
			}
			else if(error.equalsIgnoreCase(oLoginInfo.message))
			{
				oGuruRepository.updateLoginStatus(testcaseID, "Pass - "+oLoginInfo.message);
			}
			else if(msg.equalsIgnoreCase(oLoginInfo.message))
				oGuruRepository.updateLoginStatus(testcaseID, "Pass - "+oLoginInfo.message);
		
			else
				oGuruRepository.updateLoginStatus(testcaseID, "Fail - Message mismatch");
			
		} catch(NoAlertPresentException Ex1)
		
		{
			if(driver.getTitle().equalsIgnoreCase(oLoginInfo.message))
				oGuruRepository.updateLoginStatus(testcaseID, "Pass - "+oLoginInfo.message);
			else
				oGuruRepository.updateLoginStatus(testcaseID, "Fail - "+Ex1);
		} catch (Exception Ex2)
		
		{
			if(driver.getTitle().equalsIgnoreCase(oLoginInfo.message))
				oGuruRepository.updateLoginStatus(testcaseID, "Pass - "+oLoginInfo.message);
			else
				oGuruRepository.updateLoginStatus(testcaseID, "Fail - "+Ex2);
		}
		
		
		
	}
  
}
