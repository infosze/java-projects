package helpStuffs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IntCheck {
	Scanner scan;
	public IntCheck(Scanner scan) {
		this.scan = scan;
	}
	
	public int checker(int max) {
		int ident = -2;
		do {
			scan = new Scanner(System.in);
			try {
				ident = scan.nextInt();
			} catch (InputMismatchException ex) {
			}
			if(ident == -1) {
				System.out.println("Viszlát!");
				System.exit(0);
			}
			if (ident < 0 || ident >= max) {
				System.out.println("Kérem,írjon be egy számot!");
				System.out.print("Érték: ");
			}
		} while (ident < 0 || ident >= max);
		return ident;

	}

}
