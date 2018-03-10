package reference;

import org.seleniumhq.*;//Upload is not working

import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FileUploadRobot {
	
	WebDriver driver = new FirefoxDriver();
	String URL = "http://my.monsterindia.com/create_account.html";
	 
@BeforeTest
public void launchBrowser()
{
	System.setProperty("webdriver.gecko.driver", "C:\\Ranj\\Jars\\geckodriver.exe");
	driver.get(URL);
	driver.manage().window().maximize();
}

@Test
public void Fileupload() throws AWTException, InterruptedException
{
	// Specify the file location with extension
	StringSelection sel = new StringSelection("C:\\Ranj\\EmailDocs\\1.docx");

	// Copy to clipboard
	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
	System.out.println("selection" +sel);
	
	//This will scroll down the page 
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("scroll(0,350)");

	// This will click on Browse button
	driver.findElement(By.id("wordresume")).click();
	
	// Create object of Robot class
	Robot robot = new Robot();

	// Press and release Enter
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

	// Press and release CTRL+V
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyRelease(KeyEvent.VK_V);
		
	//Press and release Enter 
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);

}

}