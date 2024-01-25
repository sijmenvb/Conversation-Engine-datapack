package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;
import conversationEngineImporterInterfaces.*;

/**
 * this is an abstract class to contain the different types of line such as
 * normal text or a pointer to another node.
 * 
 * @author Sijmen_v_b
 *
 */
public abstract class ConversationLine {

	public ConversationLine() {
	}	
	
	/* Return the first argument of the command. e.g. for <<give|item|amount>> it should return "give".
	 * This is used to quickly check the input before we try to fully parse.
	 */
	public abstract String getNameOfFirstArgument();
		
	/* try to parse the whole argument list into a ConversationLine
	 * should return null when parsing fails.
	 */
	public abstract ConversationLine tryParseArguments(String[] arguments, ConversationNode node);
		
	/** transform the ConversationLine to Minecraft commands. You don't always need to use all inputs.
	 * currentCondition contains the current condition from potential if statements and should be prepended to every command.
	*/
	public abstract String toCommand(HashMap<String, ConversationNode> nodes, CEStory ceStory, NPC npc,
				LinkedList<String> listOfConditions, String currentCondition, LinkedList<String> tags);

}
