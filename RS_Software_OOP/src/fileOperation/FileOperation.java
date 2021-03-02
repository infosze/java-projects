package fileOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOperation {
	Coding coder = new Coding();

	public void writeTextToFile(String text, String file) {
		try {
			String fileName = file + ".txt";
			Files.writeString(Paths.get(fileName), text, StandardCharsets.UTF_8);
		} catch (Exception ex) {
			System.err.println("Hiba történt a fájl írásakor: " + ex.getMessage());
		}
	}

	public void fileAppend(String str, String file) {
		String fileName = file + ".txt";
		try {
			// Open given file in append mode.
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
			out.newLine(); // create line break
			out.write(coder.coder(str, 5)); // append the string to file
			out.close(); // close the file.
		} catch (IOException e) {
			System.out.println("File is missing, or can't be read." + e);
		}
	}

	public String[] readMaterial(String file) {
		int lines = getLines(file);
		String fileName = file + ".txt";
		try {
			String [] materialArray = new String[lines];
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			for (int i = 0; i < materialArray.length; i++) {
				materialArray[i] = reader.readLine(); // Read lines
				materialArray[i] = coder.decoder(materialArray[i], 5);
			}
			reader.close();
			return materialArray;

		} catch (Exception e) {
		}
		return null;

	}

	public int getLines(String file) {
		String fileName = file + ".txt";
		int lines = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			while (reader.readLine() != null) {
				lines++;
			}
			reader.close();
			return lines;
		} catch (IOException e) {
			System.out.println("File is missing, or can't be read." + e);
		}
		return 0;
	}

}
