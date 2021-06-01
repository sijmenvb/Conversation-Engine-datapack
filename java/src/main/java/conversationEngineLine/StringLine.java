package conversationEngineLine;

import conversationEngineInporter.ConverzationNode;

public class StringLine extends ConversationLine {

	String text;

	public StringLine(String text, ConverzationNode node) {
		super(node);
		this.text = text;
	}

	public String toCommand() {
		return "# TODO STRING TELRAW";
	}

}
