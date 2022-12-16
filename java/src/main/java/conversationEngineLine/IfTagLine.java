package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;

public class IfTagLine extends ConversationLine {

	private String tag;

	public IfTagLine(String tag, ConversationNode node) {
		super(node);
		this.tag = tag;
	}
	//TODO: add comma separated list to test for multiple tags

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition,
			String con) {
		int ifId = condition.size() - 1;// get the number of if statements at this time (-1 for the standard condition)
		ceStory.setNoNestedIfStatements(ifId); // update the max id (max behavior is defined in the set method)
		condition.addLast(String.format("if score @s CE_if_%02d matches 1 ", ifId));
		return String.format(
				"    # if the player has the tag %s\n%sif score @s CE_resend matches 0 run scoreboard players set @s CE_if_%02d 0\n%sif score @s CE_resend matches 0 if entity @s[tag=%s] run scoreboard players set @s CE_if_%02d 1\n",
				tag, con, ifId, con, tag, ifId);
	}

}
