package entryRoomData;

class CalculationManager {
	
	Room[] rooms;
	TotalConcreteCalculator concreteCalculator;
	TotalFloorCalculator floorCalculator;
	TotalWallCalculator wallCalculator;
	TotalGlueCalculator glueCalculator;
	private double totalPrice;
	
	public double getTotalPrice() {
		return totalPrice;
	}

	CalculationManager(Room[] rooms) {
		this.rooms = rooms;
		concreteCalculator = new TotalConcreteCalculator(rooms);
		floorCalculator = new TotalFloorCalculator(rooms);
		wallCalculator = new TotalWallCalculator(rooms);
		glueCalculator = new TotalGlueCalculator(rooms);
	}
	
	void runCalculations () {
		
		System.out.println("A kiválasztott aljzatkiegyenlítő(k)");
		concreteCalculator.printTotalMaterialsAndPrices();
		System.out.println();
		System.out.println("A kiválasztott paldóburkolat(ok)");
		floorCalculator.printTotalMaterialsAndPrices();
		System.out.println();
		System.out.println("A kiválasztott falburkolat(ok)");
		wallCalculator.printTotalMaterialsAndPrices();
		System.out.println();
		System.out.println("A kiválasztott segédanyag(ok)");
		glueCalculator.printTotalMaterialsAndPrices();
		System.out.println();
		totalPrice = sumupTotalPrice();
		System.out.printf("Végösszeg: %,.0f forint%n", totalPrice);
	}
	
	double sumupTotalPrice () {
		double sum = concreteCalculator.getTotalPrice() + floorCalculator.getTotalPrice() + wallCalculator.getTotalPrice() + glueCalculator.getTotalPrice();
		return sum;
	}
	
}
