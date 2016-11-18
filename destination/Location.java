package destination;

import exceptions.LocationException;

public abstract class Location {
	private String name;
	
	public Location(String name) throws LocationException{
		if(name != null){
			this.name = name;
		}else{
			throw new LocationException("Invalid name!");
		}
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Location [name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
