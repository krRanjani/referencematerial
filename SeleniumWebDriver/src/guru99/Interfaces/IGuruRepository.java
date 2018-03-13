package guru99.Interfaces;

import java.io.IOException;
import org.openqa.selenium.WebDriver;

import guru99.DataClasses.LoginInfo;
import guru99.DataClasses.NewCustInfo;

public interface IGuruRepository {

	public LoginInfo readLoginInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateLoginStatus(int TestcaseID,String status) throws IOException;
	public NewCustInfo readNewCustInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateNewCustStatus(int TestcaseID,String status,String custid) throws IOException;	
}
