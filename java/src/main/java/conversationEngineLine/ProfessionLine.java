package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.Functions;
import conversationEngineImporter.NPC;

public class ProfessionLine extends ConversationLine {

	private String profession;
	
	private String[] professions = { "none", "armorer", "butcher", "cartographer", "cleric", "farmer",
			"fisherman", "fletcher", "leatherworker", "librarian", "mason", "nitwit",
			"shepherd", "toolsmith", "weaponsmith" };
	
	public ProfessionLine(ConverzationNode node) {
		super(node);
	}
	
	

	@Override
	protected String getYarnCommand() {
		return "profession";
	}

	@Override
	public ConversationLine tryParseArguments(String[] arguments, ConverzationNode node) {
		if (arguments[0] != getYarnCommand()) {
			return null;
		}
		if (arguments.length == 2) {
			this.profession = Functions.closestString(professions, arguments[1]);
			return new ProfessionLine(node);
		} else {
			System.err.println("Error " + arguments[0] + " is invalid. example: <<profession|farmer>>");
		}
		return null;
	}

	@Override
	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con) {
		return null;
	}

}
