package conversationEngineLine.yarncommands;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.Functions;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public class ProfessionLine extends ConversationLine{

	private String profession;
	
	private String[] professions = { "none", "armorer", "butcher", "cartographer", "cleric", "farmer",
			"fisherman", "fletcher", "leatherworker", "librarian", "mason", "nitwit",
			"shepherd", "toolsmith", "weaponsmith" };
	
	public ProfessionLine() {
		super();
	}
	
	public ProfessionLine(ConversationNode node) {
		super();
	}	

	public String getNameOfFirstArgument() {
		return "profession";
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		if (arguments.length == 2) {
			this.profession = Functions.closestString(professions, arguments[1]);
			node.profession = this.profession;
			return new ProfessionLine(node);
		} else {
			System.err.println("Error " + arguments[0] + " is invalid. example: <<profession|farmer>>");
		}
		return null;
	}

	@Override
	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> listOfConditions, String currentCondition, LinkedList<String> tags) {
		return ""; // empty command because the profession not set using commands
	}

}
