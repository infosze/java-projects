package entryRoomData;

import java.util.InputMismatchException;
import java.util.Scanner;
import fileOperation.FileOperation;
import menu.StartMenu;
import print.MaterialPrint;

public class RoomDataEntry {

	private Scanner scan;

	public RoomDataEntry(Scanner scan) {
		this.scan = scan;
	}

	MaterialPrint materialPrint = new MaterialPrint();
	FileOperation fileOperation = new FileOperation();
	StartMenu startMenu = new StartMenu();
	private Room rooms[];
	public String[] concreteList = fileOperation.readMaterial("concrete");
	public String[] floorMatsList = fileOperation.readMaterial("floorMats");
	public String[] parquetList = fileOperation.readMaterial("parquet");
	public String[] wallpaperList = fileOperation.readMaterial("wallpaper");
	public String[] wallpaperGlueList = fileOperation.readMaterial("wallpaperGlue");
	private double discount;
	private double discountedPrice;
	private double totalPrice;

	/**
	 * This method requests room information and calls other methods to calculate
	 * amounts of materials and prices
	 */
	public void addRoomData(String[] chosenCustomer) {
		String inputLetter;
		int index;
		double roomWidth;
		double roomLength;
		double roomHeight;
		int roomNumber = 0;
		String questionFloor = "KÉR PADLÓKIEGYENLÍTÉST?";
		String questionCarpet = "KÉR PADLÓSZŐNYEGET?";
		String questionParquet = "KÉR SZALAGPARKETTÁT?";
		String questionWallPaper = "KÉR TAPÉTÁT?";
		String roomWidthText = "Szélesség (mm):";
		String roomLengthText = "Hosszúság (mm):";
		String roomHightText = "Magasság (mm) :";
		String questionDiscount = "AKAR KEDVEZMÉNYT ADNI?";

		System.out.println();
		roomNumber = enteringRoomNumbers();
// CREATE OBJECT ARRAY
		rooms = new Room[roomNumber + 1];
		for (index = 1; index <= roomNumber; index++) {
			boolean empty = true;
			do {
				boolean height = false;
				do {
					System.out.printf("%n%d. HELYISÉG ADATAI:%n", index);
					roomWidth = roomSize(roomWidthText, false);
					roomLength = roomSize(roomLengthText, false);
					roomHeight = roomSize(roomHightText, true);
					System.out.println();
// ROOM DIMENSION SETUP
					rooms[index] = new Room(roomWidth, roomLength, roomHeight);
					if (roomHeight > 0) {
						height = true;
					}
				} while (goodOrNoDataEntry().equals("u"));
// CONCRETE
				do {
					do {
						inputLetter = wantOrNoWorkflow(questionFloor);
						if (inputLetter.equals("i")) {
							materialPrint.print(concreteList);
							rooms[index].setFloorConcrete(chooseMaterials(concreteList));
							empty = false;
						} else if (inputLetter.equals("n")) {
							break;
						}
						System.out.println();
					} while (goodOrNoDataEntry().equals("u"));
// CARPET
					do {
						inputLetter = wantOrNoWorkflow(questionCarpet);
						if (inputLetter.equals("i")) {
							materialPrint.print(floorMatsList);
							rooms[index].setFloorMaterial(chooseMaterials(floorMatsList));
							empty = false;
						} else if (inputLetter.equals("n")) {
							break;
						}
						System.out.println();
					} while (goodOrNoDataEntry().equals("u"));
// PARQUET
					do {
						inputLetter = wantOrNoWorkflow(questionParquet);
						if (inputLetter.equals("i")) {
							materialPrint.print(parquetList);
							rooms[index].setFloorMaterial(chooseMaterials(parquetList));
							empty = false;
						} else if (inputLetter.equals("n")) {
							break;
						}
						System.out.println();
					} while (goodOrNoDataEntry().equals("u"));
					if (height == true) {
						do {
							inputLetter = wantOrNoWorkflow(questionWallPaper);
// WALLPAPER & GLUE
							if (inputLetter.equals("i")) {
								materialPrint.print(wallpaperList);
								rooms[index].setWallMaterial(chooseMaterials(wallpaperList));
								System.out.println();
								System.out.println("Válassza ki a tapétaragasztót a listából:");
								materialPrint.print(wallpaperGlueList);
								rooms[index].setWallGlue(chooseMaterials(wallpaperGlueList));
								empty = false;
							} else if (inputLetter.equals("n")) {
								break;
							}
							System.out.println();
						} while (goodOrNoDataEntry().equals("u"));
					}
					if (empty) {
						System.out.println("Kérem, adjon meg legalább egy munkafolyamatot.");
					}
				} while (empty);
				System.out.println();
				System.out.println("A helyiség adatai felvitelre kerültek.");
				System.out.println();
// PRINT ROOM DATA
				System.out.print(index + ". ");
				rooms[index].printRoomData();

				if (index == roomNumber) {
					System.out.println("A HELYISÉGEK ANYAGSZÜKSÉGLETÉNEK ÖSSZESÍTÉSE:");
					System.out.println();
// CALCULATION AND PRINT OF FINAL VALUES FOR MATERIAL AMOUNT AND PRICE
					var calculationManager = new CalculationManager(getRooms());
					calculationManager.runCalculations();
					do {
						inputLetter = wantOrNoWorkflow(questionDiscount);
						if (inputLetter.equals("i")) {
							discount = giveDiscount();
							empty = false;
						} else if (inputLetter.equals("n")) {
							break;
						}
						System.out.println();
					} while (goodOrNoDataEntry().equals("u"));
					totalPrice = calculationManager.getTotalPrice();
					discountedPrice = totalPrice * (100 - discount) / 100;
					System.out.printf("A kedvezménnyel csökkentett végösszeg: %,.0f Ft%n", discountedPrice);
					var dataFormater = new DataFormater(rooms, scan, totalPrice, discount, discountedPrice,
							chosenCustomer);
					dataFormater.printOffer();
//					System.out.printf("%nAz árajánlat felvitelre került.%n");
				}
				System.out.println();
			} while (goodOrNoDataEntry().equals("u"));
		}
		System.out.println();
// PRINT ALL ROOM DATA
//		for (int i = 1; i < rooms.length; i++) {
//			System.out.print(index + ". ");
//			rooms[i].printRoomData();
//		}

	}

