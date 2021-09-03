package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.NPC;

public class TagLine extends ConversationLine {

	private boolean remove;
	private String tag;

	public TagLine(boolean unless, String tag, ConverzationNode node) {
		super(node);
		this.remove = unless;
		this.tag = tag;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con) {
		String add;
		if (remove) {
			add = "remove";
		} else {
			add = "add";
		}
		return String.format("%srun tag @s %s %s\n", con, add, tag);
	}

}
