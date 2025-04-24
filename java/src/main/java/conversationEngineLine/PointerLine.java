package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.Functions;
import conversationEngineImporter.NPC;

public class PointerLine extends ConversationLine {
	private String text;
	private String pointer;

	private boolean support1_21_5Plus;

	public PointerLine(String text, ConversationNode node, boolean support1_21_5Plus) {
		super();
		String split[] = text.split("\\]\\]|\\[\\[|\\|"); // splits at and removes the following characters [[ and ]]
															// and |
		if (split.length != 3) {// make sure the split does not fail.
			System.err.println(
					String.format("\n\n! ERROR ! the choice \"%s\" in the node \"%s\" has arguments missing!\n\n", text,
							node.getRealName()));
			return;
		}
		this.text = split[1]; // get the text (note that split[0] is an empty string due to the initial split
								// at [[)
		this.pointer = split[2].replace(' ', '_').toLowerCase(); // get the pointer and make sure it has underscores
																	// just as the id's
		this.support1_21_5Plus = support1_21_5Plus;
	}

	public String getPointer() {
		return pointer;
	}

	public String toCommand(HashMap<String, ConversationNode> nodeMap, CEStory ceStory, NPC npc, LinkedList<String> conditionList,
							String currentConditionPrefix, LinkedList<String> tagList) {
		// try to get the id of the node if this node does not exist show an error and
		// use id 0 instead.
		int nodeId = 0;
		try {
			nodeId = nodeMap.get(pointer).getId();
		} catch (NullPointerException e) {
			System.err.println("WARNING: " + /*super.node.getName() +*/ " points to " + pointer + " which does NOT exist");
		}

		String clickevent = String.format(
				",\"color\":\"#A8DFFF\",\""+ (support1_21_5Plus ? "click_event" : "clickEvent") + "\":{\"action\":\"run_command\",\""+(support1_21_5Plus ? "command":"value")+"\":\"/trigger CE_trigger set %d\"}",
				nodeId);

		String playerSelector = String.format("\"%s},{\"selector\":\"@s\"%s},{\"text\":\"", clickevent, clickevent);

		return String.format("%srun tellraw @s [{\"text\":\"%s\"%s}]\n", currentConditionPrefix,
				Functions.stringEscape(text).replace("@s", playerSelector), clickevent);
	}

	public String getNameOfFirstArgument() {
		return null; //only used internally
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		return null;//only used internally
	}

}
