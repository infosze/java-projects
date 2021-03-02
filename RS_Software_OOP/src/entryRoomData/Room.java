package entryRoomData;

/**
 * 
 * @author Márton Izabella & Rózsa Tamás
 *
 */
public class Room {

	private double width, length, height;
	private String floorConcreteName;
	private double floorConcretePrice, floorConcreteAmount;
	private String floorMaterialName;
	private double floorMaterialPrice, floorMaterialAmount;
	private String wallMaterialName;
	private double wallMaterialPrice, wallMaterialAmount;
	private String wallGlueName;
	private double wallGluePrice, wallGlueAmount;
	private double floorArea;
	private double wallArea;

	/**
	 * Setup room dimension parameters & calculate floor & wall surfaces
	 * 
	 * @param width
	 * @param length
	 * @param height
	 */
	public Room(double width, double length, double height) {
		this.width = width / 1_000; // mm
		this.length = length / 1_000; // mm
		this.height = height / 1_000;
		calcFloorArea();
		calcWallArea();
	}

	public Room(double width, double length) {
		this.width = width / 1_000;
		this.length = length / 1_000;
		calcFloorArea();
	}

//  PRINT ROOM DATA
	public void printRoomData() {
		System.out.println("helyiség adatai:");
		System.out.printf("Szélesség: %.0f mm / Hosszúság: %.0f mm / Magasság: %.0f mm%n", width * 1_000,
				length * 1_000, height * 1_000);
		System.out.printf("Padlóterület: %,.2f m2 / Falfelület: %,.2f m2%n%n", floorArea, wallArea);
		if (floorConcreteName != null) {
			System.out.printf("Aljzat burkolat                       : %s (%.2f m2/csomag, %,.2f Ft/csomag)%n",
					floorConcreteName, floorConcreteAmount, floorConcretePrice);
			System.out.printf("Szükséges anyagmennyiség              : %.2f csomag ennek a költsége: %,.2f Ft%n",
					getConcretePack(), getConcretePrice());
			System.out.printf("Szükséges kerekített anyagmennyiség   : %.0f csomag ennek a költsége: %,.0f Ft%n%n",
					getConcretePackRounded(), getConcretePriceRounded());
		}
		if (floorMaterialName != null) {
			System.out.printf("Padló burkolat                        : %s (%.2f m2/csomag, %,.2f Ft/csomag)%n",
					floorMaterialName, floorMaterialAmount, floorMaterialPrice);
			System.out.printf("Szükséges anyagmennyiség              : %.2f csomag ennek a költsége: %,.2f Ft%n",
					getFloorPack(), getFloorPrice());
			System.out.printf("Szükséges kerekített anyagmennyiség   : %.0f csomag ennek a költsége: %,.0f Ft%n%n",
					getFloorPackRounded(), getFloorPriceRounded());
		}
		if (wallMaterialName != null) {
			System.out.printf("Fal burkolat                          : %s (%.2f m2/csomag, %,.2f Ft/csomag)%n",
					wallMaterialName, wallMaterialAmount, wallMaterialPrice);
			System.out.printf("Szükséges anyagmennyiség              : %.2f csomag ennek a költsége: %,.2f Ft%n",
					getWallPack(), getWallPrice());
			System.out.printf("Szükséges kerekített anyagmennyiség   : %.0f csomag ennek a költsége: %,.0f Ft%n%n",
					getWallPackRounded(), getWallPriceRounded());
		}
		if (wallGlueName != null) {
			System.out.printf("A falburkolathoz szükséges segédanyag : %s (%.2f m2/csomag, %,.2f Ft/csomag)%n",
					wallGlueName, wallGlueAmount, wallGluePrice);
			System.out.printf("Szükséges anyagmennyiség              : %.2f csomag ennek a költsége: %,.2f Ft%n",
					getGluePack(), getGluePrice());
			System.out.printf("Szükséges kerekített anyagmennyiség   : %.0f csomag ennek a költsége: %,.0f Ft%n%n",
					getGluePackRounded(), getGluePriceRounded());
		}
	}

	/*
	 * GETTERS and SETTERS
	 */
	public double getWidth() {
		return width;
	}

	public double getLength() {
		return length;
	}

	public double getHeight() {
		return height;
	}

	public String getFloorConcreteName() {
		return floorConcreteName;
	}

	public void setFloorConcreteName(String floorConcreteName) {
		this.floorConcreteName = floorConcreteName;
	}

	public double getFloorConcretePrice() {
		return floorConcretePrice;
	}

	public void setFloorConcretePrice(String concretePrice) {
		double floorConcretePrice = Double.parseDouble(concretePrice.strip().replace(",", "."));
		this.floorConcretePrice = floorConcretePrice;
	}

	public double getFloorConcreteAmount() {
		return floorConcreteAmount;
	}

	public void setFloorConcreteAmount(String concreteAmount) {
		double floorConcreteAmount = Double.parseDouble(concreteAmount.strip().replace(",", "."));
		this.floorConcreteAmount = floorConcreteAmount;
	}

	public String getFloorMaterialName() {
		return floorMaterialName;
	}

	public void setFloorMaterialName(String floorMaterialName) {
		this.floorMaterialName = floorMaterialName;
	}

	public double getFloorMaterialPrice() {
		return floorMaterialPrice;
	}

	public void setFloorMaterialPrice(String floormaterialPrice) {
		double floorMaterialPrice = Double.parseDouble(floormaterialPrice.replace(",", "."));
		this.floorMaterialPrice = floorMaterialPrice;
	}

