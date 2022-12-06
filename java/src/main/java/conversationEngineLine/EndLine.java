package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;

public class EndLine extends ConversationLine {

	public EndLine(ConversationNode node) {
		super(node);
	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con) {
		return String.format("%srun function conversation_engine:messages/%s/end\n", con, npc.getName());
	}

	@Override
	protected String getYarnCommand() {
		return "else";
	}

	@Override
	public ConversationLine tryParseArguments(String[] arguments, ConverzationNode node) {
		if (arguments[0] != getYarnCommand()) {
			return null;
		}
		return new EndLine(node);
	}

}
