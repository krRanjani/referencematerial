package guru99.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import guru99.DataClasses.LoginInfo;
import guru99.Interfaces.IGuruRepository;

public class ExcelRepository implements IGuruRepository {
  
	public LoginInfo readLoginInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readLoginInfoFromExcel( TestcaseID, Driver);
	}
	
	private LoginInfo readLoginInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File("C:\\Ranj\\git\\selenium-workspace\\referencematerial\\SeleniumWebDriver\\src\\guru99\\Guru99_testdata.xlsx");
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Login");
		
		int testcaseID = TestcaseID;
		//WebDriver driver = Driver;
		LoginInfo oLoginInfo = new LoginInfo();
		
		oLoginInfo.URL = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oLoginInfo.user = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		oLoginInfo.password = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		oLoginInfo.message = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		wb.close();
        String result = "Test case number:{0} having URL:{1} with user:{2} , password:{3} and message:{4}";

		System.out.println(String.format(result, testcaseID,oLoginInfo.URL,oLoginInfo.user,oLoginInfo.password,oLoginInfo.message));
		return oLoginInfo;
	}
	
	public void updateLoginStatus(int TestcaseID,String status) throws IOException
	{
		updateLoginStatusInExcel(TestcaseID,status);
	}
	
	private void updateLoginStatusInExcel(int TestcaseID,String status) throws IOException
	{
		File file = new File("C:\\Ranj\\git\\selenium-workspace\\referencematerial\\SeleniumWebDriver\\src\\guru99\\Guru99_testdata.xlsx");
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Login");
		int testcaseID = TestcaseID;
		
		int colcount = sh.getRow(testcaseID).getPhysicalNumberOfCells();		
		System.out.println("Number of columns "+colcount);
		wb.close();
		
	}

}
	

