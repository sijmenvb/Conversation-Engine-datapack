package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.CEStory;
import conversationEngineInporter.ConverzationNode;

public class BuyLine extends ConversationLine {

	String getItem;
	int getAmmount;
	String payItem;
	int giveAmmount;

	public BuyLine(String getItem, int getAmmount, String payItem, int giveAmmount, ConverzationNode node) {
		super(node);
		this.getItem = getItem;
		this.getAmmount = getAmmount;
		this.payItem = payItem;
		this.giveAmmount = giveAmmount;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, LinkedList<String> condition,
			String con) {
		return null;
	}

}
