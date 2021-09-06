package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.Functions;
import conversationEngineImporter.NPC;

public class PointerLine extends ConversationLine {
	private String text;
	private String pointer;

	public PointerLine(String text, ConverzationNode node) {
		super(node);
		String split[] = text.split("\\]\\]|\\[\\[|\\|"); // splits at and removes the following characters [[ and ]]
															// and |
		this.text = split[1]; // get the text (note that split[0] is an empty string due to the initial split
								// at [[)
		this.pointer = split[2].replace(' ', '_').toLowerCase(); // get the pointer and make sure it has underscores
																	// just as the id's
	}

	public String getPointer() {
		return pointer;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition,
			String con) {
		// try to get the id of the node if this node does not exist show an error and
		// use id 0 instead.
		int nodeId = 0;
		try {
			nodeId = nodes.get(pointer).getId();
		} catch (NullPointerException e) {
			System.err.println("WARNING: " + super.node.getName() + " points to " + pointer + " which does NOT exist");
		}
		
		String clickevent = String.format(",\"color\":\"#A8DFFF\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/trigger CE_trigger set %d\"}", nodeId);
		
		String playerSelector = String.format("\"%s},{\"selector\":\"@s\"%s},{\"text\":\"", clickevent,clickevent);

		return String.format(
				"%srun tellraw @s [{\"text\":\"%s\"%s}]\n",
				con, Functions.stringEscape(text).replace("@s", playerSelector), clickevent);
	}
}
