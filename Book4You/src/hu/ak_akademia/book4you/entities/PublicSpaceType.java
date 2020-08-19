package hu.ak_akademia.book4you.entities;

import java.util.ArrayList;
import java.util.List;

public enum PublicSpaceType {

	ROAD("út"), STREET("utca"), BOULEVARD("sétány"), PUBLIC("köz"), SQUARE("tér"), AVENUE("sugárút"), WHARF("rakpart");

	private String longName;

	private PublicSpaceType(String value) {
		this.longName = value;
	}

	public String getLongName() {
		return longName;
	}
	
	public static PublicSpaceType getEnum(String longName) {
		for (var pst : PublicSpaceType.values()) {
			if (pst.getLongName().equals(longName)) {
				return pst;
			}
		}
		return null;
	}

	public static List<String> getAllValues() {
		List<String> result = new ArrayList<>();

		for (PublicSpaceType e : PublicSpaceType.values()) {
			result.add(e.getLongName());
		}

		return result;
	}
}
