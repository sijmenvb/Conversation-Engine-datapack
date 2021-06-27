package conversationEngineInporter;

import java.util.LinkedList;

/** the NPC class stores the individual npc's and all the node's of it's conversation.
 * 
 * @author Sijmen_v_b
 *
 */
public class NPC {
	LinkedList<ConverzationNode> nodes = new LinkedList<ConverzationNode>();
	String name;
	
	public NPC(String name) {
		super();
		this.name = name;
	}
	
	public void addNode(ConverzationNode n) {
		nodes.push(n);
	}

}
