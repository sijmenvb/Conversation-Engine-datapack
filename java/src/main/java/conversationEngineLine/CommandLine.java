package conversationEngineLine;

import java.util.HashMap;

import conversationEngineInporter.ConverzationNode;

public class CommandLine extends ConversationLine {

	String command;

	public CommandLine(String command, ConverzationNode node) {
		super(node);
		this.command = command;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes) {

		return String.format("    execute if score bool CE_suc matches 1 run %s", command);
	}

}
