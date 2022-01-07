package OYOBooking;

import java.util.ArrayList;
import java.util.HashMap;

import imgui.ImGui;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.type.ImInt;
import imgui.type.ImString;

public class Main extends Application 
{
	private ImString userName;
	private ImString password;
	private ImString phoneNum;
	private ImInt rooms;
	
	private HashMap<String,ArrayList<HotelObjects>> locations;
	private ArrayList<HotelObjects> hotels;
	
	private String error = "";
	private String info = "";
	private String success = "";
	private String currentHotelName = "";
	private HotelObjects currentHotelObject = null;
	
	private boolean createFlag = false;
	private boolean detailFlag = false;
	private boolean hotelFlag = false;
	private boolean flag = true;
	
	private GeneralManager gm;
	
	public Main()
	{
		userName = new ImString();
		password = new ImString();
		phoneNum = new ImString();
		rooms = new ImInt(1);
		gm = new GeneralManager();
		
	}

	protected void configure(final Configuration config) 
    {
    	config.setTitle(" OYO ROOMS ");
    	config.setFullScreen(false);
    }

	public static void main(String[] args) 
	{
		Main main = new Main();
		launch(main);
	}
	
	@Override
	public void process() 
	{
		if(!createFlag && flag)
		{
			login();
		}
		
		if(createFlag)
		{
			createAccount();
		}
		
		if(gm.isLoginFlag())
		{
			hotelLocations();
			if(detailFlag)
				hotels();
		}
		
	}
	
	public void login()
	{
		ImGui.begin("Login");
		ImGui.inputText("Username", userName);
		ImGui.inputText("Password", password);
		if(ImGui.button("Login"))
		{
			error = gm.login(userName.get(), password.get());
			flag =  false;
		}
		
		if(ImGui.button("CreateAccount"))
		{
			createFlag = true;
		}
		
		ImGui.text(error);
		ImGui.end();
	}

	public void createAccount()
	{
		ImGui.begin("CreateAccount");
		ImGui.inputText("Username", userName);
		ImGui.inputText("Password", password);
		ImGui.inputText("phoneNumber", phoneNum);
		
		if(ImGui.button("CreateAccount"))
		{
			error = gm.createAccount(userName.get(), password.get(), phoneNum.get());
		}
		
		ImGui.text(error);
		
		if(ImGui.button("back"))
		{
			createFlag = false;
		}
		
		ImGui.end();
	}
	
	public void hotelLocations()
	{
		ImGui.begin("Locations");
		ImGui.beginChild("locations");
		locations = gm.getLocations();
		
		for(String s : locations.keySet())
		{
			
			if(ImGui.menuItem(s))
			{
				hotels = locations.get(s);
				detailFlag = true;
			}	
		}
		
		if(ImGui.button("back"))
		{
			gm.setLoginFlag(false);
			flag = true;
		}
		
		ImGui.endChild();
		ImGui.end();
	}
	
	public void hotels()
	{
		ImGui.begin("Hotels");
		ImGui.beginChild("hotels");
		
		for(HotelObjects ho : hotels)
		{
			if(ImGui.menuItem(ho.getHotelName()))
			{
				info = ho.getInfo();
				currentHotelName = ho.getHotelName();
				currentHotelObject = ho;
				hotelFlag = true;
			}
		}
		
		if(hotelFlag)
			clickedHotelDetail();
		ImGui.endChild();
		ImGui.end();
	}
	
	public void clickedHotelDetail()
	{
		ImGui.begin(currentHotelName);
		ImGui.text(info);
		ImGui.inputInt("How many Roooms", rooms);
		
		if(ImGui.button("Book Now !")) 
		{
			if(rooms.get() > 0)
			{
				roomsBookings(rooms.get());
			}
			else
			{
				success = "I think you never gone to school";
			}		
		}
		ImGui.text(success);
		
		if(ImGui.button("back"))
			hotelFlag = false;
		
		ImGui.end();
	}
	
	public void roomsBookings(int rooms)
	{
		if(currentHotelObject != null)
		{
			int availableRooms = Integer.parseInt(currentHotelObject.getRoomsAvailable());
			int roomCosts = Integer.parseInt(currentHotelObject.getRoomCost());
			
			if(rooms <= availableRooms)
			{
				int bill = rooms*roomCosts;
				success = "Your rooms booked Successfully and your room cost is: $" + bill ;
			}
			else
			{
				success = "We only have " + availableRooms + " Rooms check other hotels in our App";
			}
		}
	}
}
