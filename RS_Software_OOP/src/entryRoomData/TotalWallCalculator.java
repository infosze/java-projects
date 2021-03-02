package entryRoomData;

class TotalWallCalculator {

	protected Room[] rooms;
	protected int materialsNum;
	protected String[] selectedMaterials;
	protected double[] selectedMatTotalPack;
	protected double[] selectedMatPricePerPack;
	private double totalPrice = 0.0;
	private String offer = "";

	TotalWallCalculator(Room[] rooms) {
		this.rooms = rooms;
		materialsNum = countSelectedMaterials();
		selectedMaterials = fillUpSelectedMaterials();
		selectedMatTotalPack = new double[materialsNum];
		selectedMatPricePerPack = new double[materialsNum];
	}

	protected int countSelectedMaterials() {
		int counter = 0;
		outer: for (int i = 1; i < rooms.length; i++) {
			try {
				rooms[i].getWallMaterialName().isEmpty();
				if (!rooms[i].getWallMaterialName().isEmpty()) {
					for (int j = 1; j < i; j++) {
						try {
							rooms[j].getWallMaterialName().isEmpty();
							if (!rooms[j].getWallMaterialName().isEmpty()) {
								if (rooms[i].getWallMaterialName().equals(rooms[j].getWallMaterialName()))
									continue outer;
							}
						} catch (NullPointerException ex) {
//							System.out.println("1");
						}
					}
					counter++;
				}
			} catch (NullPointerException ex) {
//				System.out.println("2");
			}
		}
		return counter;
	}

	protected String[] fillUpSelectedMaterials() {
		String[] names = new String[materialsNum];
		int counter = 0;
		outer: for (int i = 1; i < rooms.length; i++) {
			try

			{
				rooms[i].getWallMaterialName().isEmpty();
				if (!rooms[i].getWallMaterialName().isEmpty()) {
					for (int j = 1; j < i; j++) {
						try {
							rooms[j].getWallMaterialName().isEmpty();
							if (!rooms[j].getWallMaterialName().isEmpty()) {
								if (rooms[i].getWallMaterialName().equals(rooms[j].getWallMaterialName()))
									continue outer;
							}
						} catch (NullPointerException ex) {
//							System.out.println("3");
						}
					}
					names[counter] = rooms[i].getWallMaterialName();
					counter++;
				}
			} catch (NullPointerException ex) {
//				System.out.println("4");
			}
		}
		return names;
	}

	String printTotalMaterialsAndPrices() {
//		for (int i = 0; i < selectedMaterials.length; i++) {
//			System.out.println(selectedMaterials[i]);
//		}
		for (int i = 0; i < selectedMaterials.length; i++) {
			for (int j = 1; j < rooms.length; j++) {
				try {
					rooms[j].getWallMaterialName().isEmpty();
					if (!rooms[j].getWallMaterialName().isEmpty()) {
						if (rooms[j].getWallMaterialName().equals(selectedMaterials[i])) {
							selectedMatTotalPack[i] += rooms[j].getWallPack();
							selectedMatPricePerPack[i] = rooms[j].getWallMaterialPrice();
						}
					}
				} catch (NullPointerException ex) {
//					System.out.println("5");
				}
			}
			double selectedMatTotalPackRounded = Math.ceil(selectedMatTotalPack[i]);
			double price = selectedMatTotalPackRounded * selectedMatPricePerPack[i];
			totalPrice += price;
			System.out.printf("neve: %s, a szükséges mennyiség: %.0f csomag, ennek ára: %.0f forint%n",
					selectedMaterials[i], selectedMatTotalPackRounded, price);
			offer += String.format("neve: %s, a szükséges mennyiség: %.0f csomag, ennek ára: %.0f forint%n",
					selectedMaterials[i], selectedMatTotalPackRounded, price);
		}
		System.out.printf("ára összesen: %,.0f forint%n", totalPrice);
		offer += String.format("ára összesen: %,.0f forint%n", totalPrice);
		return offer;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
}
