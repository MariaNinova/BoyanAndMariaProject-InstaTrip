package users;

import java.util.ArrayList;

import destination.City;
import destination.Country;
import destination.Estate;
import destination.Room;
import exceptions.CityException;
import exceptions.EstateException;
import exceptions.LocationException;
import exceptions.RoomException;

public interface IOwnerAccount {

	void addEstate(String address, Country country, City city, byte stars) throws EstateException, CityException, LocationException;
	
	void createRooms(Estate estate, int adults, int children, int numRooms, int priceForNight) throws RoomException;
}

