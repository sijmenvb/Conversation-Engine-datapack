package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.ConverzationNode;

public class ElseLine extends ConversationLine {

	public ElseLine(ConverzationNode node) {
		super(node);
		
	}

	
	public String toCommand(HashMap<String, ConverzationNode> nodes, LinkedList<String> condition) {
		
		return "todo";
	}

}
