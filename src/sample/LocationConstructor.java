package sample;

public class LocationConstructor {
	private int LocID;
	private String BuildingName;
	private String Address;
	private String Country;
	private String State;
	private String City;
	private String Zipcode;
	public LocationConstructor(int LocID, String BuildingName,
			String Address, String Country, String State, 
			String City, String Zipcode){
		this.LocID=LocID;
		this.BuildingName=BuildingName;
		this.Address=Address;
		this.Country=Country;
		this.State=State;
		this.City=City;
		this.Zipcode=Zipcode;
	}
	public int getLocID() {
		return LocID;
	}
	public void setLocID(int locID) {
		LocID = locID;
	}
	public String getBuildingName() {
		return BuildingName;
	}
	public void setBuildingName(String buildingName) {
		BuildingName = buildingName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getZipcode() {
		return Zipcode;
	}
	public void setZipcode(String zipcode) {
		Zipcode = zipcode;
	}
}
