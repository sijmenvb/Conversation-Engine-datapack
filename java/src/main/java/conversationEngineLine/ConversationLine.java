package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineInporter.CEStory;
import conversationEngineInporter.ConverzationNode;

/**this is an abstract class to contain the different types of line such as normal text or a pointer to another node.
 * 
 * @author Sijmen_v_b
 *
 */
public abstract class ConversationLine {
	
	protected ConverzationNode node;
	
	public ConversationLine(ConverzationNode node) {
		this.node = node;
	}


	public abstract String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, LinkedList<String> condition, String con);	

}
