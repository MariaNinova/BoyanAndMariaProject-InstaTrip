package destination;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import exceptions.CityException;
import exceptions.LocationException;

public class City extends Location {

	private Map<String, Estate> estates = new HashMap<String, Estate>();			//??????????????
	
	public City(String name) throws LocationException {
		super(name);
	}
	
	public void addEstate(Estate estate) throws CityException{
		if(estate != null){
			estates.put(estate.getAddress(), estate);
		}else{
			throw new CityException("Invalid destitination in the city!");
		}
	}

	public String getName() {
		return super.getName();
	}

	@Override
	public String toString() {
		return "City: " + getName();
	}

	public Map<String,Estate> getEstates() {
		return estates;
	}

}
