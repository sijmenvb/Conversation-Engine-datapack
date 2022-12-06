package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.NPC;

public abstract class IfLine extends ConversationLine {

	public IfLine(ConverzationNode node) {
		super(node);
	}
	
	protected abstract String getIfType();
	
	@Override
	protected String getYarnCommand() {
		return "if";
	}
	
	protected boolean isValidRange(String s) {
		if (s.matches("\\d*\\.\\.\\d*")) {//check if ranges like 5..10 and ..10 and 5..
			return true;
		}
		if (s.matches("\\d*")) {//check for a single number
			return true;
		}
		return false;
	}

}
