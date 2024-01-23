package demo.model;

public class AnimalShelter {
	private int id;
	private String area;
	private String shelterName;
	private String address;
	private String phone;

	public AnimalShelter() {
	}

	public AnimalShelter(int id, String area, String shelterName, String address, String phone) {		
		this.id = id;
		this.area = area;
		this.shelterName = shelterName;
		this.address = address;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getShelterName() {
		return shelterName;
	}

	public void setShelterName(String shelterName) {
		this.shelterName = shelterName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
