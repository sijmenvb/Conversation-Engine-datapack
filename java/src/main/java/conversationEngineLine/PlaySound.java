package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.NPC;

public class PlaySound extends ConversationLine {

	String command;

	public PlaySound(String command, ConverzationNode node) {
		super(node);
		if(command.charAt(0) == '/') {
			command = command.substring(1);//if the command starts with a / remove it.
		}
		this.command = command;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition, String con) {
		return String.format("%srun BRUH BRUH RBUBH %s\n",con, command);
	}

}
