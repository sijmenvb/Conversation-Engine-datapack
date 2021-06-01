package conversationEngineLine;

import conversationEngineInporter.ConverzationNode;

public class PointerLine extends ConversationLine{
	String text;
	String pointer;
	

	public PointerLine(String text,ConverzationNode node) {
		super(node);
		String split[] = text.split("\\]\\]|\\[\\[|\\|"); // splits at and removes the following characters [[ and ]] and |  
		this.text = split[1]; // get the text (note that split[0] is an empty string due to the initial split at [[)
		this.pointer = split[2]; // get the pointer
		System.out.println();
	}
	
	public String getPointer() {
		return pointer;
	}



	public String toCommand() {
		return "# TODO clickable pointer text";
	}
}
