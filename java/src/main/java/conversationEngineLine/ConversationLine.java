package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;

/**
 * this is an abstract class to contain the different types of line such as
 * normal text or a pointer to another node.
 * 
 * @author Sijmen_v_b
 *
 */
public abstract class ConversationLine {

	protected ConversationNode node;

	public ConversationLine(ConversationNode node) {
		this.node = node;
	}
	
	protected abstract String getYarnCommand();
	
	public abstract ConversationLine tryParseArguments(String[] arguments, ConverzationNode node);

	public abstract String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
			LinkedList<String> condition, String con);

}
