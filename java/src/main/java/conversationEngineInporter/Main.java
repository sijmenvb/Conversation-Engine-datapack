package conversationEngineInporter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) {
		
		//String file = "src/main/resources/basic villager.json"; // get the basic villager
		//String file = "src/main/resources/broken.json"; // load in the file with broken JSON for testing error
		String file = "src/main/resources/missing node.json"; // file with a reference to a node that does not exist
		JSONParser parser = new JSONParser();
		
		JSONArray nodesArray;
		
		try { // try to get the specified file else throw an error and end the program.
			FileReader input = new FileReader(file); // get the file
			nodesArray = (JSONArray)parser.parse(input); // convert the file to a JSON array.
		} catch (FileNotFoundException e) {  // if the file was not found
			System.out.println("could not find \"" + file + "\".\nABORTING");
			return;  // stop the program
		} catch (Exception e) {  // if there was another error (could not read file, not proper JSON file etc.)
			System.out.println("an unexpected error occured when reading \"" + file + "\".");
			e.printStackTrace();
			System.out.println("ABORTING");
			return;  // stop the program
		}
		
		System.out.println("importing the file");
		CEStory Story = new Generator().generateStory(nodesArray,10);
		
		Story.generateDatapack();
		

		System.out.println("Done! -- tool provided by sijmen_v_b");

	}
}