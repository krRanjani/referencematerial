package guru99.Interfaces;

import java.io.IOException;
import org.openqa.selenium.WebDriver;

import guru99.DataClasses.DeleteAcctInfo;
import guru99.DataClasses.DeleteCustInfo;
import guru99.DataClasses.EditAcctInfo;
import guru99.DataClasses.EditCustInfo;
import guru99.DataClasses.LoginInfo;
import guru99.DataClasses.NewAcctInfo;
import guru99.DataClasses.NewCustInfo;

public interface IGuruRepository {

	public LoginInfo readLoginInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateLoginStatus(int TestcaseID,String status) throws IOException;
	
	public NewCustInfo readNewCustInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateNewCustStatus(int TestcaseID,String status,String custid) throws IOException;	
	
	public EditCustInfo readEditCustInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateEditCustStaus(int TestcaseID,String status) throws IOException;
	
	public DeleteCustInfo readDeleteCustInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateDeleteCustStatus(int TestcaseID,String status) throws IOException;
	public void deleteCustidsFromExcel() throws IOException;
	
	public NewAcctInfo readNewAcctInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateNewAcctStatus(int TestcaseID,String status,String acctnum) throws IOException;
	
	public DeleteAcctInfo readDeleteAcctInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateDeleteAcctStatus(int TestcaseID,String status) throws IOException;
	public void deleteAcctnumsFromExcel() throws IOException;
	
	public EditAcctInfo readEditAcctInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateEditAcctStaus(int TestcaseID,String status) throws IOException;


}
