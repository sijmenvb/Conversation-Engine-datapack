package conversationEngineLine;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;

import java.util.HashMap;
import java.util.LinkedList;

public class IsNpc extends ConversationLine{


	public IsNpc() {
		super();
	}	

	public String getNameOfFirstArgument() {
		return "npc";
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		return new IsNpc();
	}

	@Override
	public String toCommand(HashMap<String, ConversationNode> nodeMap, CEStory ceStory, NPC npc,
							LinkedList<String> conditionList, String currentConditionPrefix, LinkedList<String> tagList) {

		return ""; // empty command because we only check if this exact object exists.
	}

}