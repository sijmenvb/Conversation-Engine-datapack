package conversationEngineLine.commandPlugins;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public class BuyLine extends ConversationLine{

	String getItem;
	int getAmmount;
	String payItem;
	int payAmmount;
	
	public BuyLine() {
		super();	
	}

	public BuyLine(String getItem, int getAmmount, String payItem, int payAmmount, ConversationNode node) {
		super();
		this.getItem = getItem;
		this.getAmmount = getAmmount;
		this.payItem = payItem;
		this.payAmmount = payAmmount;
	}

	public String toCommand(HashMap<String, ConversationNode> nodeMap, CEStory ceStory, NPC npc, LinkedList<String> conditionList,
							String currentConditionPrefix, LinkedList<String> tagList) {
		String s =String.format("    scoreboard players set @s CE_buy_count 0\n%srun scoreboard players set @s CE_buy_success 0\n%sif score @s CE_resend matches 0 store result score @s CE_buy_count run clear @s %s 0\n", currentConditionPrefix,currentConditionPrefix,payItem);
		return String.format("%s    execute if score @s CE_buy_count matches %d.. run clear @s %s %d\n    execute if score @s CE_buy_count matches %d.. run give @s %s %d\n    execute if score @s CE_buy_count matches %d.. run scoreboard players set @s CE_buy_success 1\n", s, payAmmount,payItem,payAmmount,payAmmount,getItem,getAmmount,payAmmount);
	}

	public String getNameOfFirstArgument() {
		return "buy";
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		if (arguments.length == 5) {
			int getAmmount;
			int payAmmount;
			try {
				getAmmount = Integer.parseInt(arguments[2]);
				payAmmount = Integer.parseInt(arguments[4]);
			} catch (NumberFormatException e) {
				getAmmount = 1;
				payAmmount = 1;
				System.err.println("Error " + arguments[0]
						+ " 3rd argument should be a number. example: <<buy|carrot|20|diamond|1>>");
			}
			return new BuyLine(arguments[1], getAmmount, arguments[3], payAmmount, node);
		} else {
			System.err.println("Error " + arguments[0]
					+ " is invalid. example: <<buy|carrot|20|diamond|1>> (where you but 20 carrots for 1 diamond)");
		}
		return null;
	}

}
