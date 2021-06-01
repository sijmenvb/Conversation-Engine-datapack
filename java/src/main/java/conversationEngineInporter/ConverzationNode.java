package conversationEngineInporter;

import java.util.LinkedList;

import org.json.simple.JSONObject;

import conversationEngineLine.ConversationLine;
import conversationEngineLine.PointerLine;

/**
 * this class will hold all the individual nodes of the conversation with
 * references to the nodes connected to this one.
 * 
 * @author Sijmen_v_b
 *
 */
public class ConverzationNode {
	LinkedList<ConversationLine> lines = new LinkedList<ConversationLine>();
	int id;
	String name;
	LinkedList<String> OutPointer;
	LinkedList<String> InPointer;

	public ConverzationNode(JSONObject in, int id) {
		this.id = id;
		this.name = (String) in.get("title");
		String body = (String) in.get("body");
		String lines[] = body.split("(?<=\\r?\\n)|((?=\\[\\[)|(?<=\\]\\]))"); // splits before [[ and after ]] and next-lines
		
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].matches("\\[\\[([^\\|]*)\\|([^\\|]*)\\]\\]")) {
				this.lines.push(new PointerLine(lines[i],this)); 				
			}
		}
	}

	public int getId() {
		return id;
	}
	
	

}
