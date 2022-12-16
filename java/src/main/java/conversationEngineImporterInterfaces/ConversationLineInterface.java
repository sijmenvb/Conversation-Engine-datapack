package conversationEngineImporterInterfaces;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public interface ConversationLineInterface {

	// return the YARN command which should match the first argument of the line
	String getYarnCommand();
	
	// try to parse the whole argument list into a ConversationLine
	ConversationLine tryParseArguments(String[] arguments, ConversationNode node);
	
	// transform the ConversationLine to Minecraft commands
	String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con);
	
}
