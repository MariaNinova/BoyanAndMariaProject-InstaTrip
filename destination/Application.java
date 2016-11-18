package destination;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import exceptions.CoutryException;
import exceptions.LocationException;
import exceptions.RoomException;
import users.Account;
import users.OwnerAccount;
import users.User;

public class Application {

	public static Map<Country, Map<String, City>> locations = new HashMap<Country, Map<String, City>>();

	public static void main(String[] args) throws RoomException {

		try {

			generateLocations();

			Country bg = new Country("Bulgaria");

			City sofia = locations.get(bg).get("Sofia");

			OwnerAccount boyan = new OwnerAccount("Boyan1993", "Ronaldo7", "bo4ko_vr@abv.bg", "Boyan", "Enchev");

			Estate CeaserPalace = new Estate("Rila 5", locations.get(bg).get("Sofia"), (byte) 5);
			boyan.addEstate(CeaserPalace, bg);
			
			
			boyan.addEstate("Sofia+Nishava+5", bg, locations.get(bg).get("Sofia"), (byte) 5);
			boyan.addEstate("Sofia+ZlatenRog+18", bg, locations.get(bg).get("Sofia"), (byte) 3);
			boyan.addEstate("Sofia+KrumPopov+22", bg, locations.get(bg).get("Sofia"), (byte) 4);

			boyan.createRooms(boyan.getEstate("Rila 5"), 2, 0, 3, 80);
			boyan.createRooms(boyan.getEstate("Sofia+Nishava+5"), 2, 2, 3, 80);
			boyan.createRooms(boyan.getEstate("Sofia+ZlatenRog+18"), 5, 0, 4, 120);
			boyan.createRooms(boyan.getEstate("Sofia+KrumPopov+22"), 3, 0, 2, 200);

			User.search(bg, sofia, 2016, 12, 20, 2016, 12, 25, 2, 0);
			
			Account vanio = new Account("Ivancho97", "fhje7Ufef", "vancyki@abv.bg", "Ivan", "Kolev");
			
			
			vanio.reserve(CeaserPalace, LocalDate.of(2016,12,24), LocalDate.of(2016,12,29), 2, 0);
			vanio.reserve(CeaserPalace, LocalDate.of(2016,12,24), LocalDate.of(2016,12,29), 2, 0);
		
			
			User.search(bg, sofia, 2016, 12, 20, 2016, 12, 25, 2, 0);
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	public static void generateLocations() throws LocationException, CoutryException {
		Country bulgaria = new Country("Bulgaria");
		Country spain = new Country("Spain");
		Country italy = new Country("Italy");

		Map<String, City> bgCities = new HashMap<String, City>();

		bgCities.put("Sofia", new City("Sofia"));
		bgCities.put("Varna", new City("Varna"));
		bgCities.put("Burgas", new City("Burgas"));
		locations.put(bulgaria, bgCities);

		Map<String, City> spCities = new HashMap<String, City>();
		spCities.put("Barcelona", new City("Barcelona"));
		spCities.put("Madrid", new City("Madrid"));
		spCities.put("Palma De Mallorka", new City("Palma De Mallorka"));
		locations.put(spain, spCities);

		Map<String, City> itCities = new HashMap<String, City>();
		itCities.put("Rome", new City("Rome"));
		itCities.put("Milano", new City("Milano"));
		itCities.put("Genoa", new City("Genoa"));
		locations.put(italy, itCities);

	}

}
