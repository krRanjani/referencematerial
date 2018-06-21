package guru99.Repository;

//This is for reading the inputs from excel and writing the test results into excel for all the functions
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import guru99.DataClasses.BalEnquiryInfo;
import guru99.DataClasses.BankStatementInfo;
import guru99.DataClasses.DeleteAcctInfo;
import guru99.DataClasses.DeleteCustInfo;
import guru99.DataClasses.DepositInfo;
import guru99.DataClasses.EditAcctInfo;
import guru99.DataClasses.EditCustInfo;
import guru99.DataClasses.FundTransferInfo;
import guru99.DataClasses.LoginLogoutInfo;
import guru99.DataClasses.NewAcctInfo;
import guru99.DataClasses.NewCustInfo;
import guru99.DataClasses.WithdrawalInfo;
import guru99.Interfaces.IGuruRepository;

public class ExcelRepository implements IGuruRepository {
	
	private String filepath = "F:\\Eclipse-workspace\\referencematerial\\SeleniumWebDriver\\src\\guru99\\DataFiles\\\\Guru99_testdata.xlsx";
  
	public LoginLogoutInfo readLoginInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readLoginInfoFromExcel( TestcaseID, Driver);
	}
	
	private LoginLogoutInfo readLoginInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("LoginLogout");
		
		int testcaseID = TestcaseID;
		LoginLogoutInfo oLoginLogoutInfo = new LoginLogoutInfo();
		
		oLoginLogoutInfo.URL = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oLoginLogoutInfo.user = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		oLoginLogoutInfo.password = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		oLoginLogoutInfo.message = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		wb.close();
		
        String result = "Test case number:%d having URL:%s with user:%s , password:%s and message:%s";
		System.out.println(String.format(result, testcaseID,oLoginLogoutInfo.URL,oLoginLogoutInfo.user,oLoginLogoutInfo.password,oLoginLogoutInfo.message));
		return oLoginLogoutInfo;
	}
	
	public void updateLoginLogoutStatus(int TestcaseID,String status) throws IOException
	{
		updateLoginLogoutStatusInExcel(TestcaseID,status);
	}
	
	private void updateLoginLogoutStatusInExcel(int TestcaseID,String status) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("LoginLogout");
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
	    
	
		int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();		
		if(colcount>=7)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			if(status.contains("Pass"))
				sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
			else
				sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
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
		
		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
		sh.getRow(testcaseID).getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);	
		sh.getRow(testcaseID).getCell(5).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(5,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(6,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
		sh.getRow(testcaseID).getCell(6).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(7).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(7,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
		sh.getRow(testcaseID).getCell(8).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(8,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
		sh.getRow(testcaseID).getCell(9).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(9,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
		sh.getRow(testcaseID).getCell(10).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(10,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
		sh.getRow(testcaseID).getCell(11).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(11,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
		sh.getRow(testcaseID).getCell(12).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(12,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		

		oNewCustInfo.custname = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oNewCustInfo.gender = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		Date dtDOB =sh.getRow(testcaseID).getCell(4).getDateCellValue();
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		if( dtDOB == null)
			oNewCustInfo.dob="";
		else
		oNewCustInfo.dob=  dt.format(dtDOB);
		oNewCustInfo.address = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		oNewCustInfo.city = sh.getRow(testcaseID).getCell(6).getStringCellValue() ;
		oNewCustInfo.state = sh.getRow(testcaseID).getCell(7).getStringCellValue();
		oNewCustInfo.pin = sh.getRow(testcaseID).getCell(8).getStringCellValue();
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
	
	@SuppressWarnings("deprecation")
	private void updateNewCustStatusInExcel(int TestcaseID,String status,String custid) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("NewCust");
		XSSFSheet sh1 = wb.getSheet("EditCust");
		XSSFSheet sh2 = wb.getSheet("DeleteCust");
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
	    
	
		int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		
		int colmcnt,colcnt;
		if(TestcaseID<=15)
			colmcnt = sh1.getRow(TestcaseID).getPhysicalNumberOfCells();
		else
			colmcnt=0;
		
		if(TestcaseID<=26)
			colcnt = sh2.getRow(TestcaseID).getPhysicalNumberOfCells();
		else
			colcnt=0;
		
		//System.out.println("Column count in New sheet: "+colcount+", Column count in Edit sheet: "+colmcnt+" and Delete sheet: "+colcnt);
		
		if(colcount>=15)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			sh.getRow(TestcaseID).getCell(colcount-2).setCellValue(custid);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
					sh.getRow(TestcaseID).getCell(colcount-2).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
					sh.getRow(TestcaseID).getCell(colcount-2).setCellStyle(styleF);
				}
			
			//Updating the EditCust sheet with newly created customer ids
			if(colmcnt>=11 && TestcaseID==1)
			{
				for(int i=1;i<=8;i++)
				sh1.getRow(i).getCell(colmcnt-9).setCellValue(custid);
				System.out.println("EditCust sheet updated");
			}
			else
				System.out.println("Nothing to update in EditCust sheet");
			
			
			//Updating the DeleteCust sheet with newly created customer ids
			if(colcnt>=5 && custid !=" ")
			{		//System.out.println("Customer id is "+custid);		
					for(int i=1;i<=15;i++)
					{
						sh2.getRow(i).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						boolean check = sh2.getRow(i).getCell(2).getStringCellValue().isEmpty();
						if(check)
							{
								//System.out.println("Value of i is "+ i);
								sh2.getRow(i).getCell(2).setCellValue(custid);
								System.out.println("DeleteCust sheet updated");
								break;
							}
					}
				}
			else if(TestcaseID>15 || custid == " ")
				System.out.println("Nothing to update in DeleteCust sheet");
			}
		
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();
	}
	
	public EditCustInfo readEditCustInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		 return readEditCustInfoFromExcel(TestcaseID,Driver);
	}
	
	@SuppressWarnings("deprecation")
	private EditCustInfo readEditCustInfoFromExcel(int TestcaseID,WebDriver driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("EditCust");
		
		
		int testcaseID = TestcaseID;
		EditCustInfo oEditCustInfo = new EditCustInfo();
		
		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(4).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(5,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(5).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(6,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(6).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(7,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(7).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(8,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(8).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(9,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(9).setCellType(Cell.CELL_TYPE_STRING);

		oEditCustInfo.custid = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oEditCustInfo.address = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		oEditCustInfo.city = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		oEditCustInfo.state = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		oEditCustInfo.pin = sh.getRow(testcaseID).getCell(6).getStringCellValue();
		oEditCustInfo.mobno = sh.getRow(testcaseID).getCell(7).getStringCellValue();
		oEditCustInfo.email = sh.getRow(testcaseID).getCell(8).getStringCellValue();
		oEditCustInfo.message = sh.getRow(testcaseID).getCell(9).getStringCellValue();
		wb.close();
		
        String result = "Test case number:%d having %s,%s,%s,%s,%s,%s,%s,%s";
		System.out.println(String.format(result, testcaseID,oEditCustInfo.custid,oEditCustInfo.address,oEditCustInfo.city,oEditCustInfo.state,oEditCustInfo.pin,oEditCustInfo.mobno,oEditCustInfo.email,oEditCustInfo.message));
		return oEditCustInfo;
	}

	public void updateEditCustStaus(int TestcaseID,String status) throws IOException
	{
		updateEditCustStatusInExcel(TestcaseID,status);
	}
	
	private void updateEditCustStatusInExcel(int TestcaseID,String status) throws IOException
	{
		File file  = new File(filepath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("EditCust");
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
	    
	
		int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		//System.out.println("Column count "+colcount);
		if(colcount>=11)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
				}
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();		
	}

	public DeleteCustInfo readDeleteCustInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readDeleteCustInfoFromExcel(TestcaseID, Driver);
	}
	
	@SuppressWarnings("deprecation")
	private DeleteCustInfo readDeleteCustInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("DeleteCust");
		
		
		int testcaseID = TestcaseID;
		DeleteCustInfo oDeleteCustInfo = new DeleteCustInfo();
		
		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
			
		oDeleteCustInfo.custid = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oDeleteCustInfo.message = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		wb.close();
		
        String result = "Test case number:%d having %s,%s";
		System.out.println(String.format(result, testcaseID,oDeleteCustInfo.custid,oDeleteCustInfo.message));
		return oDeleteCustInfo;
	}

	public void updateDeleteCustStatus(int TestcaseID,String status) throws IOException
	{
		updateDeleteCustStatusInExcel(TestcaseID,status);
	}
	
	private void updateDeleteCustStatusInExcel(int TestcaseID,String status) throws IOException

	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("DeleteCust");
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
	    
	
		int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		//System.out.println("Column count "+colcount);
		if(colcount>=5)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
				}
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();	
	}
	
	@SuppressWarnings("deprecation")
	public void deleteCustidsFromExcel() throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("DeleteCust");
		int counter = 0;
		
		for(int i=1;i<=15;i++)
		{
			sh.getRow(i).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
			if(!sh.getRow(i).getCell(2).getStringCellValue().isEmpty())
				{
				sh.getRow(i).getCell(2).setCellValue("");
				System.out.println(sh.getRow(i).getCell(2).getStringCellValue());
				counter++;
				}
			else
				System.out.println("Account number is blank, nothing to delete");
					
		}
		/*NEED TO DELETE
				for(int j=1;j<=15;j++)
			{
					if(sh.getRow(j).getCell(2).getStringCellValue().isEmpty())
					{
						counter++;
						//System.out.println("Counter at iteration: "+j+ " is "+counter);
					}
			}*/
		
				if(counter==15)
					System.out.println("All records are deleted");
				else
					System.out.println("All records are not deleted");
					
					FileOutputStream fos = new FileOutputStream(file);
					wb.write(fos);
					wb.close();
					
	}
	
	public NewAcctInfo readNewAcctInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readNewAcctInfoFromExcel(TestcaseID,Driver);
	}
	
	@SuppressWarnings("deprecation")
	private NewAcctInfo readNewAcctInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("NewAcct");
		
		int testcaseID = TestcaseID;
		NewAcctInfo oNewAcctInfo = new NewAcctInfo();
		
		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(4).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);	
		sh.getRow(testcaseID).getCell(5).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(5,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		
		oNewAcctInfo.custid = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oNewAcctInfo.acctType = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		oNewAcctInfo.initdepo = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		oNewAcctInfo.message = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		wb.close();
		
		String result = "Test case number:%d having %s,%s,%s,%s";
		System.out.println(String.format(result, testcaseID,oNewAcctInfo.custid,oNewAcctInfo.acctType,oNewAcctInfo.initdepo,oNewAcctInfo.message));
		
		return oNewAcctInfo;
		
	}

	public void updateNewAcctStatus(int TestcaseID,String status,String acctnum) throws IOException
	{
		updateNewAcctStatusInExcel(TestcaseID,status,acctnum);
	}
	
	@SuppressWarnings("deprecation")
	private void updateNewAcctStatusInExcel(int TestcaseID,String status,String acctnum) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("NewAcct");
		XSSFSheet sh1 = wb.getSheet("EditAcct");
		XSSFSheet sh2 = wb.getSheet("DeleteAcct");
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
	    
	
		int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		
		int colmcnt,colcnt;
		if(TestcaseID<=12)
			colmcnt = sh1.getRow(TestcaseID).getPhysicalNumberOfCells();
		else
			colmcnt=0;
			
		if(TestcaseID<=20)
			colcnt = sh2.getRow(TestcaseID).getPhysicalNumberOfCells();
		
		else
			colcnt=0;
		
		//System.out.println("Column count in New sheet: "+colcount, Column count in Edit sheet: "+colmcnt+" and Delete sheet: "+colcnt);

		
		if(colcount>=8)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			sh.getRow(TestcaseID).getCell(colcount-2).setCellValue(acctnum);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
					sh.getRow(TestcaseID).getCell(colcount-2).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
					sh.getRow(TestcaseID).getCell(colcount-2).setCellStyle(styleF);
				}
			
			
	//Updating the EditAcct sheet with newly created account ids
			  if(colmcnt>=6 && TestcaseID<=4)
			{
				for(int i=1;i<=4;i++)
				{
					sh1.getRow(i).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					boolean check = sh1.getRow(i).getCell(2).getStringCellValue().isEmpty();
					if(check)
						{
						sh1.getRow(i).getCell(colmcnt-4).setCellValue(acctnum);
						System.out.println("EditAcct sheet updated");
						break;
						}
				
				}
			}
			else if(TestcaseID>4)
				System.out.println("Nothing to update in EditCust sheet");
			
			/*//Updating the DeleteAcct sheet with newly created account ids
			if(colcnt>=5 && acctnum != " ")
			{				
				for(int i=1;i<=13;i++)
					{
						sh2.getRow(i).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
						boolean check = sh2.getRow(i).getCell(2).getStringCellValue().isEmpty();
						if(check)
							{
								
								//System.out.println("Value of i is "+ i);
								sh2.getRow(i).getCell(2).setCellValue(acctnum);
								System.out.println("DeleteAcct sheet updated");
								break;
							}
					}
			}
					else if(TestcaseID>13 || acctnum == " ")
						System.out.println("No need to update DeleteAcct sheet");*/
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();	
	}

	public EditAcctInfo readEditAcctInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		 return readEditAcctInfoFromExcel(TestcaseID,Driver);
	}
	
	@SuppressWarnings("deprecation")
	private EditAcctInfo readEditAcctInfoFromExcel(int TestcaseID,WebDriver driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("EditAcct");
		
		
		int testcaseID = TestcaseID;
		EditAcctInfo oEditAcctInfo = new EditAcctInfo();
		
		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(4).setCellType(Cell.CELL_TYPE_STRING);		
		sh.getRow(testcaseID).getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

		oEditAcctInfo.acctnum = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oEditAcctInfo.acctType = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		oEditAcctInfo.message = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		
		wb.close();
		
        String result = "Test case number:%d having %s,%s,%s";
		System.out.println(String.format(result, testcaseID,oEditAcctInfo.acctnum,oEditAcctInfo.acctType,oEditAcctInfo.message));
		return oEditAcctInfo;
	}
	
	public void updateEditAcctStaus(int TestcaseID,String status) throws IOException
	{
		updateEditAcctStatusInExcel(TestcaseID,status);
	}
	
	private void updateEditAcctStatusInExcel(int TestcaseID,String status) throws IOException
	{
		File file  = new File(filepath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("EditAcct");
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
	    
	
		int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		//System.out.println("Column count "+colcount);
		if(colcount>=6)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
				}
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();		
	}
	
	@SuppressWarnings("deprecation")
	public void deleteAcctnumsFromEditAcctExcel() throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("EditAcct");
		int counter = 0;
		for(int i=1;i<=4;i++)
		{
			sh.getRow(i).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
			if(!sh.getRow(i).getCell(2).getStringCellValue().isEmpty())
			
					{
						sh.getRow(i).getCell(2).setCellValue("");
						//System.out.println(sh.getRow(i).getCell(2).getStringCellValue());
						counter++;
					}
					else
						System.out.println("Account number is blank, nothing to delete");
		}
				if(counter==4)
					System.out.println("All records are deleted");
				else
					System.out.println("All records are not deleted");
					
					FileOutputStream fos = new FileOutputStream(file);
					wb.write(fos);
					wb.close();	
	}

	public DeleteAcctInfo readDeleteAcctInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readDeleteAcctInfoFromExcel(TestcaseID, Driver);
	}
	
	@SuppressWarnings("deprecation")
	private DeleteAcctInfo readDeleteAcctInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("DeleteAcct");
		
		
		int testcaseID = TestcaseID;
		DeleteAcctInfo oDeleteAcctInfo = new DeleteAcctInfo();
		
		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);		
			
		oDeleteAcctInfo.acctnum = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oDeleteAcctInfo.message = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		wb.close();
		
        String result = "Test case number:%d having %s,%s";
		System.out.println(String.format(result, testcaseID,oDeleteAcctInfo.acctnum,oDeleteAcctInfo.message));
		return oDeleteAcctInfo;
	}

	public void updateDeleteAcctStatus(int TestcaseID,String status) throws IOException
	{
		updateDeleteAcctStatusInExcel(TestcaseID,status);
	}
	
	private void updateDeleteAcctStatusInExcel(int TestcaseID,String status) throws IOException

	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("DeleteAcct");
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
	    
	
		int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		//System.out.println("Column count "+colcount);
		if(colcount>=5)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
				}
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();	
	}

	@SuppressWarnings("deprecation")
	public void deleteAcctnumsFromDeleteAcctExcel() throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("DeleteAcct");
		int counter = 0;
		for(int i=1;i<=13;i++)
		{
			sh.getRow(i).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
			if(!sh.getRow(i).getCell(2).getStringCellValue().isEmpty())
			
					{
					sh.getRow(i).getCell(2).setCellValue("");
					System.out.println(sh.getRow(i).getCell(2).getStringCellValue());
					counter++;
					}
					else
						System.out.println("Account number is blank, nothing to delete");
		}
				/*NEED TO DELETE
				 * for(int j=1;j<=13;j++)
			{
					if(sh.getRow(j).getCell(2).getStringCellValue().isEmpty())
					{
						counter++;
						//System.out.println("Counter at iteration: "+j+ " is "+counter);
					}
			}*/
				if(counter==13)
					System.out.println("All records are deleted");
				else
					System.out.println("All records are not deleted");
					
					FileOutputStream fos = new FileOutputStream(file);
					wb.write(fos);
					wb.close();				
	}
	
	public DepositInfo readDepositInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readDepositInfoFromExcel(TestcaseID,Driver);
	}
	
	@SuppressWarnings("deprecation")
	private DepositInfo readDepositInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Deposit");
		
		
		int testcaseID = TestcaseID;
		DepositInfo oDepositInfo = new DepositInfo();

		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(4).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);	
		sh.getRow(testcaseID).getCell(5).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(5,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);	
		sh.getRow(testcaseID).getCell(6).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(6,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		
		oDepositInfo.acctnum = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oDepositInfo.balBefore = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		oDepositInfo.amount = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		oDepositInfo.description = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		oDepositInfo.message = sh.getRow(testcaseID).getCell(6).getStringCellValue();

		wb.close();
		
        String result = "Test case number:%d having %s,%s,%s,%s,%s";
		System.out.println(String.format(result, testcaseID,oDepositInfo.acctnum,oDepositInfo.balBefore,oDepositInfo.amount,oDepositInfo.description,oDepositInfo.message));
		return oDepositInfo;
	}
	
	public void updateDepositStatus(int TestcaseID,String status,String currentbal) throws IOException
	{
		updateDepositStatusInExcel(TestcaseID,status,currentbal);
	}
	
	private void updateDepositStatusInExcel(int TestcaseID,String status,String currentbal) throws IOException

	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Deposit");
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
	    
	
	    int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		System.out.println("Column count "+colcount);
		if(colcount>=9)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			if(Integer.parseInt(currentbal)>=0)
			{	
				sh.getRow(TestcaseID).getCell(colcount-2).setCellValue(currentbal);
				sh.getRow(TestcaseID).getCell(colcount-6).setCellValue(currentbal);
			}

			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
				}
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();	
	}

	public WithdrawalInfo readWithdrawalInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readWithdrawalInfoFromExcel(TestcaseID,Driver);
	}
	
	@SuppressWarnings("deprecation")
	private WithdrawalInfo readWithdrawalInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Withdrawal");
		
		
		int testcaseID = TestcaseID;
		WithdrawalInfo oWithdrawalInfo = new WithdrawalInfo();
		
		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(4).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);	
		sh.getRow(testcaseID).getCell(5).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(5,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(6).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(6,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			
		oWithdrawalInfo.acctnum = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oWithdrawalInfo.balBefore = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		oWithdrawalInfo.amount = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		oWithdrawalInfo.description = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		oWithdrawalInfo.message = sh.getRow(testcaseID).getCell(6).getStringCellValue();

		wb.close();
		
        String result = "Test case number:%d having %s,%s,%s,%s,%s";
		System.out.println(String.format(result, testcaseID,oWithdrawalInfo.acctnum,oWithdrawalInfo.balBefore,oWithdrawalInfo.amount,oWithdrawalInfo.description,oWithdrawalInfo.message));
		return oWithdrawalInfo;
	}
	
	public void updateWithdrawalStatus(int TestcaseID,String status,String currentbal) throws IOException
	{
		updateWithdrawalStatusInExcel(TestcaseID,status,currentbal);
	}
	
	private void updateWithdrawalStatusInExcel(int TestcaseID,String status,String currentbal) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Withdrawal");
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
	    
	
	    int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		//System.out.println("Column count "+colcount);
		if(colcount>=9)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
				if(Integer.parseInt(currentbal)>=0)
			{
				sh.getRow(TestcaseID).getCell(colcount-2).setCellValue(currentbal);
				sh.getRow(TestcaseID).getCell(colcount-6).setCellValue(currentbal);
			}
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
				}
		}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();	
	}

	public FundTransferInfo readFundTransferInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readFundTransferInfoFromExcel(TestcaseID,Driver);
	}
	
	@SuppressWarnings("deprecation")
	private FundTransferInfo readFundTransferInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("FundTransfer");
		
		
		int testcaseID = TestcaseID;
		FundTransferInfo oFundTransferInfo = new FundTransferInfo();
		
		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(4).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);	
		sh.getRow(testcaseID).getCell(5).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(5,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);	
		sh.getRow(testcaseID).getCell(6).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(6,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);	
			
		oFundTransferInfo.payersacctnum = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oFundTransferInfo.payeesacctnum = sh.getRow(testcaseID).getCell(3).getStringCellValue();
		oFundTransferInfo.amount = sh.getRow(testcaseID).getCell(4).getStringCellValue();
		oFundTransferInfo.description = sh.getRow(testcaseID).getCell(5).getStringCellValue();
		oFundTransferInfo.message = sh.getRow(testcaseID).getCell(6).getStringCellValue();

		wb.close();
		
        String result = "Test case number:%d having %s,%s,%s,%s,%s";
		System.out.println(String.format(result, testcaseID,oFundTransferInfo.payersacctnum,oFundTransferInfo.payeesacctnum,oFundTransferInfo.amount,oFundTransferInfo.description,oFundTransferInfo.message));
		return oFundTransferInfo;
	}
	
	public void updateFundTransferStatus(int TestcaseID,String status) throws IOException
	{
		updateFundTransferStatusInExcel(TestcaseID,status);
	}
	
	private void updateFundTransferStatusInExcel(int TestcaseID,String status) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("FundTransfer");
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
	    
	
	    int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		System.out.println("Column count "+colcount);
		/*if(colcount>=5)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
				}
		}*/
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();	
	}

	public BalEnquiryInfo readBalEnquiryInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readBalEnquiryInfoFromExcel(TestcaseID,Driver);
	}
	
	@SuppressWarnings("deprecation")
	private BalEnquiryInfo readBalEnquiryInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("BalEnquiry");
		
		int testcaseID = TestcaseID;
		BalEnquiryInfo oBalEnquiryInfo = new BalEnquiryInfo();
		
		sh.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	
		oBalEnquiryInfo.acctnum = sh.getRow(testcaseID).getCell(2).getStringCellValue();
		oBalEnquiryInfo.message = sh.getRow(testcaseID).getCell(3).getStringCellValue();

		wb.close();
		
        String result = "Test case number:%d having %s and %s";
		System.out.println(String.format(result, testcaseID,oBalEnquiryInfo.acctnum,oBalEnquiryInfo.message));
		return oBalEnquiryInfo;
	}
	
	public void updateBalEnquiryStatus(int TestcaseID,String status,String balance) throws IOException
	{
		updateBalEnquiryStatusInExcel(TestcaseID,status,balance);
	}
	
	private void updateBalEnquiryStatusInExcel(int TestcaseID,String status,String balance) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("BalEnquiry");
		XSSFSheet sh1 = wb.getSheet("Deposit");
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
	    
	
	    int colcount = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
	    int colcnt = sh.getRow(TestcaseID).getPhysicalNumberOfCells();
		//System.out.println("Column count "+colcount);
		if(colcount>=6)
		{
			sh.getRow(TestcaseID).getCell(colcount-1).setCellValue(status);
			sh.getRow(TestcaseID).getCell(colcount-2).setCellValue(balance);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount-1).setCellStyle(styleF);
				}
		}
		
		if(colcnt>=9 && balance!= null)
		{				
				for(int i=1;i<1000;i++)
				{
					boolean check = sh1.getRow(i).getCell(3).getStringCellValue().isEmpty();
					if(check)
						{
							System.out.println("Value of i is "+ i);
							sh1.getRow(i).getCell(3).setCellValue(balance);
							break;
						}
				}
				System.out.println("Deposit sheet updated");
			}
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();	
	}

	public BankStatementInfo readBankStatementInfo(int TestcaseID,WebDriver Driver) throws IOException
	{
		return readBankStatementInfoFromExcel(TestcaseID,Driver);
	}
	
	@SuppressWarnings("deprecation")
	private BankStatementInfo readBankStatementInfoFromExcel(int TestcaseID,WebDriver Driver) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh1 = wb.getSheet("MiniStat");
		XSSFSheet sh2 = wb.getSheet("CustStat");

		int testcaseID = TestcaseID;
		BankStatementInfo oBankStatementInfo = new BankStatementInfo();
		
		sh1.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh1.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh1.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh1.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		
		sh2.getRow(testcaseID).getCell(2).setCellType(Cell.CELL_TYPE_STRING);
		sh2.getRow(testcaseID).getCell(2,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh2.getRow(testcaseID).getCell(3).setCellType(Cell.CELL_TYPE_STRING);
		sh2.getRow(testcaseID).getCell(3,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh2.getRow(testcaseID).getCell(4).setCellType(Cell.CELL_TYPE_STRING);
		sh2.getRow(testcaseID).getCell(4,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh2.getRow(testcaseID).getCell(5).setCellType(Cell.CELL_TYPE_STRING);
		sh2.getRow(testcaseID).getCell(5,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh2.getRow(testcaseID).getCell(6).setCellType(Cell.CELL_TYPE_STRING);
		sh2.getRow(testcaseID).getCell(6,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		sh2.getRow(testcaseID).getCell(7).setCellType(Cell.CELL_TYPE_STRING);
		sh2.getRow(testcaseID).getCell(7,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
	
		oBankStatementInfo.Macctnum = sh1.getRow(testcaseID).getCell(2).getStringCellValue();
		oBankStatementInfo.Mmessage = sh1.getRow(testcaseID).getCell(3).getStringCellValue();

		oBankStatementInfo.Cacctnum = sh2.getRow(testcaseID).getCell(2).getStringCellValue();
		oBankStatementInfo.fromdate = sh2.getRow(testcaseID).getCell(3).getStringCellValue();
		oBankStatementInfo.todate = sh2.getRow(testcaseID).getCell(4).getStringCellValue();
		oBankStatementInfo.mintransvalue = sh2.getRow(testcaseID).getCell(5).getStringCellValue();
		oBankStatementInfo.numoftrans = sh2.getRow(testcaseID).getCell(6).getStringCellValue();
		oBankStatementInfo.Cmessage = sh2.getRow(testcaseID).getCell(7).getStringCellValue();

		wb.close();
		
        String result1 = "Test case number:%d having %s,%s";
        String result2 = "Test case number:%d having %s,%s,%s,%s,%s,%s";

		System.out.println(String.format(result1, testcaseID,oBankStatementInfo.Macctnum,oBankStatementInfo.Mmessage));
		System.out.println(String.format(result2, testcaseID,oBankStatementInfo.Cacctnum,oBankStatementInfo.fromdate,oBankStatementInfo.todate,oBankStatementInfo.mintransvalue,oBankStatementInfo.numoftrans,oBankStatementInfo.Cmessage));

		return oBankStatementInfo;
	}

	public void updateBankStatementStatus(int TestcaseID,String status) throws IOException
	{
		updateBankStatementStatusInExcel(TestcaseID,status);
	}

	private void updateBankStatementStatusInExcel(int TestcaseID,String status) throws IOException
	{
		File file = new File(filepath);
		FileInputStream fis  = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh1 = wb.getSheet("MiniStat");
		XSSFSheet sh2 = wb.getSheet("CustStat");
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
	    
	
	    int colcount1 = sh1.getRow(TestcaseID).getPhysicalNumberOfCells();
	    int colcount2 = sh2.getRow(TestcaseID).getPhysicalNumberOfCells();

		System.out.println("Column counts "+colcount1+", "+colcount2);
		/*if(colcount1>=5)
		{
			sh.getRow(TestcaseID).getCell(colcount1-1).setCellValue(status);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount1-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount1-1).setCellStyle(styleF);
				}
		}
		if(colcount2>=5)
		{
			sh.getRow(TestcaseID).getCell(colcount2-1).setCellValue(status);
			if(status.contains("Pass"))
				{
					sh.getRow(TestcaseID).getCell(colcount2-1).setCellStyle(styleP);
				}
			else
				{
					sh.getRow(TestcaseID).getCell(colcount2-1).setCellStyle(styleF);
				}
		}
		*/
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();	
	}


}
	

