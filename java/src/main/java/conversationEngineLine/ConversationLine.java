package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineImporterInterfaces.*;

/**
 * this is an abstract class to contain the different types of line such as
 * normal text or a pointer to another node.
 * 
 * @author Sijmen_v_b
 *
 */
public abstract class ConversationLine {

	public ConversationLine() {
	}	
	
	// return the YARN command which should match the first argument of the line
	public abstract String getYarnCommand();
		
		// try to parse the whole argument list into a ConversationLine
	public abstract ConversationLine tryParseArguments(String[] arguments, ConversationNode node);
		
		// transform the ConversationLine to Minecraft commands
	public abstract String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
				LinkedList<String> condition, String con);

}
