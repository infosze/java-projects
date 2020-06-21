package hu.ak_akademia.book4you.entities;

public enum PublicSpaceType {
	
	ROAD("út"),
	STREET("utca"),
	BOULEVARD("sétány"),
	PUBLIC("köz"),
	SQUARE("tér"),
	AVENUE("sugárút"),
	WHARF("rakpart");

	private String name;

	private PublicSpaceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
