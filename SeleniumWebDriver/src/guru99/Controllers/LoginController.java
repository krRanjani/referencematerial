package guru99.Controllers;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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
	

	public LoginController(int iTestcaseID,WebDriver wDriver,IGuruRepository objGuruRepository,LoginInfo objLoginInfo)
	{
		testcaseID = iTestcaseID;
		driver = wDriver;
		//oGuruRepository = objGuruRepository;
		oGuruRepository = objGuruRepository;
		oLoginInfo = objLoginInfo;
	}
	
	public void Login() throws IOException
	{
		oLoginInfo = oGuruRepository.readLoginInfo(testcaseID, driver);
		
		driver.get(oLoginInfo.URL);
		driver.manage().window().maximize();
		
		driver.findElement(By.name("uid")).sendKeys(oLoginInfo.user);
		driver.findElement(By.name("password")).sendKeys(oLoginInfo.password);
		driver.findElement(By.name("btnLogin")).submit();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			if(msg.equalsIgnoreCase(oLoginInfo.message))
				
				oGuruRepository.updateLoginStatus(testcaseID, "Pass - "+oLoginInfo.message);
			else
				oGuruRepository.updateLoginStatus(testcaseID, "Fail");
			
		}catch(Exception e)
		
		{
			System.out.println(driver.getTitle());
			System.out.println("Exception is "+e);
		}
		
		
	}
  
}
