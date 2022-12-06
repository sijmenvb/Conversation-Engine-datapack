package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;

public class TagLine extends ConversationLine {

	private boolean remove;
	private String tag;

	public TagLine(boolean unless, String tag, ConversationNode node) {
		super(node);
		this.remove = unless;
		this.tag = tag;
	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con) {
		String add;
		if (remove) {
			add = "remove";
		} else {
			add = "add";
		}
		return String.format("%srun tag @s %s %s\n", con, add, tag);
	}

	@Override
	protected String getYarnCommand() {
		return "tag";
	}

	@Override
	public ConversationLine tryParseArguments(String[] arguments, ConverzationNode node) {
		if (arguments[0] != getYarnCommand()) {
			return null;
		}
		if (arguments.length == 3) {
			boolean b = false;
			//if the second argument is remove make it a remove tag otherwise assume add.
			if(arguments[1].toLowerCase().equals("remove")) {
				b = true;
			}
			return new TagLine(b,arguments[2],node);
		}else {
			System.err.println("Error " + arguments[0]
					+ " is invalid. example: <<tag|add|name of the tag>>");
		}
		return null;
	}

}
