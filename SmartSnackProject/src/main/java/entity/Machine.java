package entity;

import java.util.Objects;

public class Machine {

	private String machineId;
	private int machineTypeId;
	private String coutry;
	private int zipCode;
	private String city;
	private String address;

	public Machine() {
	}

	public Machine(String machineId, int machineTypeId, String coutry, int zipCode, String city, String address) {
		this.machineId = machineId;
		this.machineTypeId = machineTypeId;
		this.coutry = coutry;
		this.zipCode = zipCode;
		this.city = city;
		this.address = address;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public int getMachineTypeId() {
		return machineTypeId;
	}

	public void setMachineTypeId(int machineTypeId) {
		this.machineTypeId = machineTypeId;
	}

	public String getCoutry() {
		return coutry;
	}

	public void setCoutry(String coutry) {
		this.coutry = coutry;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(machineId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Machine))
			return false;
		Machine other = (Machine) obj;
		return Objects.equals(machineId, other.machineId);
	}

	@Override
	public String toString() {
		return "Automata [iD=" + machineId + "; " + zipCode + ". " + city + ", " + address + "]";
	}

	
}
