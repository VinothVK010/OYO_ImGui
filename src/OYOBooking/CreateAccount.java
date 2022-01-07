package OYOBooking;

public class CreateAccount 
{
	
	public String createAcc(String name, String password, String number)
	{
		String error = "";
		FileHandler fh = new FileHandler("accounts.csv");
		if(number.length() == 10)
		{
			fh.writeString(String.format("%s,%s,%s", name, password, number));
		}
		else
		{
			error = "Mobile number can't be less (or) greater than 10 numbers you FOOL !!!";
		}
		fh.closeFile();
		
		return error;
	}
	
}
