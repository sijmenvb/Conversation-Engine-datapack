package conversationEngineInporter;

import java.util.HashMap;
import java.util.LinkedList;

import org.json.simple.JSONObject;

import conversationEngineLine.ConversationLine;
import conversationEngineLine.PointerLine;
import conversationEngineLine.StringLine;

/**
 * this class will hold all the individual nodes of the conversation with
 * references to the nodes connected to this one.
 * 
 * @author Sijmen_v_b
 *
 */
public class ConverzationNode {
	private LinkedList<ConversationLine> lines = new LinkedList<ConversationLine>();

	private int id;
	private String name;
	private LinkedList<String> outPointer = new LinkedList<String>(); // a list of names of nodes this node points to.
	private LinkedList<String> inPointer = new LinkedList<String>(); // a list of names of nodes that point to this
																		// node.

	public ConverzationNode(JSONObject in, int id) {
		this.id = id;
		this.name = ((String) in.get("title"));// make sure there are no spaces in the name.
		String body = (String) in.get("body");
		String lines[] = body.split("(?<=\\r?\\n)|((?=\\[\\[)|(?<=\\]\\]))"); // splits before [[ and after ]] and
																				// next-lines

		for (int i = lines.length - 1; i >= 0; i--) { // go over array backwards (since we want the linkedlist to be in
														// order)
			if (lines[i].matches("\\[\\[([^\\|]*)\\|([^\\|]*)\\]\\]")) { // if the line is in the format of
																			// [[ some text | some text ]]
				PointerLine pointerLine = new PointerLine(lines[i], this);// convert input to PointerLine.
				this.lines.push(pointerLine); // add the pointerLine to the list.
				outPointer.push(pointerLine.getPointer());// update the outPointer list.

			} else if (lines[i].matches("\\[\\[([^\\|]*)\\]\\]")) { // check for pointers without text
				//we might want to make this syntax for going straight to 
				
				//convert the string of type [[text]] to type [[text|text]]. so making the message the same as the text
				String s = lines[i].substring(0, lines[i].length()-2);
				s += "|";
				s += s.substring(2,s.length()-1);
				s += "]]";
				
				PointerLine pointerLine = new PointerLine(s, this);// convert input to PointerLine.
				this.lines.push(pointerLine); // add the pointerLine to the list.
				outPointer.push(pointerLine.getPointer());// update the outPointer list.
			} else { // if it is not in any special syntax treat it as text
				this.lines.push(new StringLine(lines[i], this)); // convert the input to a string line.
			}
		}
	}

	public LinkedList<Integer> getValidInpointerIds(HashMap<String, ConverzationNode> nodes) {
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

	public String toCommand(HashMap<String, ConverzationNode> nodes) {
		String s = "";
		for (int i = 0; i < lines.size(); i++) {
			s += lines.get(i).toCommand(nodes);
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
	
}
