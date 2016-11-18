package destination;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.EstateException;
import exceptions.LocationException;
import exceptions.ReservationException;
import exceptions.RoomException;

public class Estate {

	private String address;
	private City city;
	// private User owner;
	private static Set<String> allHotelFacilites = new HashSet<String>();
	private Set<String> hotelFacilities = new HashSet<String>();
	private Map<String, ArrayList<Room>> rooms = new HashMap<String, ArrayList<Room>>();
	private int countRooms = 1;
	private List<File> images;
	private double raiting;
	private byte stars;

	public Estate(String address, City city, byte stars) throws EstateException, LocationException {
		if (address != null) {
			this.address = address;
		} else {
			throw new EstateException("Invalid address of the estate!");
		}
		if (city != null) {
			this.city = city;
		} else {
			throw new EstateException("Invalid city!");
		}
		if (stars > 0) {
			this.stars = stars;
		} else {
			throw new EstateException("Ivalid number of stars");
		}
		Country bg = new Country("Bulgaria"); // Shte zarejdame ot DB
		Application.locations.get(bg).put(city.getName(), city);
	}

	public void addRoom(String type, Room room) {

		if (type != null && room != null) {

			if (this.rooms.containsKey(type)) {
				rooms.get(type).add(room);
			} else {
				rooms.put(type, new ArrayList<Room>());
				rooms.get(type).add(room);
			}
			countRooms++;
		}

	}

	public ArrayList<Room> getAvailableRooms(int startYear, int startMonth, int startDay, int endYear, int endMonth,
			int endDay, int numAdults, int numChildren) throws RoomException, ReservationException {
		String type = Room.checkType(numAdults, numChildren);
		ArrayList<Room> available = new ArrayList<Room>();

		if (rooms.containsKey(type)) {
			available = new ArrayList<Room>(rooms.get(type));
			int availableSize = available.size();
			int countRemoves = 0;

			for (int index = 0; index < availableSize; index++) {
				if (!available.get(index - countRemoves).isFree(startYear, startMonth, startDay, endYear, endMonth,
						endDay)) {
					available.remove(index - countRemoves);
					countRemoves++;
				}
			}
		}
		return available;
	}

	public void printRooms() {
		System.out.println("Rooms in estate with address: " + city.getName() + ", " + address + "\n");
		rooms.forEach((type, listOfRooms) -> System.out.println(listOfRooms));
	}

	public int getNumOfRooms() {
		// TODO Auto-generated method stub
		return this.countRooms;
	}

	public String getAddress() {
		// TODO Auto-generated method stub
		return this.address;
	}

	@Override
	public String toString() {
		return "Estate [address=" + address + ", city=" + city + ", rooms=" + rooms + ", countRooms=" + countRooms
				+ ", stars=" + stars + "]";
	}

	public boolean reserveRoom(String roomType, LocalDate startDay, LocalDate endDay) throws ReservationException {

		ArrayList<Room> roomsByType = this.rooms.get(roomType);

		for (int count = 0; count < roomsByType.size(); count++) {
			if (roomsByType.get(count).addReservation(startDay, endDay))
				return true;
		}
		System.out.println("Nqma poveche svobodni stai ot tip: " + roomType + " v tozi hotel za perioda : " + startDay + " / " + endDay);
		return false;
	}

	public City getCity() {
		return city;
	}

}
