package conversationEngineImporter;



import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) {

		// String file = "src/main/resources/examples/basic villager.json"; // get the basic
		// villager
		// String file = "src/main/resources/examples/broken.json"; // load in the file with
		// broken JSON for testing error
		// String file = "src/main/resources/examples/missing node.json"; // file with a
		// reference to a node that does not exist
		// String file = "src/main/resources/examples/shopDialog.json"; // file with [[text]]
		// notation.
		// String file = "src/main/resources/examples/shopDialog-Fixed.json"; // fixed version of
		// the previous.
		// String file = "src/main/resources/examples/farmer+give.json"; // uses <<>> notation
		// for profession and give command.
		// String file = "src/main/resources/examples/command.json"; // uses <<command|some
		// custom command>> notation for profession and give command.
		// String file = "src/main/resources/examples/if.json"; // uses <<if>> , <<else>> and
		// <<endif>> notaton.
		// String file = "src/main/resources/examples/10000.json"; // stress testing
		// String file = "src/main/resources/examples/buy.json"; // usess
		// <<buy|carrot|20|diamond|1>> notation for the attempt at buying items.
		// String file = "src/main/resources/examples/end.json"; // Uses <<end>> notation to end
		// the conversation.
		// String file = "src/main/resources/examples/ifTag.json"; // uses <<if|tag|name of the
		// tag>> notation.
		// String file = "src/main/resources/examples/ifCustom.json"; // uses <<if|custom|some if
		// statement>> notation.
		// String file = "src/main/resources/examples/tag.json"; // uses <<tag|add|some tag>> notation.
		// String file = "src/main/resources/examples/@s.json"; // uses @s in the pointerline notation.
		// String file = "src/main/resources/examples/error.json"; // a file that generates a error send to me by a user.
		// String file = "src/main/resources/examples/long_name.json"; // s file with names longer than 16 characters.
		// String file = "src/main/resources/examples/scoreRanges.json"; // s file with names longer than 16 characters.
		ReadConfig config = new ReadConfig();
		String file = config.getFileName();

		JSONParser parser = new JSONParser();

		JSONArray nodesArray;

		try { // try to get the specified file else throw an error and end the program.
			FileReader input = new FileReader(file); // get the file
			nodesArray = (JSONArray) parser.parse(input); // convert the file to a JSON array.
		} catch (FileNotFoundException e) { // if the file was not found
			System.out.println("could not find \"" + file + "\".\nABORTING");
			return; // stop the program
		} catch (Exception e) { // if there was another error (could not read file, not proper JSON file etc.)
			System.out.println("an unexpected error occurred when reading \"" + file + "\".");
			e.printStackTrace();
			System.out.println("ABORTING");
			return; // stop the program
		}

		System.out.println("importing the file");
		CEStory Story = new Generator().generateStory(nodesArray, 10,config.getDatapackName(),config.isSaveAsZip());

		Story.generateDatapack();

		System.out.println("Done! -- tool provided by sijmen_v_b");
		Functions.debug("DONE!");

	}
	
	
}