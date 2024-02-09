package conversationEngineLine.commandPlugins;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public class TagLine extends ConversationLine {

	private boolean remove;
	private String tag;

	public TagLine() {
		super();
	}
	
	public TagLine(boolean unless, String tag, ConversationNode node) {
		super();
		this.remove = unless;
		this.tag = tag;
	}

	public String toCommand(HashMap<String, ConversationNode> nodeMap, CEStory ceStory, NPC npc,
							LinkedList<String> conditionList, String currentConditionPrefix, LinkedList<String> tagList) {
		String add;
		if (remove) {
			add = "remove";
		} else {
			add = "add";
		}
		return String.format("%srun tag @s %s %s\n", currentConditionPrefix, add, tag);
	}

	public String getNameOfFirstArgument() {
		return "tag";
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
//		System.err.println("TagLine");
		if (arguments.length == 3) {
//			System.err.println("3 arguments");
//			System.err.println(arguments[0]);
//			System.err.println(arguments[1]);
//			System.err.println(arguments[2]);
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
