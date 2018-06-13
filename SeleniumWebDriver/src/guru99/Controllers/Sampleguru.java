package guru99.Controllers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sampleguru {
	
	public static void main(String[] args)
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/V5/");
		driver.manage().window().maximize();
		driver.findElement(By.name("uid")).sendKeys("mngr137461");
		driver.findElement(By.name("password")).sendKeys("Guru@2018");
		driver.findElement(By.name("btnLogin")).click();
		
		
		/*for(int i=1;i<2;i++)
		{
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			driver.findElement(By.xpath(".//ul[@class='menusubnav']/li[8]/a")).click();
			//Account no: 27812 and account no:40658
			driver.findElement(By.name("accountno")).sendKeys("40658");
			driver.findElement(By.name("ammount")).sendKeys("1000");
			driver.findElement(By.name("desc")).sendKeys("maximum");
		
			driver.findElement(By.name("AccSubmit")).click();
			String currentbal = driver.findElement(By.xpath(".//table[@id='deposit']/tbody/tr[23]/td[2]")).getText();
	
			System.out.println("Current balance for run :"+i+" is "+currentbal);
		}*/
	}
}

