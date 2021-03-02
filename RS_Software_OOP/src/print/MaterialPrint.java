package print;

public class MaterialPrint {

	public void print(String[] material) {
		System.out.println("Index|               Név│            Ár │       m²/pack│");
		System.out.println("─────┼──────────────────┼───────────────┼──────────────┼");
		for (int i = 0; i < material.length; i++) {
			String[] temp = material[i].split(";");
			System.out.printf("%5d|%18s|%11s HUF|%14s|%n", i, temp[0], temp[1], temp[2]);
			System.out.println("─────┼──────────────────┼───────────────┼──────────────┼");
		}
	}
	
	
}
