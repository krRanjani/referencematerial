package guru99.Interfaces;

import java.io.IOException;
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

public interface IGuruRepository {

	public LoginLogoutInfo readLoginInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateLoginLogoutStatus(int TestcaseID,String status) throws IOException;
	
	public NewCustInfo readNewCustInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateNewCustStatus(int TestcaseID,String status,String custid) throws IOException;	
	
	public EditCustInfo readEditCustInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateEditCustStaus(int TestcaseID,String status) throws IOException;
	
	public DeleteCustInfo readDeleteCustInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateDeleteCustStatus(int TestcaseID,String status) throws IOException;
	public void deleteCustidsFromExcel() throws IOException;
	
	public NewAcctInfo readNewAcctInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateNewAcctStatus(int TestcaseID,String status,String acctnum) throws IOException;
	
	public EditAcctInfo readEditAcctInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateEditAcctStaus(int TestcaseID,String status) throws IOException;
	
	public DeleteAcctInfo readDeleteAcctInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateDeleteAcctStatus(int TestcaseID,String status) throws IOException;
	public void deleteAcctnumsFromExcel() throws IOException;
	
	public DepositInfo readDepositInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateDepositStatus(int TestcaseID,String status) throws IOException;

	public WithdrawalInfo readWithdrawalInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateWithdrawalStatus(int TestcaseID,String status) throws IOException;
	
	public FundTransferInfo readFundTransferInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateFundTransferStatus(int TestcaseID,String status) throws IOException;
	
	public BalEnquiryInfo readBalEnquiryInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateBalEnquiryStatus(int TestcaseID,String status) throws IOException;

	public BankStatementInfo readBankStatementInfo(int TestcaseID,WebDriver Driver) throws IOException;
	public void updateBankStatementStatus(int TestcaseID,String status) throws IOException;

}
