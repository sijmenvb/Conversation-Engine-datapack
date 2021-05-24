package conversationEngineInporter;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) {

		Scanner scan;
		
		JSONParser parser = new JSONParser();
		
		try {
			File input = new File("src/input6.txt");
			scan = new Scanner(input);
		} catch (FileNotFoundException e) {
			scan = new Scanner(System.in);
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		

		System.out.println("poject is set up!");

	}
}