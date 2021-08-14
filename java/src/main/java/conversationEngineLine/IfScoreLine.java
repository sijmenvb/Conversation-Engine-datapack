package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.ConverzationNode;

public class IfScoreLine extends ConversationLine{

	public IfScoreLine(ConverzationNode node) {
		super(node);
		
	}

	@Override
	public String toCommand(HashMap<String, ConverzationNode> nodes, LinkedList<String> condition) {
		return null;
	}

}
