package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.ConverzationNode;
import conversationEngineInporter.Functions;

public class StringLine extends ConversationLine {

	private String text;
	

	public StringLine(String text, ConverzationNode node) {
		super(node);
		this.text = text;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, LinkedList<String> condition) {
		return String.format("run tellraw @s [{\"text\":\"%s\",\"color\":\"white\"}]\n", Functions.stringEscape(text));
	}
}
