package conversationEngineLine;

import conversationEngineInporter.ConverzationNode;

public class StringLine extends ConversationLine {

	private String text;
	

	public StringLine(String text, ConverzationNode node) {
		super(node);
		this.text = text;
	}

	public String toCommand() {
		return "execute if score bool CE_suc matches 1 run tellraw @s [{\"text\":\""+text+"\",\"color\":\"white\"}]\n";
	}

}
