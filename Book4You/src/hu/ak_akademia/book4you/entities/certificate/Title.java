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

	private String longName;
	private Direction direction;

	private Title(String longName, Direction direction) {
		this.longName = longName;
		this.direction = direction;
	}

	public String getLongName() {
		return longName;
	}

	public Direction getDirection() {
		return direction;
	}

	public static String getName(String value) {
		for (Title enumName : Title.values()) {
			if (enumName.getLongName().equals(value)) {
				return enumName.name();
			}
		}
		return null;
	}

	public static List<String> getAllValues() {
		List<String> result = new ArrayList<>();

		for (Title e : Title.values()) {
			result.add(e.getLongName());
		}

		return result;
	}
	//Test
	public static Title getTitle(String value) {
		for( Title t : Title.values()) {
			if (t.getLongName().equals(value)) {
				return t;
			}
		}
		return null;
	}
}
