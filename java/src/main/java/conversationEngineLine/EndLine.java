package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineLine.ConversationLine;

public class EndLine extends ConversationLine {
	
	public EndLine() {
		super();
	}

	public EndLine(ConversationNode node) {
		super();
	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> listOfConditions, String currentCondition) {
		return String.format("%srun function conversation_engine:messages/%s/end\n", currentCondition, npc.getName());
	}

	public String getNameOfFirstArgument() {
		return "end";
	}

	public ConversationLine tryParseArguments(String[] arguments, ConversationNode node) {
		return new EndLine(node);
	}

}
