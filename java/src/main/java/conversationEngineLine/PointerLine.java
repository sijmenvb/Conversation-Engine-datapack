package conversationEngineLine;

import java.util.HashMap;

import conversationEngineInporter.ConverzationNode;
import conversationEngineInporter.Functions;

public class PointerLine extends ConversationLine {
	private String text;
	private String pointer;

	public PointerLine(String text, ConverzationNode node) {
		super(node);
		String split[] = text.split("\\]\\]|\\[\\[|\\|"); // splits at and removes the following characters [[ and ]]
															// and |
		this.text = split[1]; // get the text (note that split[0] is an empty string due to the initial split
								// at [[)
		this.pointer = split[2]; // get the pointer
		System.out.println();
	}

	public String getPointer() {
		return pointer;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes) {
		// try to get the id of the node if this node does not exist show an error and
		// use id 0 instead.
		int nodeId = 0;
		try {
			nodeId = nodes.get(pointer).getId();
		} catch (NullPointerException e) {
			System.err.println("WARNING: " + super.node.getName() + " points to " + pointer + " which does NOT exist");
		}

		return String.format(
				"    execute if score bool CE_suc matches 1 run tellraw @s [{\"text\":\"%s\",\"color\":\"#A8DFFF\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/trigger CE_trigger set %d\"}}]\n",
				Functions.stringEscape(text), nodeId);
	}
}
