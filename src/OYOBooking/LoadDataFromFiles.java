package OYOBooking;

import java.util.ArrayList;
import java.util.HashMap;

public class LoadDataFromFiles 
{
	public HashMap<String,ArrayList<String>> loadAccountdata()
	{
		FileHandler fh = new FileHandler("accounts.csv");
		return fh.splitStringFromFile();
	}
	
	public HashMap<String,ArrayList<String>> loadHotelData()
	{
		FileHandler fh = new FileHandler("hoteldata.csv");
		return fh.splitStringFromFile();
	}
	
}
