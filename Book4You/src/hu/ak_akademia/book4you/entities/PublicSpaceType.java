package hu.ak_akademia.book4you.entities;

public enum PublicSpaceType {

	ROAD("út"), STREET("utca"), BOULEVARD("sétány"), PUBLIC("köz"), SQUARE("tér"), AVENUE("sugárút"), WHARF("rakpart");

	private String value;

	private PublicSpaceType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static String getName(String value) {
		for (PublicSpaceType enumName : PublicSpaceType.values()) {
			if (enumName.getValue().equals(value)) {
				return enumName.name();
			}
		}
		return null;
	}
}
