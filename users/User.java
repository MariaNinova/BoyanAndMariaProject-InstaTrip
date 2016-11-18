package users;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import destination.Application;
import destination.City;
import destination.Country;
import destination.Estate;
import destination.Room;
import exceptions.ReservationException;
import exceptions.RoomException;

public abstract class User {

	public static List<ArrayList<Room>> search(Country country, City city, int startYear, int startMonth, int startDay,
			int endYear, int endMonth, int endDay, int numAdults, int numChildren)
					throws RoomException, ReservationException {

		List<ArrayList<Room>> result = new ArrayList<ArrayList<Room>>();

		if (Application.locations.containsKey(country)) {

			Map<String, City> cities = Application.locations.get(country);

			if (cities.containsKey(city.getName())) {

				Map<String, Estate> estatesInCity = cities.get(city.getName()).getEstates();

				List<Estate> estates = new ArrayList<Estate>(estatesInCity.values());

				int added = 0;
				for (int count = 0; count < estates.size(); count++) {

					ArrayList<Room> temp = estates.get(count).getAvailableRooms(startYear, startMonth, startDay,
							endYear, endMonth, endDay, numAdults, numChildren);

					if (!temp.isEmpty()) {
						System.out.println(country.getName() + ", " + city.getName() + ", "
								+ estates.get(count).getAddress() + ": " + temp);
						result.add(added, temp);
						added++;
					}
				}
			}
		}

		return result;

	}

}
