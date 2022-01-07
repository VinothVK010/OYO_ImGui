package OYOBooking;

import java.util.ArrayList;

public class HotelObjects 
{
	private String hotelName;
	private String location;
	private String roomsAvailable;
	private String ratings;
	private String roomCost;
	
	public HotelObjects(ArrayList<String> hoteldetails)
	{
		setHotelName(hoteldetails.get(0));
		setLocation(hoteldetails.get(1));
		setRoomsAvailable(hoteldetails.get(2));
		setRatings(hoteldetails.get(3));
		setRoomCost(hoteldetails.get(4));
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRoomsAvailable() {
		return roomsAvailable;
	}

	public void setRoomsAvailable(String roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRatings() {
		return ratings;
	}

	public void setRatings(String ratings) {
		this.ratings = ratings;
	}

	public String getRoomCost() {
		return roomCost;
	}

	public void setRoomCost(String roomCost) {
		this.roomCost = roomCost;
	}
	
	public String toString()
	{
		return this.hotelName;
	}
	
	public String getInfo()
	{
		String deatils = String.format("\nRoom Available: %s, \nRatings: %s, "
				+ "\nRoom Cost: $%s per room",this.roomsAvailable,this.ratings,this.roomCost);
		return deatils;
	}
}
