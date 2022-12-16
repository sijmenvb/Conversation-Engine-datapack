package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;

public class CommandLine extends ConversationLine {

	String command;

	public CommandLine(String command, ConversationNode node) {
		super(node);
		if(command.charAt(0) == '/') {
			command = command.substring(1);//if the command starts with a / remove it.
		}
		this.command = command;
	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition, String con) {
		return String.format("%srun %s\n",con, command);
	}

}
