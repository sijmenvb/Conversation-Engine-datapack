package conversationEngine;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {

		Scanner scan;
		
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