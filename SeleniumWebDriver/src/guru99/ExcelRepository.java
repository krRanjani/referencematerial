package guru99;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class ExcelRepository implements GuruRepository {
  
	public LoginController readLoginInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readLoginInfoFromExcel( TestcaseID, Driver);
	}
	
	private LoginController readLoginInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File("C:\\Ranj\\git\\selenium-workspace\\referencematerial\\SeleniumWebDriver\\src\\guru99\\Guru99_testdata.xlsx");
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Login");
		
		int testcaseID = TestcaseID;
		WebDriver driver = Driver;
		String URL = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		String user = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		String password = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		String message = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		wb.close();
		
		return  new LoginController(testcaseID,driver,URL,user,password,message);
	}
	
	public void updateLoginStatus(int TestcaseID,String status)
	{
		updateLoginStatusInExcel(TestcaseID,status);
	}
	
	private void updateLoginStatusInExcel(int TestcaseID,String status)
	{
		
	}

}
	

