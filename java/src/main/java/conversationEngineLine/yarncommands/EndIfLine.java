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

	
	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition, String con) {
		if (condition.size() > 1) {//check if there is a if statement to end.
			condition.removeLast();// remove last condition
		} else {
			System.err.println("<<endif>> was used without corresponding if!!");
		}

		return "    # end if\n";
	}

	@Override
	public String getYarnCommand() {
		return "endif";
	}


	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		return new EndIfLine(node);
	}

}
