package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.NPC;

public class EndLine extends ConversationLine {

	public EndLine(ConverzationNode node) {
		super(node);
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con) {
		return String.format("%srun function conversation_engine:messages/%s/end\n", con, npc.getName());
	}

}
