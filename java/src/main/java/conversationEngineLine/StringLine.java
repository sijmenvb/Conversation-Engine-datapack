package conversationEngineLine;

import java.util.HashMap;

import conversationEngineInporter.ConverzationNode;
import conversationEngineInporter.Functions;

public class StringLine extends ConversationLine {

	private String text;
	

	public StringLine(String text, ConverzationNode node) {
		super(node);
		this.text = text;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes) {
		return String.format("    execute if score bool CE_suc matches 1 run tellraw @s [{\"text\":\"%s\",\"color\":\"white\"}]\n", Functions.stringEscape(text));
	}

}
