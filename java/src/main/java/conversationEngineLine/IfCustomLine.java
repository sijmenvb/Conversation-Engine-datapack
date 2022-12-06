package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.NPC;

public class IfCustomLine extends IfLine {

	private String ifStatement;

	public IfCustomLine(String ifStatement, ConverzationNode node) {
		super(node);
		// make sure the string is long enough
		if (ifStatement.length() >= 4) {
			// if the string starts with a space remove it.
			if (ifStatement.charAt(0) == ' ') {
				ifStatement = ifStatement.substring(1);
			}
			// if it starts with if remove it.
			if (ifStatement.charAt(0) == 'i' && ifStatement.charAt(1) == 'f') {
				ifStatement = ifStatement.substring(3);// remove "if " (including the space)
			}
		}
		this.ifStatement = ifStatement;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con) {
		int ifId = condition.size() - 1;// get the number of if statements at this time (-1 for the standard condition)
		ceStory.setNoNestedIfStatements(ifId); // update the max id (max behaviour is defined in the set method)

		condition.addLast(String.format("if score @s CE_if_%02d matches 1 ", ifId));
		return String.format(
				"    # a custom if statement:\n%sif score @s CE_resend matches 0 run scoreboard players set @s CE_if_%02d 0\n%sif score @s CE_resend matches 0 if %s run scoreboard players set @s CE_if_%02d 1\n",
				con, ifId, con, ifStatement, ifId);
	}

	@Override
	protected String getIfType() {
		return "custom";
	}

	@Override
	public ConversationLine tryParseArguments(String[] arguments, ConverzationNode node) {
		if (arguments[0] != getYarnCommand()) {
			return null;
		}
		
		if (arguments.length == 3) {
			if (arguments[1].toLowerCase().equals(getIfType())) {
					return new IfCustomLine(arguments[2], node);
			} else {
				System.err
						.println("Error " + arguments[0] + " is invalid. example: <<if|custom|some selector>>");
			}
		}
		return null;
	}
}
