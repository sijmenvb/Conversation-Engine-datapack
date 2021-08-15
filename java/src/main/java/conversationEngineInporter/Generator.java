package conversationEngineInporter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.HashSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * this class takes the input file and converts/parses it into a CEStory
 * 
 * @author Sijmen_v_b
 *
 */
public class Generator {

	private int IdCounter = 0; // to ensure unique id's (picked sequentially) (0 will never be used
								// intentionally)

	public CEStory generateStory(JSONArray nodesArray, int groupSize) {
		// System.out.println(nodesArray.get(0));

		HashMap<String, ConverzationNode> nodes = new HashMap<String, ConverzationNode>();// store all conversation
																							// nodes with their names as
																							// their key.
		Queue<NPC> NPCs = new LinkedList<NPC>(); // store the npc's

		// get all nodes into the nodes hashMap as conversationNodes.
		{
			@SuppressWarnings("unchecked") // ignore warning
			Iterator<JSONObject> iter = nodesArray.iterator(); // get iterator for all nodes
			while (iter.hasNext()) { // loop over all nodes
				JSONObject obj = (JSONObject) iter.next(); // get current node
				ConverzationNode n = new ConverzationNode(obj, getNewId()); // make node into conversation node
				nodes.put(n.getName(), n);
			}
		}

		// now we have all the nodes loaded update all their inPointer lists
		for (ConverzationNode n : nodes.values()) {
			String name = n.getName(); // get the name of the current node
			Iterator<String> iter = n.getOutPointer().iterator(); // get all the nodes this one points to.
			while (iter.hasNext()) { // for all the nodes this node points to
				String s = iter.next();
				try {
					
					nodes.get(s).addInPointer(name); // add the name of this node to the inPonter of the node
																// it
																// points to.
				} catch (NullPointerException e) {
					System.err.println("WARNING! the node " + s + " does not exist!");
				}

			}
		}

		// now to create the villagers we need to get the nodes that no other nodes
		// point to.
		LinkedList<ConverzationNode> startingNodes = new LinkedList<ConverzationNode>();
		for (ConverzationNode n : nodes.values()) {
			if (n.isStartingNode()) {
				startingNodes.push(n);
			}
		}

		// now create the npc's (same name as the starting node and contain all )
		for (ConverzationNode node : startingNodes) {
			HashSet<String> exploredNodes = new HashSet<String>(); // keep track of the nodes
			Queue<String> nodesQueue = new LinkedList<String>(); // keep list of nodes to explore
	
			NPC npc = new NPC(node.getRealName(),node.getProfession()); // create new npc.
			nodesQueue.add(npc.getName()); // add the name(lower case and space is _) to the nodes queue
			
			System.out.println("detected npc named: " + node.getRealName());

			while (!nodesQueue.isEmpty()) {
				String nodeName = nodesQueue.remove(); // get next item from the queue
				if (exploredNodes.add(nodeName)) { // if this node was not already explored
					ConverzationNode n = nodes.get(nodeName);
					if (n == null) {
						System.err.println("WARNING! the node " + nodeName + " does not exist!");
					} else {
						npc.addNode(n); // add this node to the npc.
						for (String s : n.getOutPointer()) { // for all nodes this node points to.
							nodesQueue.add(s); // add the nodes this node points to to the queue
						}
					}

				}
			}
			NPCs.add(npc);// add this npc to the list of npc's
		}

		// put the npc's into groups of groupSize.
		LinkedList<NPCGroup> npcGroups = new LinkedList<NPCGroup>();
		int groupId = 0; // start with a group id of 0
		while (!NPCs.isEmpty()) { // while we still have npc's
			LinkedList<NPC> npcGroup = new LinkedList<NPC>(); // create new group of npc's
			for (int i = 0; i < groupSize; i++) { // repeat group size times.
				if (!NPCs.isEmpty()) { // if we have not run out of npc's
					npcGroup.add(NPCs.remove()); // add the npc to the group
				}
			}
			npcGroups.add(new NPCGroup(npcGroup, groupId)); // add the list of npcs into a npcGroup
			groupId++; // increase the group id
		}

		// now gather the groups into a CEStory
		CEStory story = new CEStory(npcGroups, nodes);

		// print all the commands, for testing purposes.
		for (ConverzationNode n : nodes.values()) {
			System.out.println("new " + n.getRealName());
			System.out.println(n.toCommand(nodes,story,true));
		}

		return story;
	}

	/**
	 * get a new identifier, which is the previous identifier plus one.
	 * 
	 * @return new id as int
	 */
	public int getNewId() {
		IdCounter++; // get to new id (0 will never be used intentionally)
		return IdCounter; // return the new id
	}

}
