package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.CEStory;
import conversationEngineInporter.ConverzationNode;
import conversationEngineInporter.Functions;

public class StringLine extends ConversationLine {

	private String text;
	

	public StringLine(String text, ConverzationNode node) {
		super(node);
		this.text = text;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, LinkedList<String> condition, String con) {
		return String.format("%srun tellraw @s [{\"text\":\"%s\",\"color\":\"white\"}]\n",con, Functions.stringEscape(text));
	}
}