	public double getFloorMaterialAmount() {
		return floorMaterialAmount;
	}

	public void setFloorMaterialAmount(String floormaterialAmount) {
		double floorMaterialAmount = Double.parseDouble(floormaterialAmount.replace(",", "."));
		this.floorMaterialAmount = floorMaterialAmount;
	}

	public String getWallMaterialName() {
		return wallMaterialName;
	}

	public void setWallMaterialName(String wallMaterialName) {
		this.wallMaterialName = wallMaterialName;
	}

	public double getWallMaterialPrice() {
		return wallMaterialPrice;
	}

	public void setWallMaterialPrice(String wallmaterialPrice) {
		double wallMaterialPrice = Double.parseDouble(wallmaterialPrice.replace(",", "."));
		this.wallMaterialPrice = wallMaterialPrice;
	}

	public double getWallMaterialAmount() {
		return wallMaterialAmount;
	}

	public void setWallMaterialAmount(String wallmaterialAmount) {
		double wallMaterialAmount = Double.parseDouble(wallmaterialAmount.replace(",", "."));
		this.wallMaterialAmount = wallMaterialAmount;
	}

	public String getWallGlueName() {
		return wallGlueName;
	}

	public void setWallGlueName(String wallGlueName) {
		this.wallGlueName = wallGlueName;
	}

	public double getWallGluePrice() {
		return wallGluePrice;
	}

	public void setWallGluePrice(String wallgluePrice) {
		double wallGluePrice = Double.parseDouble(wallgluePrice.replace(",", "."));
		this.wallGluePrice = wallGluePrice;
	}

	public double getWallGlueAmount() {
		return wallGlueAmount;
	}

	public void setWallGlueAmount(String wallglueAmount) {
		double wallGlueAmount = Double.parseDouble(wallglueAmount.replace(",", "."));
		this.wallGlueAmount = wallGlueAmount;
	}

	/*
	 * NECESSARY BASIC DATA CALC METHODS
	 */
	public double calcFloorArea() {
		return floorArea = width * length;
	}

	public double calcWallArea() {
		return wallArea = (width * height + length * height) * 2;
	}

	/*
	 * 
	 */
	public double getConcretePack() {
		return floorArea / floorConcreteAmount;
	}

	public double getConcretePackRounded() {
		return Math.ceil(getConcretePack());
	}

	public double getConcretePrice() {
		return floorConcretePrice * (floorArea / floorConcreteAmount);
	}

	public double getConcretePriceRounded() {
		return floorConcretePrice * getConcretePackRounded();
	}

	public double getFloorPack() {
		return floorArea / floorMaterialAmount;
	}

	public double getFloorPackRounded() {
		return Math.ceil(getFloorPack());
	}

	public double getFloorPrice() {
		return floorMaterialPrice * (floorArea / floorMaterialAmount);
	}

	public double getFloorPriceRounded() {
		return floorMaterialPrice * getFloorPackRounded();
	}

	public double getWallPack() {
		return wallArea / wallMaterialAmount;
	}

	public double getWallPackRounded() {
		return Math.ceil(getWallPack());
	}

	public double getWallPrice() {
		return wallMaterialPrice * (wallArea / wallMaterialAmount);
	}

	public double getWallPriceRounded() {
		return wallMaterialPrice * getWallPackRounded();
	}

	public double getGluePack() {
		return wallArea / wallGlueAmount;
	}

	public double getGluePackRounded() {
		return Math.ceil(getGluePack());
	}

	public double getGluePrice() {
		return wallGluePrice * getGluePack();
	}

	public double getGluePriceRounded() {
		return wallGluePrice * getGluePackRounded();
	}

	/*
	 * FULL SETUP METHODS
	 */
	public void setFloorConcrete(String[] floorConcrete) {
		this.floorConcreteName = floorConcrete[0];
		double floorConcretePrice = Double.parseDouble(floorConcrete[1].replace(",", "."));
		this.floorConcretePrice = floorConcretePrice;
		double floorConcreteAmount = Double.parseDouble(floorConcrete[2].replace(",", "."));
		this.floorConcreteAmount = floorConcreteAmount;
	}

	public void setFloorMaterial(String[] floorMaterial) {
		this.floorMaterialName = floorMaterial[0];
		double floorMaterialPrice = Double.parseDouble(floorMaterial[1].replace(",", "."));
		this.floorMaterialPrice = floorMaterialPrice;
		double floorMaterialAmount = Double.parseDouble(floorMaterial[2].replace(",", "."));
		this.floorMaterialAmount = floorMaterialAmount;
	}

	public void setWallMaterial(String[] wallMaterial) {
		this.wallMaterialName = wallMaterial[0];
		double wallMaterialPrice = Double.parseDouble(wallMaterial[1].replace(",", "."));
		this.wallMaterialPrice = wallMaterialPrice;
		double wallMaterialAmount = Double.parseDouble(wallMaterial[2].replace(",", "."));
		this.wallMaterialAmount = wallMaterialAmount;
	}

	public void setWallGlue(String[] glueMaterials) {
		this.wallGlueName = glueMaterials[0];
		double wallGluePrice = Double.parseDouble(glueMaterials[1].replace(",", "."));
		this.wallGluePrice = wallGluePrice;
		double wallGlueAmount = Double.parseDouble(glueMaterials[2].replace(",", "."));
		this.wallGlueAmount = wallGlueAmount;
	}

}
