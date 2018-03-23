package reference;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;

public class WindowHandles {
	//Handling multiple windows
			/*String mainwindow = driver.getWindowHandle();
			System.out.println("MAIN:"+mainwindow);
			Set<String> s1 = driver.getWindowHandles();
			Iterator<String> i1 = s1.iterator();
			
			while(i1.hasNext())
			{
				String childwindow = i1.next();
				System.out.println("CHILD:"+childwindow);

				if(!mainwindow.equalsIgnoreCase(childwindow))
					
				{
					driver.switchTo().window(childwindow);
					driver.findElement(By.id("txtCusip")).sendKeys("AAEX");
					driver.findElement(By.id("btnLookup")).click();
					driver.findElement(By.xpath(".//td[@class='radgrdlink']/nobr")).click();
					driver.close();
				}
			}
			driver.switchTo().window(mainwindow);*/


}
