package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.ConverzationNode;

public class IfScoreLine extends ConversationLine{

	private String score;
	private int target;

	public IfScoreLine(String score, int target,ConverzationNode node) {
		super(node);
		this.score = score;
		this.target = target;
	}

	
	public String toCommand(HashMap<String, ConverzationNode> nodes, LinkedList<String> condition) {
		
		return "TODO";
	}

}
