package conversationEngineLine.commandPlugins;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public class IfScoreLine extends IfLine {

	private String score;
	private String target;

	public IfScoreLine() {
		super();
	}
	
	public IfScoreLine(String score, String target, ConversationNode node) {
		super();
		this.score = score;
		this.target = target;
	}

	public String toCommand(HashMap<String, ConversationNode> nodeMap, CEStory ceStory, NPC npc, LinkedList<String> conditionList,
							String currentConditionPrefix, LinkedList<String> tagList) {
		int ifId = conditionList.size() - 1;// get the number of if statements at this time (-1 for the standard condition)
		ceStory.setNoNestedIfStatements(ifId); // update the max id (max behaviour is defined in the set method)
		conditionList.addLast(String.format("if score @s CE_if_%02d matches 1 ", ifId));
		return String.format(
				"    # if %s is %s\n%sif score @s CE_resend matches 0 run scoreboard players set @s CE_if_%02d 0\n%sif score @s CE_resend matches 0 if score @s %s matches %s run scoreboard players set @s CE_if_%02d 1\n",
				score, target, currentConditionPrefix, ifId, currentConditionPrefix, score, target, ifId);
	}

	protected String getIfType() {
		return "score";
	}

	@Override
	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {		
		if (arguments[1].equalsIgnoreCase(getIfType())) {
			if (arguments.length == 4) {
				String target = arguments[3];
				if (!isValidRange(target)) { 
					target = "1";
					System.err.println("Error " + arguments[0]
							+ " 4th argument should be a range. example: <<if|score|name of score|..5>>");
				}
				return new IfScoreLine(arguments[2], target, node);
			} else {
				System.err.println("Error " + arguments[0]
						+ " is invalid. example: <<if|score|name of score|target score>> ");
			}	
		}  
		return null;
	}

}
