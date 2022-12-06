package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.NPC;

public class CommandLine extends ConversationLine {

	String command;

	public CommandLine(String command, ConverzationNode node) {
		super(node);
		if(command.charAt(0) == '/') {
			command = command.substring(1);//if the command starts with a / remove it.
		}
		this.command = command;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition, String con) {
		return String.format("%srun %s\n",con, command);
	}

	@Override
	protected String getYarnCommand() {
		return "command";
	}

	@Override
	public ConversationLine tryParseArguments(String[] arguments, ConverzationNode node) {
		if (arguments[0] != getYarnCommand()) {
			return null;
		}
		
		if (arguments.length == 2) {
			return new CommandLine(arguments[1], node);
		} else {
			System.err.println("Error " + arguments[0]
					+ " is invalid. example: <<command|some custom command>> (use @s to select the player talkign with the npc)");
		}
		return null;
	}

}
