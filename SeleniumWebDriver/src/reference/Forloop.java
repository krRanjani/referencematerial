package reference;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Forloop {

	public static void main(String[] args) {
		/*// for loop with two variable i & j
		// i will start with 0 and keep on incrementing till 10
		// j will start with 10 and keep on decrementing till 0

		for (int i = 1, j = 8; i <=8 && j >=1; i++, j--) 
		{
			System.out.println("i = " + i + " :: " + "j = " + j);
		}*/
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://ebiz.licindia.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		driver.findElement(By.id("da_textfield-1090-inputEl")).sendKeys("ranjanikr_lic");
		driver.findElement(By.id("da_textfield-1091-inputEl")).sendKeys("Lic@577126");
		//"driver.findElement(By.xpath(".//div[@id='da_datefield-1092-trigger-picker']")).click();
		driver.findElement(By.id("da_datefield-1092-inputEl")).sendKeys("01/06/1987");
		
		driver.findElement(By.id("da_button-1096-btnEl")).click();
		
		WebDriverWait wait = new WebDriverWait (driver,10);
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		System.out.println(msg);
		alert.accept();
		
		
		
	}

}