package entryRoomData;

public class TotalFloorCalculator {

	protected Room[] rooms;
	protected int materialsNum;
	protected String[] selectedMaterials;
	protected double[] selectedMatTotalPack;
	protected double[] selectedMatPricePerPack;
	private double totalPrice = 0.0;

	TotalFloorCalculator(Room[] rooms) {
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
				rooms[i].getFloorMaterialName().isEmpty();
				if (!rooms[i].getFloorMaterialName().isEmpty()) {
					for (int j = 1; j < i; j++) {
						try {
							rooms[j].getFloorMaterialName().isEmpty();
							if (!rooms[j].getFloorMaterialName().isEmpty()) {
								if (rooms[i].getFloorMaterialName().equals(rooms[j].getFloorMaterialName()))
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
				rooms[i].getFloorMaterialName().isEmpty();
				if (!rooms[i].getFloorMaterialName().isEmpty()) {
					for (int j = 1; j < i; j++) {
						try {
							rooms[j].getFloorMaterialName().isEmpty();
							if (!rooms[j].getFloorMaterialName().isEmpty()) {
								if (rooms[i].getFloorMaterialName().equals(rooms[j].getFloorMaterialName()))
									continue outer;
							}
						} catch (NullPointerException ex) {
//							System.out.println("3");
						}
					}
					names[counter] = rooms[i].getFloorMaterialName();
					counter++;
				}
			} catch (NullPointerException ex) {
//				System.out.println("4");
			}
		}
		return names;
	}

	void printTotalMaterialsAndPrices() {
//		for (int i = 0; i < selectedMaterials.length; i++) {
//			System.out.println(selectedMaterials[i]);
//		}
		for (int i = 0; i < selectedMaterials.length; i++) {
			for (int j = 1; j < rooms.length; j++) {
				try {
					rooms[j].getFloorMaterialName().isEmpty();
					if (!rooms[j].getFloorMaterialName().isEmpty()) {
						if (rooms[j].getFloorMaterialName().equals(selectedMaterials[i])) {
							selectedMatTotalPack[i] += rooms[j].getFloorPack();
							selectedMatPricePerPack[i] = rooms[j].getFloorMaterialPrice();
						}
					}
				} catch (NullPointerException ex) {
//					System.out.println("5");
				}
			}
			double selectedMatTotalPackRounded = Math.ceil(selectedMatTotalPack[i]);
			double price = selectedMatTotalPackRounded * selectedMatPricePerPack[i];
			totalPrice += price;
			System.out.printf(
					"neve: %s, a szükséges mennyiség: %.0f csomag, ennek ára: %.0f forint%n",
					selectedMaterials[i], selectedMatTotalPackRounded,
					price);
		}
		System.out.printf("ára összesen: %,.0f forint%n", totalPrice);
	}

	public double getTotalPrice() {
		return totalPrice;
	}
}
