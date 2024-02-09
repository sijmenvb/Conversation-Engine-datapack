package conversationEngineLine.commandPlugins;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public abstract class IfLine extends ConversationLine {

	// Helper class for IfLine subclasses
	protected boolean isValidRange(String s) {
		if (s.matches("\\d*\\.\\.\\d*")) {// check if ranges like 5..10 and ..10 and 5..
			return true;
		}
		if (s.matches("\\d*")) {// check for a single number
			return true;
		}
		return false;
	}

	// by default the command is "if", can be overwritten by subclass if command is
	// different (such as "else")
	@Override
	public String getNameOfFirstArgument() {
		return "if";
	}

	@Override
	public abstract ConversationLine tryParseArguments(String[] arguments, ConversationNode node);

	@Override
	public abstract String toCommand(HashMap<String, ConversationNode> nodeMap, CEStory ceStory, NPC npc,
									 LinkedList<String> conditionList, String currentConditionPrefix, LinkedList<String> tagList);

}
