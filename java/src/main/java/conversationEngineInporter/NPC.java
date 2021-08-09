package conversationEngineInporter;

import java.io.IOException;
import java.util.LinkedList;

/** the NPC class stores the individual npc's and all the node's of it's conversation.
 * 
 * @author Sijmen_v_b
 *
 */
public class NPC {
	private LinkedList<ConverzationNode> nodes = new LinkedList<ConverzationNode>();
	private String name;
	
	public NPC(String name) {
		super();
		this.name = name;
	}
	
	public void addNode(ConverzationNode n) {
		nodes.push(n);
	}

	public String getName() {
		return name;
	}
	
	public int GetStartingNodeId() {
		for (ConverzationNode converzationNode : nodes) {
			if (converzationNode.isStartingNode()) {
				return converzationNode.getId();
			}
		}
		
		//if no starting node was found display an error.
		try { //i might still want this method to throw an error instead of immediately catching it.
			throw new IOException(name+" NPC does not have a starting node");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //in theory this should never be run.
	}
	
	
}
