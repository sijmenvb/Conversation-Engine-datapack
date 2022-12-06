package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.NPC;

public interface ConversationLineInterface {

	ConversationLine tryParseArguments();
	
	String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con);
	
}
