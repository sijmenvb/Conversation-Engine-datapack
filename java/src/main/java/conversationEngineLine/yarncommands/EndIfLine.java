package conversationEngineLine.yarncommands;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public class EndIfLine extends IfLine {

	public EndIfLine() {
		super();
	}
	
	public EndIfLine(ConversationNode node) {
		super();
	}

	
	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> listOfConditions, String currentCondition) {
		if (listOfConditions.size() > 1) {//check if there is a if statement to end.
			listOfConditions.removeLast();// remove last condition
		} else {
			System.err.println("<<endif>> was used without corresponding if!!");
		}

		return "    # end if\n";
	}

	@Override
	public String getNameOfFirstArgument() {
		return "endif";
	}


	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		return new EndIfLine(node);
	}

}
