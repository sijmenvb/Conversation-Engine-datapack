package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.ConverzationNode;

public class GiveLine extends ConversationLine {
	private String item;
	private int ammount;

	public GiveLine(String item, int ammount, ConverzationNode node) {
		super(node);
		this.item = item;
		this.ammount = ammount;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, LinkedList<String> condition) {
		return String.format("if score bool CE_resend matches 0 run give @s %s %d", item, ammount);
	}

}
