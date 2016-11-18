package destination;

import java.util.Set;
import java.util.TreeSet;

import exceptions.CoutryException;
import exceptions.LocationException;

public class Country extends Location {

	private Set<City> cities = new TreeSet<City>();

	public Country(String name) throws LocationException {
		super(name);
	}
	public void addCity(City city) throws CoutryException{
		if(city != null){
			cities.add(city);
		}else{
			throw new CoutryException("Invalid city in the country!");
		}
	}
	@Override
	public String toString() {
		return "Country: " + super.getName();
	}
	
	
}
