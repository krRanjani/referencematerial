package guru99.DataClasses;
//It has the declaration and initialization of the variables used in the class.

public class DepositInfo {
	
	public String acctnum;
	public String balBefore;
	public String amount;
	public String description;
	public String message;

	public String warn = "Account does not exist"; //For blank account no,characters,spl characters and blank spaces for account no field
	public String fail = "Amount could not be credited"; //For blank amount,characters,spl characters and blank spaces for amount field
	
	
}
