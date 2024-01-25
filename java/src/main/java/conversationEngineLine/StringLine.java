package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.Functions;
import conversationEngineImporter.NPC;

public class StringLine extends ConversationLine {

	private String text;

	public StringLine(String text, ConversationNode node) {
		super();
		this.text = text;
	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> listOfConditions, String currentCondition, LinkedList<String> tags) {
		return String.format("%srun tellraw @s [{\"text\":\"%s\",\"color\":\"white\"}]\n", currentCondition,
				Functions.stringEscape(text).replace("@s", "\"},{\"selector\":\"@s\"},{\"text\":\"")); // when you type
																										// @s it will
																										// type the name
																										// of the
																										// player.
	}

	public String getNameOfFirstArgument() {
		return null; //only used internally
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		return null;//only used internally
	}
}
