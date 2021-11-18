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

	public NPC(String name, String profession) {
		super();
		this.name = name;
		this.profession = profession;
	}

	public void addNode(ConverzationNode n) {
		nodes.push(n);
	}

	public int GetStartingNodeId() {
		// it might be worth wile making this a variable to do lookups after calculating
		// once might be worth it.

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

	/**
	 * this will return the name for use by the code. since mincraft has a character
	 * limit for scoreboards all names will be a maximum of 16 characters long. it
	 * will ensure unique villager names by including the id in the last five
	 * characters if the name is longer than 16 characters.
	 */
	public String getName() {
		if (name.length() > 16) {// Minecraft does not support scoreboards with more than 16 characters.
			return String.format("%11.11s%05d", name.toLowerCase().replace(' ', '_'), GetStartingNodeId());// return a
																											// string of
																											// 11
																											// character
																											// and a int
																											// of five.
		}
		return name.toLowerCase().replace(' ', '_');
	}

	public String getRealName() {
		return name.replace("_", " ");
	}

	public String getTagName() {
		return name.toLowerCase().replace(' ', '_');
	}

	public LinkedList<ConverzationNode> getNodes() {
		return nodes;
	}

	public String getProfession() {
		return profession;
	}

}
