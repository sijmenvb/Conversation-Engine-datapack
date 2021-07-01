package conversationEngineInporter;

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
	
	public String createStartFunction() {
		return  "# TODO generate start function";
	}
	
	public String createEndFunction() {
		return  "# TODO generate end function";
	}
	
	public String createTickFunction() {
		return  "# TODO generate Tick function";
	}
	
	public String createNodeFunctions() {
		return  "# TODO generate Tick function";
	}

}
