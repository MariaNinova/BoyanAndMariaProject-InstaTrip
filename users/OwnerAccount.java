package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import destination.Application;
import destination.City;
import destination.Country;
import destination.Estate;
import destination.Room;
import exceptions.AccountException;
import exceptions.CityException;
import exceptions.EstateException;
import exceptions.LocationException;
import exceptions.RoomException;

public class OwnerAccount extends Account implements IOwnerAccount {

	private Map<String, Estate> estates = new HashMap<String, Estate>();

	public OwnerAccount(String username, String password, String email, String firstName, String lastName)
			throws AccountException {
		super(username, password, email, firstName, lastName);
	}

	@Override
	public void addEstate(String address, Country country, City city, byte stars) throws EstateException, CityException, LocationException {

		Estate estate = new Estate(address, city, (byte) stars);

		// check if dest contains in city TO-DO
		if (estate != null && country != null) {
			Application.locations.get(country).get(city.getName()).addEstate(estate);
			estates.put(address, estate);
		}else{
			System.out.println("Trqbva da napravq malko validacii tuk!");
		}

	}
	
	public void addEstate(Estate estate, Country country) throws CityException {
		if (estate != null && country != null) {
			Application.locations.get(country).get(estate.getCity().getName()).addEstate(estate);
			estates.put(estate.getAddress(), estate);
		}else{
			System.out.println("Trqbva da napravq malko validacii tuk!");
		}
		
	}

	@Override
	public void createRooms(Estate estate, int adults, int children, int numRooms, int priceForNight)
			throws RoomException {
		List<Room> rooms= new ArrayList<Room>();
		String type;

		
		
			for (int number = 0; number < numRooms; number++) {
				Room room = new Room(priceForNight, (byte) adults ,(byte)children, estate.getNumOfRooms());
				
				estate.addRoom(room.getType(), room);
			}

		}
		
	
	public Estate getEstate(String address) throws EstateException{
		if(estates.containsKey(address))
				return estates.get(address);
		throw new EstateException(super.getFirstName() + " " + super.getLastName() + " have not estate with address: " + address);
	}

	public void printEstates() {
		
		estates.forEach((address,estate) -> System.out.println(estate.toString()));		
	}

	
}
