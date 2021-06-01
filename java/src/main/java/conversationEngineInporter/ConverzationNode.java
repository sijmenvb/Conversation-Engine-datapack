package conversationEngineInporter;

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
	LinkedList<ConversationLine> lines = new LinkedList<ConversationLine>();
	int id;
	String name;
	LinkedList<String> OutPointer;
	LinkedList<String> InPointer;

	public ConverzationNode(JSONObject in, int id) {
		this.id = id;
		this.name = (String) in.get("title");
		String body = (String) in.get("body");
		String lines[] = body.split("(?<=\\r?\\n)|((?=\\[\\[)|(?<=\\]\\]))"); // splits before [[ and after ]] and
																				// next-lines

		for (int i = lines.length-1; i >= 0; i--) {
			if (lines[i].matches("\\[\\[([^\\|]*)\\|([^\\|]*)\\]\\]")) { // if the line is in the format of 
																			// [[ some text | some text ]]
				this.lines.push(new PointerLine(lines[i], this)); // convert input to PointerLine.
			} else { // it it is not in any special syntax treat it as text
				this.lines.push(new StringLine(lines[i], this));
			}
		}
	}
	
	public String toCommand() {
		String s = "";
		for (int i = 0; i < lines.size(); i++) {
			s += lines.get(i).toCommand() + "\n";
		}
		
		return s;
	}

	public int getId() {
		return id;
	}

}
