package conversationEngineInporter;

import java.util.Iterator;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * this class takes the input file and converts/parses it into a CEStory
 * 
 * @author Sijmen_v_b
 *
 */
public class Generator {

	private int IdCounter = 0; // to ensure unique id's (picked sequentially)

	public CEStory generateStory(JSONArray nodesArray) {
		// System.out.println(nodesArray.get(0));

		HashMap<String, ConverzationNode> nodes = new HashMap<String, ConverzationNode>();//store all conversation nodes with their names as their key.

		@SuppressWarnings("unchecked") // ignore warning
		Iterator<JSONObject> iter = nodesArray.iterator(); // get iterator for all nodes
		while (iter.hasNext()) { // loop over all nodes
			JSONObject obj = (JSONObject) iter.next(); // get current node
			ConverzationNode n = new ConverzationNode(obj, getNewId()); // make node into conversation node
			nodes.put(n.getName(), n);
		}
		
		//print all the commands, for testing purposes.
		for (ConverzationNode i : nodes.values()) {
			System.out.println(i.toCommand());
			System.out.println("new");
		}

		return new CEStory();
	}

	/**
	 * get a new identifier, which is the previous identifier plus one.
	 * 
	 * @return new id as int
	 */
	public int getNewId() {
		IdCounter++; // get to new id
		return IdCounter; // return the new id
	}

}
