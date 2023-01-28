package conversationEngineLine.yarncommands;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public class ElseLine extends IfLine {

	public ElseLine() {
		super();
	}
	
	public ElseLine(ConversationNode node) {
		super();
	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> listOfConditions, String currentCondition) {
		if (listOfConditions.size() > 1) {//check if there is a if statement to "else".
			listOfConditions.removeLast();// remove last condition
			int ifId = listOfConditions.size() - 1;// get the number of if statements at this time (-1 for the standard
											// condition)
			listOfConditions.addLast(String.format("if score @s CE_if_%02d matches 0 ", ifId));
		} else {
			System.err.println("<<else>> was used without corresponding if!!");
		}

		return "    # else \n";
	}

	public String getNameOfFirstArgument() {
		return "else";
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		return new ElseLine(node);
	}

}
