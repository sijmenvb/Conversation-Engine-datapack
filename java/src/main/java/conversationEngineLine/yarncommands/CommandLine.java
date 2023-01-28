package conversationEngineLine.yarncommands;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public class CommandLine extends ConversationLine {

	String command;
	
	public CommandLine() {
		super();
	}

	public CommandLine(String command, ConversationNode node) {
		super();
		if(command.charAt(0) == '/') {
			command = command.substring(1);//if the command starts with a / remove it.
		}
		this.command = command;
	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition, String con) {
		return String.format("%srun %s\n",con, command);
	}

	public String getYarnCommand() {
		return "command";
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		if (arguments.length == 2) {
			return new CommandLine(arguments[1], node);
		} else {
			System.err.println("Error " + arguments[0]
					+ " is invalid. example: <<command|some custom command>> (use @s to select the player talkign with the npc)");
		}
		return null;
	}

}
