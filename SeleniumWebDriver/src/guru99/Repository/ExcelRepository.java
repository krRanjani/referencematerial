package guru99.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import guru99.DataClasses.LoginInfo;
import guru99.DataClasses.NewCustInfo;
import guru99.Interfaces.IGuruRepository;

public class ExcelRepository implements IGuruRepository {
	
	private String filepath = "C:\\Ranj\\git\\selenium-workspace\\referencematerial\\SeleniumWebDriver\\src\\guru99\\DataFiles\\Guru99_testdata.xlsx";
  
	public LoginInfo readLoginInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readLoginInfoFromExcel( TestcaseID, Driver);
	}
	
	private LoginInfo readLoginInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Login");
		
		int testcaseID = TestcaseID;
		LoginInfo oLoginInfo = new LoginInfo();
		
		oLoginInfo.URL = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oLoginInfo.user = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		oLoginInfo.password = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		oLoginInfo.message = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		wb.close();
		
        String result = "Test case number:%d having URL:%s with user:%s , password:%s and message:%s";
		System.out.println(String.format(result, testcaseID,oLoginInfo.URL,oLoginInfo.user,oLoginInfo.password,oLoginInfo.message));
		return oLoginInfo;
	}
	
	public void updateLoginStatus(int TestcaseID,String status) throws IOException
	{
		updateLoginStatusInExcel(TestcaseID,status);
	}
	
	private void updateLoginStatusInExcel(int testcaseID,String status) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Login");
		CellStyle styleP = wb.createCellStyle();
		
	    styleP.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
	    styleP.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    styleP.setBorderBottom(BorderStyle.THIN);
	    styleP.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styleP.setBorderLeft(BorderStyle.THIN);
	    styleP.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    styleP.setBorderRight(BorderStyle.THIN);
	    styleP.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    styleP.setBorderTop(BorderStyle.THIN);
	    styleP.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    
	    CellStyle styleF = wb.createCellStyle();
	    styleF.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
	    styleF.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    styleF.setBorderBottom(BorderStyle.THIN);
	    styleF.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styleF.setBorderLeft(BorderStyle.THIN);
	    styleF.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    styleF.setBorderRight(BorderStyle.THIN);
	    styleF.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    styleF.setBorderTop(BorderStyle.THIN);
	    styleF.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    
	
		int colcount = sh.getRow(testcaseID).getPhysicalNumberOfCells();		
		if(colcount>=7)
		{
			sh.getRow(testcaseID).getCell(colcount-1).setCellValue(status);
			if(status.contains("Pass"))
				sh.getRow(testcaseID).getCell(colcount-1).setCellStyle(styleP);
			else
				sh.getRow(testcaseID).getCell(colcount-1).setCellStyle(styleF);
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();	
	}

	public NewCustInfo readNewCustInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readNewCustInfoFromExcel(TestcaseID, Driver);
	}
	
	@SuppressWarnings("deprecation")
	private NewCustInfo readNewCustInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("NewCust");
		
		
		int testcaseID = TestcaseID;
		NewCustInfo oNewCustInfo = new NewCustInfo();
		
		oNewCustInfo.custname = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oNewCustInfo.gender = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		//sh.getRow(testcaseID).getCell(4).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
		Date dtDOB =sh.getRow(testcaseID).getCell(4).getDateCellValue();
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		oNewCustInfo.dob=  dt.format(dtDOB);
		oNewCustInfo.address = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		oNewCustInfo.city = sh.getRow(testcaseID).getCell(6).getStringCellValue() ;
		oNewCustInfo.state = sh.getRow(testcaseID).getCell(7).getStringCellValue();
		sh.getRow(testcaseID).getCell(8).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(8,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		oNewCustInfo.pin = sh.getRow(testcaseID).getCell(8).getStringCellValue();
		sh.getRow(testcaseID).getCell(9).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(9,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		oNewCustInfo.mobno = sh.getRow(testcaseID).getCell(9).getStringCellValue();
		oNewCustInfo.email = sh.getRow(testcaseID).getCell(10).getStringCellValue();
		oNewCustInfo.password = sh.getRow(testcaseID).getCell(11).getStringCellValue();
		oNewCustInfo.message = sh.getRow(testcaseID).getCell(12).getStringCellValue();
		wb.close();
		
        String result = "Test case number:%d having %s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s";
		System.out.println(String.format(result, testcaseID,oNewCustInfo.custname,oNewCustInfo.gender,oNewCustInfo.dob,oNewCustInfo.address,oNewCustInfo.city,oNewCustInfo.state,oNewCustInfo.pin,oNewCustInfo.mobno,oNewCustInfo.email,oNewCustInfo.password,oNewCustInfo.message));
		return oNewCustInfo;
	}

	public void updateNewCustStatus(int TestcaseID,String status,String custid) throws IOException
	{
		updateNewCustStatusInExcel(TestcaseID,status,custid);
	}
	
	private void updateNewCustStatusInExcel(int testcaseID,String status,String custid) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("NewCust");
		//XSSFSheet sh1 = wb.getSheet("EditCust");
		CellStyle styleP = wb.createCellStyle();
		
	    styleP.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
	    styleP.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    styleP.setBorderBottom(BorderStyle.THIN);
	    styleP.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styleP.setBorderLeft(BorderStyle.THIN);
	    styleP.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    styleP.setBorderRight(BorderStyle.THIN);
	    styleP.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    styleP.setBorderTop(BorderStyle.THIN);
	    styleP.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    
	    CellStyle styleF = wb.createCellStyle();
	    styleF.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
	    styleF.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    styleF.setBorderBottom(BorderStyle.THIN);
	    styleF.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	    styleF.setBorderLeft(BorderStyle.THIN);
	    styleF.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	    styleF.setBorderRight(BorderStyle.THIN);
	    styleF.setRightBorderColor(IndexedColors.BLACK.getIndex());
	    styleF.setBorderTop(BorderStyle.THIN);
	    styleF.setTopBorderColor(IndexedColors.BLACK.getIndex());
	    
	
		int colcount = sh.getRow(testcaseID).getPhysicalNumberOfCells();
		//System.out.println("Column count "+colcount);
		if(colcount>=15)
		{
			sh.getRow(testcaseID).getCell(colcount-1).setCellValue(status);
			sh.getRow(testcaseID).getCell(colcount-2).setCellValue(custid);
			if(status.contains("Pass"))
				{
					sh.getRow(testcaseID).getCell(colcount-1).setCellStyle(styleP);
					sh.getRow(testcaseID).getCell(colcount-2).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(testcaseID).getCell(colcount-1).setCellStyle(styleF);
					sh.getRow(testcaseID).getCell(colcount-2).setCellStyle(styleF);
				}
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();
	}

}
	

