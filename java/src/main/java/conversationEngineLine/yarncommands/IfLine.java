package conversationEngineLine.yarncommands;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineImporterInterfaces.ConversationLineInterface;
import conversationEngineLine.ConversationLine;

public abstract class IfLine extends ConversationLine implements ConversationLineInterface {
			
	protected boolean isValidRange(String s) {
		if (s.matches("\\d*\\.\\.\\d*")) {//check if ranges like 5..10 and ..10 and 5..
			return true;
		}
		if (s.matches("\\d*")) {//check for a single number
			return true;
		}
		return false;
	}
	
	@Override
	public String getYarnCommand() {
		return "if";
	}

	@Override
	public abstract ConversationLine tryParseArguments(String[] arguments, ConversationNode node);

	@Override
	public abstract String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con);


}
