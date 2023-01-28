package conversationEngineImporter;

import java.util.HashMap;
import java.util.LinkedList;

import org.json.simple.JSONObject;

import conversationEngineLine.*;

/**
 * this class will hold all the individual nodes of the conversation with
 * references to the nodes connected to this one.
 * 
 * @author Sijmen_v_b
 *
 */
public class ConversationNode {
	public LinkedList<ConversationLine> lines = new LinkedList<ConversationLine>();

	private int id;
	private String name;
	public LinkedList<String> outPointer = new LinkedList<String>(); // a list of names of nodes this node points to.
	public LinkedList<String> inPointer = new LinkedList<String>(); // a list of names of nodes that point to this
																		// node.
	public String profession = "none";
	private int noEmptyLines = 20;

	public ConversationNode(JSONObject in, int id) {
		this.id = id;
		this.name = ((String) in.get("title"));// make sure there are no spaces in the name.
		String body = (String) in.get("body");	
		
		body = Functions.recReplace(body, "\n\n", "\n \n"); // replace to sequential nextlines to have a space until no
															// sequential nextlines are left.	
		
		ConversationNodeJsonParser.parseBody(body, this); // parse the body
	}

	public LinkedList<Integer> getValidInpointerIds(HashMap<String, ConversationNode> nodes) {
		LinkedList<Integer> list = new LinkedList<Integer>();

		// for all the valid input pointers get the node id's
		for (String name : inPointer) {
			list.add(nodes.get(name).getId());
		}

		// if this node is the starting node then 0 is also a valid starting point.
		if (isStartingNode()) {
			list.add(0);
		}

		return list;
	}

	public String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc, Boolean clearchat) {
		String s = "";

		// add the clear chat message.
		if (clearchat) {
			String nexline = "";
			for (int i = 0; i < noEmptyLines; i++) {
				nexline += "\\n";
			}

			s += String.format(
					"    execute if score @s CE_suc matches 1 run tellraw @s [{\"text\":\"%s\",\"color\":\"white\"}]\n",
					nexline);
		}

		LinkedList<String> condition = new LinkedList<String>();
		condition.addLast("    execute if score @s CE_suc matches 1 ");
		for (int i = 0; i < lines.size(); i++) {
			String con = "";

			// if the line is a <<else>> do not apply the last conditional

			for (int j = 0; j < condition.size(); j++) {

				con += condition.get(j);
			}
			s += lines.get(i).toCommand(nodes, ceStory, npc, condition, con);
			
		}

		return s;
	}
	

	public void addInPointer(String name) {
		inPointer.push(name);// update the inPointer list.
	}

	public LinkedList<String> getOutPointer() {
		return outPointer;
	}

	/**
	 * see's if this node has no other nodes pointing to it
	 * 
	 * @return true if this is a starting node
	 */
	public boolean isStartingNode() {
		return inPointer.isEmpty();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name.toLowerCase().replace(" ", "_");
	}

	public String getRealName() {
		return name;
	}

	public String getProfession() {
		return profession;
	}

}
