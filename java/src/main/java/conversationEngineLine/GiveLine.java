package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;

public class GiveLine extends ConversationLine {
	private String item;
	private int ammount;

	public GiveLine(String item, int ammount, ConversationNode node) {
		super(node);
		this.item = item;
		this.ammount = ammount;
	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition,
			String con) {
		return String.format("%sif score @s CE_resend matches 0 run give @s %s %d\n", con, item, ammount);
	}

}
