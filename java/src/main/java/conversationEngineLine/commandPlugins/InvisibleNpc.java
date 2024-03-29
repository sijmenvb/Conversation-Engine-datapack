package conversationEngineLine.commandPlugins;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public class InvisibleNpc extends ConversationLine{

	private static String tagName = "CE_invisible";
	
	public InvisibleNpc() {
		super();
	}	

	public String getNameOfFirstArgument() {
		return "invisible";
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		
		return new InvisibleNpc();
	}

	@Override
	public String toCommand(HashMap<String, ConversationNode> nodeMap, CEStory ceStory, NPC npc,
							LinkedList<String> conditionList, String currentConditionPrefix, LinkedList<String> tagList) {
		if (!tagList.contains(tagName)) {
			tagList.add(tagName);
		}
		return ""; // empty command because we only set a tag
	}

}