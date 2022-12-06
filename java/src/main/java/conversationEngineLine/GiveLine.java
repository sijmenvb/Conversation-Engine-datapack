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

	@Override
	protected String getYarnCommand() {
		return "give";
	}

	@Override
	public ConversationLine tryParseArguments(String[] arguments, ConverzationNode node) {
		if (arguments[0] != getYarnCommand()) {
			return null;
		}
		
		String item;
		int ammount;
		if (arguments.length == 2) {
			item = arguments[1];
			ammount = 1;
			return new GiveLine(item, ammount, node);
		} else if (arguments.length == 3) {
			item = arguments[1];
			try {
				ammount = Integer.parseInt(arguments[2]);
			} catch (NumberFormatException e) {
				ammount = 1;
				System.err.println("Error " + arguments[0]
						+ " 3rd argument should be a number. example: <<cooked_beef|4>>");
				return null;
			}
			return new GiveLine(item, ammount, node);
		} else {
			System.err.println("Error " + arguments[0] + " is invalid. example: <<give|cooked_beef|4>>");
			return null;
		}
	}
	
	
	

}
