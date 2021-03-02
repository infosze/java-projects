package tables;

import java.util.Scanner;

import fileOperation.Coding;
import fileOperation.FileOperation;
import helpStuffs.IntCheck;
import print.MaterialPrint;

public class Employees {
		private Scanner scan;

		public Employees(Scanner scan) {
			this.scan = scan;

		}
		Coding coder = new Coding();
		IntCheck isInt = new IntCheck(scan);
		FileOperation file = new FileOperation();
		private String[] employees = file.readMaterial("employees");

		public String[] getEmployees() {
			return employees;
		}

		public void delete() {
			MaterialPrint printer = new MaterialPrint();
			printer.print(employees);
			System.out.println("Kérem írja be a törölni kívánt anyag indexét!");

			int i = isInt.checker(employees.length);
			System.out.println("Biztosan törölni szeretné? (i/n)");
			String[] materialDel = employees[i].split(";");
			System.out.println(materialDel[0]);
			String inputLetter;
			do {
				inputLetter = scan.next();
				checkExit(inputLetter);
				System.out.println();
			} while (!inputLetter.equals("i") && !inputLetter.equals("n"));
			if (inputLetter.equals("i")) {
				StringBuilder sb1 = new StringBuilder();
				for (int j = 0; j < i; j++) {
					sb1.append(String.format("%s%n", coder.coder(employees[j], 5)));
				}
				for (int j = i + 1; j < employees.length; j++) {
					sb1.append(String.format("%s%n", coder.coder(employees[j], 5)));
				}
				file.writeTextToFile(sb1.toString().trim(),"employees");
				employees = file.readMaterial("employees");
			}
		}

		public void add() {
			String identity = "";
			System.out.printf("Kérem, adja meg az azonosítót: ");
			identity += scan.next();
			checkExit(identity);
			identity += ";";
			System.out.printf("Kérem, adja meg a vezetéknevét: ");
			identity += scan.next();
			checkExit(identity);
			identity += " ";
			System.out.printf("Kérem, adja meg a keresztnevét: ");
			identity += scan.next();
			checkExit(identity);
			identity += ";";
			System.out.printf("Kérem, adja meg a beosztását: ");
			identity += scan.next();
			checkExit(identity);
			identity += ";";
			System.out.printf("Kérem, adja meg a jelszót: ");
			identity += scan.next();
			checkExit(identity);
			file.fileAppend(identity , "employees");
			employees = file.readMaterial("employees");
		}
		public void checkExit(String value) {
			if(value.equals("-1")) {
				System.out.println("Viszlát");
				System.exit(0);
			}
		}
	}