	public double getDiscount() {
		return discount;
	}

	public Room[] getRooms() {
		return rooms;
	}

	public String goodOrNoDataEntry() {
		String inputLetter;
		boolean notGoodData = false;
		do {
			notGoodData = false;
			System.out.printf("JÓ AZ ADATBEVITEL? (\"i\"-igen / \"u\"-újra) ");
			inputLetter = scan.next().trim();
			checkExit(inputLetter);

			if (!inputLetter.equals("i") && !inputLetter.equals("u")) {
				System.out.println();
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				notGoodData = true;
			}
		} while (notGoodData);
		return inputLetter;
	}

	public String wantOrNoWorkflow(String workflow) {
		String inputLetter;
		boolean notGoodData = false;
		do {
			notGoodData = false;
			System.out.println();
			System.out.print(workflow);
			System.out.print(" (\"i\"-igen / \"n\"-nem) ");
			inputLetter = scan.next().trim();
			checkExit(inputLetter);
			if (!inputLetter.equals("i") && !inputLetter.equals("n")) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				notGoodData = true;
			}
		} while (notGoodData);
		return inputLetter;
	}

	public int enteringRoomNumbers() {
		int roomNumber = 0;
		boolean notGoodData = false;
		do {
			do {
				notGoodData = false;
				System.out.print("ADJA MEG A HELYISÉGEK SZÁMÁT: ");
				try {
					roomNumber = scan.nextInt();
					scan.nextLine();
					checkExit(roomNumber);
					System.out.println();
				} catch (InputMismatchException e) {
					System.out.println("Kérem, megfelelő adatot adjon meg!");
					System.out.println();
					scan.next();
					notGoodData = true;
				}
				if (roomNumber < 1 && notGoodData == false) {
					System.out.println("Kérem, megfelelő adatot adjon meg!");
					System.out.println();
					notGoodData = true;
				}
			} while (notGoodData);
		} while (goodOrNoDataEntry().equals("u"));
		return roomNumber;
	}

	public double roomSize(String data, boolean height) {
		double value = 0;
		boolean notGoodData = false;
		do {
			notGoodData = false;
			System.out.print(data + " ");
			try {
				value = scan.nextDouble();
				scan.nextLine();
				checkExit(value);
			} catch (InputMismatchException e) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				scan.next();
				notGoodData = true;
			}
			if (value <= 0 && notGoodData == false && height == false) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				notGoodData = true;
			}
			if (value < 0 && notGoodData == false && height == true) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				notGoodData = true;
			}
		} while (notGoodData);
		return value;
	}

	/**
	 * 
	 * @param datas A string array that stores materials.
	 * @return A string array that stores data for 1 selected material from the
	 *         array of materials. This string array stores data for the selected
	 *         material. Index 1 stores the name of the material, index 2 stores the
	 *         price of the pack of material, and index 3 stores the square footage
	 *         of the material per pack.
	 */
	public String[] chooseMaterials(String[] datas) {
		int materialIndex = 0;
		boolean notGoodData = false;
		do {
			notGoodData = false;
			System.out.print("ADJA MEG A KIVÁLASZTOTT ANYAG INDEXÉT: ");
			try {
				materialIndex = scan.nextInt();
				scan.nextLine();
				checkExit(materialIndex);
			} catch (InputMismatchException e) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				System.out.println();
				scan.next();
				notGoodData = true;
			}
			if (materialIndex < 0 || materialIndex > datas.length - 1 && notGoodData == false) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				System.out.println();
				notGoodData = true;
			}
		} while (notGoodData);
		String[] materialDatas = datas[materialIndex].split(";");
		System.out.printf("%s, %s HUF/pack, %s m²/pack%n", materialDatas[0], materialDatas[1], materialDatas[2]);
		return materialDatas;
	}

	double giveDiscount() {
		double discount = 0.0;
		boolean notGoodData = false;
		do {
			notGoodData = false;
			System.out.printf("ADJA MEG A KEDVEZMÉNY MÉRTÉKÉT (%%): ");
			try {
				discount = scan.nextDouble();
				scan.nextLine();
				checkExit(discount);
			} catch (InputMismatchException e) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				System.out.println();
				scan.next();
				notGoodData = true;
			}
			if (discount < 0 || discount > 100 && notGoodData == false) {
				System.out.println("Kérem, megfelelő adatot adjon meg!");
				System.out.println();
				notGoodData = true;
			}
		} while (notGoodData);
		return discount;
	}

	public void checkExit(String value) {
		if (value.equals("-1")) {
			System.out.println();
			System.out.println("Viszontlátásra.");
			System.exit(0);
		}
	}

	public void checkExit(double value) {
		if (value == -1) {
			System.out.println();
			System.out.println("Viszontlátásra.");
			System.exit(0);
		}
	}

	public void checkExit(int value) {
		if (value == -1) {
			System.out.println();
			System.out.println("Viszont látásra.");
			System.exit(0);
		}
	}
}
