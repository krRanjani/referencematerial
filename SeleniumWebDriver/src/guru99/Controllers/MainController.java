package guru99.Controllers;

import org.testng.annotations.*;

import guru99.DataClasses.LoginInfo;
import guru99.Interfaces.IGuruRepository;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class MainController {
	
	WebDriver driver = new ChromeDriver();
	LoginInfo oLoginInfo = new LoginInfo();

@Test
  public void Login() throws IOException 
 {
	int lTestcaseID =1;
	IGuruRepository ob1 = new IGuruRepository();
	LoginController oLoginController = new LoginController(lTestcaseID,driver, oLoginInfo); 
	oLoginController.Login();
 }

  @AfterTest
  public void afterTest() 
  {
	  //driver.close();
  }

}
