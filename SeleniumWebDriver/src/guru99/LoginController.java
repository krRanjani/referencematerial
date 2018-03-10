package guru99;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginController {
	
	String URL;
	String user;
	String password;
	int testcaseID;
	WebDriver driver;
	String message;
	GuruRepository oGuruRepository;
	
	public LoginController(int iTestcaseID,WebDriver wDriver,GuruRepository objGuruRepository,String sUrl,String sUser,String sPassword,String sMessage)
	{
		testcaseID = iTestcaseID;
		driver = wDriver;
		URL = sUrl;
		user =  sUser;
		password = sPassword;
		message = sMessage;
		oGuruRepository = objGuruRepository;
	}
	
	public void Login()
	{
		driver.get(URL);
		driver.manage().window().maximize();
		
		driver.findElement(By.name("uid")).sendKeys(user);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).submit();
		
		try
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			String msg = alert.getText();
			
			if(msg.equalsIgnoreCase(message))
				
				oGuruRepository.updateLoginStatus(testcaseID, "Pass- ");
		}
		
		
	}
  
}
