package hu.ak_akademia.book4you.entities.certificate;

import java.util.ArrayList;
import java.util.List;

public enum Title {

	//@formatter:off
	SELL_BOOK              ("könyv eladás",        Direction.INCOME),
	BUY_BOOK               ("könyv vétel",         Direction.OUTCOME),
	PARKING_RENTAL         ("parkolóbérlet",       Direction.INCOME),
	MOBILEPHONE            ("mobiltelefon számla", Direction.INCOME),
	CLEANING               ("takarítási költség",  Direction.OUTCOME),
	INTERNET_SUBSCRIPTION  ("internet előfizetés", Direction.OUTCOME),
	LEAFLET                ("szórólap",            Direction.OUTCOME),
	DEDICATION_EVENT       ("dedikálási esemény",  Direction.INCOME);
	//@formatter:on

	private String value;
	private Direction direction;

	private Title(String longName, Direction direction) {
		this.value = longName;
		this.direction = direction;
	}

	public String getValue() {
		return value;
	}

	public Direction getDirection() {
		return direction;
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
	//Test
	public static Title getTitle(String value) {
		for( Title t : Title.values()) {
			if (t.getValue().equals(value)) {
				return t;
			}
		}
		return null;
	}
}
