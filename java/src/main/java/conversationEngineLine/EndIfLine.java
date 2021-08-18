package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.CEStory;
import conversationEngineInporter.ConverzationNode;
import conversationEngineInporter.NPC;

public class EndIfLine extends ConversationLine{

	public EndIfLine(ConverzationNode node) {
		super(node);
	}

	
	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition, String con) {
		if (condition.size() > 1) {//check if there is a if statement to end.
			condition.removeLast();// remove last condition
		} else {
			System.err.println("<<endif>> was used without corresponding if!!");
		}

		return "    # end if\n";
	}

}
