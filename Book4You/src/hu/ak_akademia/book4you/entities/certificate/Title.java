package hu.ak_akademia.book4you.entities.certificate;

import java.util.ArrayList;
import java.util.List;

import hu.ak_akademia.book4you.entities.PublicSpaceType;

public enum Title {
	
	SELL_BOOK ("könyv eladás"),
	BUY_BOOK("könyv vétel"),
	PARKING_RENTAL("parkolóbérlet"),
	MOBILEPHONE("mobiltelefon számla"),
	CLEANING("takarítási költség"),
	INTERNET_SUBSCRIPTION("internet előfizetés"),
	LEAFLET("szórólap"),
	DEDICATION_EVENT("dedikálási esemény");
	
	private String value;

	private Title(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static String getName(String value) {
		for (Title enumName : Title.values()) {
			if (enumName.getValue().equals(value)) {
				return enumName.name();
			}
		}
		return null;
	}
	
	public static List<String> getAllValues() {
		List<String> result = new ArrayList<>();

		for (Title e : Title.values()) {
			result.add(e.getValue());
		}

		return result;
	}
}
