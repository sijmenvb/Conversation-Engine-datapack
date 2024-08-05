package conversationEngineImporter;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

import conversationEngineLine.IsNpc;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import conversationEngineLine.ConversationLine;
import conversationEngineLine.ConversationNodeJsonParser;
import conversationEngineLine.EndLine;


public class Main {

	public static void main(String[] args) {
		loadConversationLinePlugins();
		
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
		CEStory Story = new Generator().generateStory(nodesArray, 10,config.getDatapackName(),config.isSaveAsZip(),config.isSupport1_21Plus());

		Story.generateDatapack();

		System.out.println("Done! -- tool provided by sijmen_v_b");
		Functions.debug("DONE!");

	}
	
	private static void loadConversationLinePlugins() {
		File pluginFolder = new File("Plugins");
		pluginFolder.mkdir();	
		LinkedList<ConversationLine> list = PluginLoader.loadClasses(pluginFolder, ConversationLine.class);
		list.push(new EndLine());
		list.push(new IsNpc());
		ConversationNodeJsonParser.linetypes = list;
	}
	
	
}