package hangman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class provides functions for reading text files line by line.
 */
public class FileReader {
	
	/**
	 * Reads all the lines in a text file.
	 * All lines are trimmed of whitespace.
	 * Blank lines are ignored.
	 * @param filename a text
	 * @return a list with one line of text per entry
	 */
	public static ArrayList<String> readLines(String filename) {
		ArrayList<String> lines = new ArrayList<String>();
		String charsetName = "UTF-8";
		Scanner scanner;

		try {
			scanner = new Scanner(new FileInputStream(filename), charsetName);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		try {
			while (scanner.hasNextLine()){
				String line = scanner.nextLine();
				line = line.trim();
				// skip blank lines
				if (line.length()==0) {
					continue;
				}
				lines.add(line.toUpperCase());
			}
		} finally {
			scanner.close();
		}
		return lines;		
	}
	
	/**
	 * Reads all the lines in a text file.
	 * All lines are trimmed of whitespace.
	 * Blank lines are ignored.
	 * @param filename a text
	 * @return a list with one line of text per entry
	 */
	public static ArrayList<char[]> readLinesAsArrays(String filename) {
		ArrayList<char[]> lines = new ArrayList<char[]>();
		String charsetName = "UTF-8";
		Scanner scanner;

		try {
			scanner = new Scanner(new FileInputStream(filename), charsetName);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		try {
			while (scanner.hasNextLine()){
				String line = scanner.nextLine();
				line = line.trim();
				// skip blank lines
				if (line.length()==0) {
					continue;
				}				
				lines.add(line.toUpperCase().toCharArray());
			}
		} finally {
			scanner.close();
		}
		return lines;		
	}	
	
}