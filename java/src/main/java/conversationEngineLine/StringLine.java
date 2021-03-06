package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.Functions;
import conversationEngineImporter.NPC;

public class StringLine extends ConversationLine {

	private String text;

	public StringLine(String text, ConverzationNode node) {
		super(node);
		this.text = text;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con) {
		return String.format("%srun tellraw @s [{\"text\":\"%s\",\"color\":\"white\"}]\n", con,
				Functions.stringEscape(text).replace("@s", "\"},{\"selector\":\"@s\"},{\"text\":\"")); // when you type
																										// @s it will
																										// type the name
																										// of the
																										// player.
	}
}
