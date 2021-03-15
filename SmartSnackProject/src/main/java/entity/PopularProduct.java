package entity;

public class PopularProduct {
	
	private int machineId;
	private int zipCode;
	private String city;
	private String address;
	private int productId;
	private String productName;
	private int quantity;
	
	public PopularProduct(int machineId, int zipCode, String city, String address, int productId, String productName,
			int quantity) {
		super();
		this.machineId = machineId;
		this.zipCode = zipCode;
		this.city = city;
		this.address = address;
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
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

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + machineId;
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + quantity;
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
		PopularProduct other = (PopularProduct) obj;
		if (machineId != other.machineId)
			return false;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return machineId + ", " + productId + ", " + productName + ", " + quantity;
	}

}
