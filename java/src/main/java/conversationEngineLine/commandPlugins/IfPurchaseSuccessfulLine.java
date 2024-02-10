package conversationEngineLine.commandPlugins;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

import java.util.HashMap;
import java.util.LinkedList;

public class IfPurchaseSuccessfulLine extends IfLine {
	public IfPurchaseSuccessfulLine() {
		super();
	}

	public String toCommand(HashMap<String, ConversationNode> nodeMap, CEStory ceStory, NPC npc, LinkedList<String> conditionList,
							String currentConditionPrefix, LinkedList<String> tagList) {
		int ifId = conditionList.size() - 1;// get the number of if statements at this time (-1 for the standard condition)
		ceStory.setNoNestedIfStatements(ifId); // update the max id (max behavior is defined in the set method)
		conditionList.addLast(String.format("if score @s CE_if_%02d matches 1 ", ifId));
		return String.format(
				"    # if the last purchase was successful\n%sif score @s CE_resend matches 0 run scoreboard players set @s CE_if_%02d 0\n%sif score @s CE_resend matches 0 if score @s CE_buy_success matches 1 run scoreboard players set @s CE_if_%02d 1\n",
				 currentConditionPrefix, ifId, currentConditionPrefix, ifId);
	}

	protected String getIfType() {
		return "PurchaseSuccessful";
	}

	@Override
	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {	
		if (arguments[1].equalsIgnoreCase(getIfType())) {
			return new IfPurchaseSuccessfulLine();
		}
		return null;
	}

}
