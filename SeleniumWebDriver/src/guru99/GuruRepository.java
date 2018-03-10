package guru99;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public interface GuruRepository {

	public LoginController readLoginInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateLoginStatus(int TestcaseID,String status);

}
