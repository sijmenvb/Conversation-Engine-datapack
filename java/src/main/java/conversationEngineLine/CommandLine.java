package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.CEStory;
import conversationEngineInporter.ConverzationNode;

public class CommandLine extends ConversationLine {

	String command;

	public CommandLine(String command, ConverzationNode node) {
		super(node);
		this.command = command;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, LinkedList<String> condition, String con) {
		return String.format("%srun %s",con, command);
	}

}
