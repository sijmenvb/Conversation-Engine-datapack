package conversationEngineImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Main {

	public static void main(String[] args) {

		// String file = "src/main/resources/basic villager.json"; // get the basic
		// villager
		// String file = "src/main/resources/broken.json"; // load in the file with
		// broken JSON for testing error
		// String file = "src/main/resources/missing node.json"; // file with a
		// reference to a node that does not exist
		// String file = "src/main/resources/shopDialog.json"; // file with [[text]]
		// notation.
		// String file = "src/main/resources/shopDialog-Fixed.json"; // fixed version of
		// the previous.
		// String file = "src/main/resources/farmer+give.json"; // uses <<>> notation
		// for profession and give command.
		// String file = "src/main/resources/command.json"; // uses <<command|some
		// custom command>> notation for profession and give command.
		// String file = "src/main/resources/if.json"; // uses <<if>> , <<else>> and
		// <<endif>> notaton.
		// String file = "src/main/resources/10000.json"; // stress testing
		// String file = "src/main/resources/buy.json"; // usess
		// <<buy|carrot|20|diamond|1>> notation for the attempt at buying items.
		// String file = "src/main/resources/end.json"; // Uses <<end>> notation to end
		// the conversation.
		// String file = "src/main/resources/ifTag.json"; // uses <<if|tag|name of the
		// tag>> notation.
		// String file = "src/main/resources/ifCustom.json"; // uses <<if|custom|some if
		// statement>> notation.
		// String file = "src/main/resources/tag.json"; // uses <<tag|add|some tag>> notation.
		// String file = "src/main/resources/@s.json"; // uses @s in the pointerline notation.
		// String file = "src/main/resources/error.json"; // a file that generates a error send to me by a user.
		String file = "src/main/resources/long_name.json"; // s file with names longer than 16 characters.
		ReadConfig config = new ReadConfig();
		//String file = config.getFileName();

		JSONParser parser = new JSONParser();

		JSONArray nodesArray;

		try { // try to get the specified file else throw an error and end the program.
			FileReader input = new FileReader(file); // get the file
			nodesArray = (JSONArray) parser.parse(input); // convert the file to a JSON array.
		} catch (FileNotFoundException e) { // if the file was not found
			System.out.println("could not find \"" + file + "\".\nABORTING");
			return; // stop the program
		} catch (Exception e) { // if there was another error (could not read file, not proper JSON file etc.)
			System.out.println("an unexpected error occured when reading \"" + file + "\".");
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