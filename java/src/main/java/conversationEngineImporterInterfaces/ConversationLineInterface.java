package conversationEngineImporterInterfaces;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public interface ConversationLineInterface {

	String getYarnCommand();
	
	ConversationLine tryParseArguments(String[] arguments, ConversationNode node);
	
	String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con);
	
}
