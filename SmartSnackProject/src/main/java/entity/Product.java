package entity;

import java.util.Objects;

public class Product {

	private int productId;
	private int productTypeId;
	private String name;
	private double volume;
	private String unitOfMeasure;
	private double unitPrice;
	private String currency;

	public Product() {
	}

	public Product(int productId, int productTypeId, String name, double volume, String unitOfMeasure, double unitPrice, String currency) {
		this.productId = productId;
		this.productTypeId = productTypeId;
		this.name = name;
		this.volume = volume;
		this.unitOfMeasure = unitOfMeasure;
		this.unitPrice = unitPrice;
		this.currency = currency;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		return productId == other.productId;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + "]";
	}

}
