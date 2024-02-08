package application;

public class City {// this class represents the vertix as its here the cities in gaza and each city
					// have name and longitude and Latitude
	public String name; // name of the city
	public double Latitude;
	public double longitude;

	public City() { // Constructor with all parameters
		this.name = name;
		this.Latitude = Latitude;
		this.longitude = longitude;
	}

	public City(String name, double x, double y) { // Constructor with all parameters
		this.name = name;
		this.Latitude = x;
		this.longitude = y;
	}

	public String getname() {// get name of city
		return name;
	}

	public void setname(String name) {// set name of city
		this.name = name;
	}

	public double getX() {// grt Latitude
		return Latitude;
	}

	public void setX(double x) {// set Latitude
		this.Latitude = x;
	}

	public double getY() {// get longitude
		return longitude;
	}

	public void setY(double y) {// set longitude
		this.longitude = y;
	}

	@Override
	public String toString() {// to string that print out the city name
		return name;
	}

}