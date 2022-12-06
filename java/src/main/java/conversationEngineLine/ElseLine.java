package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;

public class ElseLine extends ConversationLine {

	public ElseLine(ConversationNode node) {
		super(node);

	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition, String con) {
		if (condition.size() > 1) {//check if there is a if statement to "else".
			condition.removeLast();// remove last condition
			int ifId = condition.size() - 1;// get the number of if statements at this time (-1 for the standard
											// condition)
			condition.addLast(String.format("if score @s CE_if_%02d matches 0 ", ifId));
		} else {
			System.err.println("<<else>> was used without corresponding if!!");
		}

		return "    # else \n";
	}

	@Override
	protected String getYarnCommand() {
		return "else";
	}

	@Override
	public ConversationLine tryParseArguments(String[] arguments, ConverzationNode node) {
		return new ElseLine(node);
	}

}
