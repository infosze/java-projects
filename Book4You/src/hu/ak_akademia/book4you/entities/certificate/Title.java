package hu.ak_akademia.book4you.entities.certificate;

public enum Title {
	
	SELL_BOOK ("könyv eladás"),
	BUY_BOOK("könyv vétel"),
	PARKING_RENTAL("parkolóbérlet"),
	MOBILEPHONE("mobiltelefon számla"),
	CLEANING("takarítási költség"),
	INTERNET_SUBSCRIPTION("internet előfizetés"),
	LEAFLET("szórólap"),
	DEDICATION_EVENT("dedikálási esemény");
	
	private String title;

	private Title(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}
