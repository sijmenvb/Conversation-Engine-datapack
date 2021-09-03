package conversationEngineImporter;

import java.io.IOException;
import java.util.LinkedList;

/**
 * the NPC class stores the individual npc's and all the node's of it's
 * conversation.
 * 
 * @author Sijmen_v_b
 *
 */
public class NPC {
	private LinkedList<ConverzationNode> nodes = new LinkedList<ConverzationNode>();
	private String name;
	private String profession;
	
	public NPC(String name,String profession) {
		super();
		this.name = name;
		this.profession = profession;
	}

	public void addNode(ConverzationNode n) {
		nodes.push(n);
	}

	public int GetStartingNodeId() {
		for (ConverzationNode converzationNode : nodes) {
			if (converzationNode.isStartingNode()) {
				return converzationNode.getId();
			}
		}

		// if no starting node was found display an error.
		try { // i might still want this method to throw an error instead of immediately
				// catching it.
			throw new IOException(name + " NPC does not have a starting node");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // in theory this should never be run.
	}

	public String getName() {
		return name.toLowerCase().replace(' ', '_');
	}
	
	public String getRealName() {
		return name.replace("_", " ");
	}

	public LinkedList<ConverzationNode> getNodes() {
		return nodes;
	}

	public String getProfession() {
		return profession;
	}

	
}
