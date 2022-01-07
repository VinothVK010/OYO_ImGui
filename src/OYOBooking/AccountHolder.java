package OYOBooking;

import java.util.ArrayList;

public class AccountHolder 
{
	private String name;
	private String password;
	private String phonenumber;
	
	public AccountHolder(ArrayList<String> data)
	{
		setName(data.get(0));
		setPassword(data.get(1));
		setPhonenumber(data.get(2));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
		
}
