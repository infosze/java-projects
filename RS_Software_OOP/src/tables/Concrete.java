package tables;


import java.util.Scanner;

import fileOperation.Coding;
import fileOperation.FileOperation;
import helpStuffs.IntCheck;
import print.MaterialPrint;

public class Concrete {
	private Scanner scan;

	public Concrete(Scanner scan) {
		this.scan = scan;

	}
	Coding coder = new Coding();
	IntCheck intCheck = new IntCheck(scan);
	FileOperation file = new FileOperation();
	private String[] concrete = file.readMaterial("concrete");

	public String[] getConcrete() {
		return concrete;
	}

	public void delete() {
		MaterialPrint printer = new MaterialPrint();
		printer.print(concrete);
		System.out.println("Kérem írja be a törölni kívánt anyag indexét!");
		int i = intCheck.checker(concrete.length);
		System.out.println("Biztosan törölni szeretné? (i/n)");
		String[] materialDel = concrete[i].split(";");
		System.out.println(materialDel[0]);
		String inputLetter;
		do {
			inputLetter = scan.next();
			checkExit(inputLetter);
			System.out.println();
		} while (!inputLetter.equals("i") && !inputLetter.equals("i"));
		if (inputLetter.equals("i")) {
			StringBuilder sb1 = new StringBuilder();
			for (int j = 0; j < i; j++) {
				sb1.append(String.format("%s%n", coder.coder(concrete[j], 5)));
			}
			for (int j = i + 1; j < concrete.length; j++) {
				sb1.append(String.format("%s%n", coder.coder(concrete[j], 5)));
			}
			file.writeTextToFile(sb1.toString().trim(),"concrete");
			concrete = file.readMaterial("concrete");
		}
	}

	public void add() {
		String input = "";
		System.out.printf("Kérem, adja meg az anyag nevét: ");
		scan.nextLine();
		input += scan.nextLine();
		checkExit(input);
		input += ";";
		System.out.printf("Kérem, adja meg az anyag árát: ");
		input += Integer.toString(intCheck.checker(Integer.MAX_VALUE));
		input += ";";
		System.out.printf("Kérem, adja meg a m2/pack: ");
		input += Integer.toString(intCheck.checker(Integer.MAX_VALUE));
		file.fileAppend(input , "concrete");
		concrete = file.readMaterial("concrete");
	}
	public void checkExit(String value) {
		if(value.equals("-1")) {
			System.out.println("Viszlát");
			System.exit(0);
		}
	}
}
