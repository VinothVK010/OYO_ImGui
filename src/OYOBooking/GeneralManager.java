package OYOBooking;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneralManager 
{
	private CreateAccount ca;
	private LoadDataFromFiles la;
	private HashMap<String,ArrayList<String>> accountData;
	private HashMap<String,ArrayList<String>> hotelData;
	private HashMap<String,AccountHolder> accountDetails; //username to userdata like password
	private HashMap<String,HotelObjects> hotelDetails;// Hotelname to hotel details
	private HashMap<String,ArrayList<HotelObjects>> locations; // locations to hotelobjects
	private boolean loginFlag = false;
	
	public GeneralManager()
	{
		ca = new CreateAccount();
		la = new LoadDataFromFiles();
		this.loadHotelDetails(); 
		this.loadHotelLocations();
	}

	public String createAccount(String name , String password, String number)
	{
		return ca.createAcc(name, password, number);
	}
	
	public String login(String name, String password)
	{
		String success = "";
		loadAccountDetails();
		
		if(accountDetails.containsKey(name))
		{
			AccountHolder ac = accountDetails.get(name);
			
			if(password.equals(ac.getPassword()))
			{
				success = "Login Successful";
				loginFlag = true;
				return success;
			}
			else
			{
				success = "Username (Or) Password is incorrect";
				loginFlag = false;
				return success;
			}
		}
		else
		{
			success = "Username (Or) Password is incorrect";
		}
		return success;
	}
	
	private void loadHotelDetails()
	{
		hotelDetails = null;
		hotelData = la.loadHotelData();
		hotelDetails = new HashMap<>();
		
		for(String s : hotelData.keySet())
		{
			HotelObjects hd = new HotelObjects(hotelData.get(s));
			hotelDetails.put(s, hd);
			hd = null;
		}		
	}
	
	private void loadHotelLocations()
	{
		locations = null;
		locations = new HashMap<>();//locations mapped to Arraylist of hotels details
		
		for(String s : hotelDetails.keySet())
		{
			HotelObjects hd = hotelDetails.get(s);
			String location = hd.getLocation();
			
			if(locations.containsKey(location))
			{
				ArrayList<HotelObjects> hotelsInCurrentLocation	= locations.get(location);
				hotelsInCurrentLocation.add(hd);
				//locations.put(location, hotelsInCurrentLocation);
			}
			else
			{
				ArrayList<HotelObjects> hotelsInCurrentLocation =  new ArrayList<>();
				hotelsInCurrentLocation.add(hd);
				locations.put(location, hotelsInCurrentLocation);
			}
		}
	}
	
	public void loadAccountDetails()
	{
		accountDetails = null;
		accountData = la.loadAccountdata();
		accountDetails = new HashMap<>();
		for(String s : accountData.keySet())
		{
			AccountHolder ah = new AccountHolder(accountData.get(s));
			accountDetails.put(s, ah);
		}
	}
	
	public HashMap<String,ArrayList<HotelObjects>> getLocations()
	{
		return locations;
	}
	
	public HashMap<String,HotelObjects> getHotelDetails()
	{
		return hotelDetails;
	}

	public boolean isLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}
}
