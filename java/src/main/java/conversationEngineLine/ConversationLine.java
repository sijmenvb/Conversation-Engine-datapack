package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConversationNode;
import conversationEngineImporter.NPC;

/**
 * this is an abstract class to contain the different types of line such as
 * normal text or a pointer to another node.
 *
 * @author Sijmen_v_b
 */
public abstract class ConversationLine {

    public ConversationLine() {
    }

    /** Returned String should be lowercase!
     * Return the first argument of the command. e.g. for << give|item|amount>> it should return "give".
     * This is used to quickly check the input before we try to fully parse.
     */
    public abstract String getNameOfFirstArgument();

    /**
     * try to parse the whole argument list into a ConversationLine
     * should return null when parsing fails.
     */
    public abstract ConversationLine tryParseArguments(String[] arguments, ConversationNode node);

    /**
     * transform the ConversationLine to Minecraft commands. You don't always need to use all inputs.
     * currentConditionPrefix contains the current condition from potential if statements and should be prepended to every command.
     *
     * @param nodeMap                map of all the nodes keyed by their names.
     * @param ceStory                this object stores all the data, you probably don't need this. But if you do, here it is.
     * @param npc                    the npc this line belongs to.
     * @param conditionList          the list of conditions as separate strings, should only be used for conditionals (if, else, endif).
     * @param currentConditionPrefix the current condition should be prepended to every command. does not include the "run".
     * @param tagList                the list of tags this npc will spawn with (tags added in game won't be accounted for), add tags here, most useful in combination with the scheduled commands.
     * @return the command pasted in the data-pack.
     */
    public abstract String toCommand(HashMap<String, ConversationNode> nodeMap, CEStory ceStory, NPC npc,
                                     LinkedList<String> conditionList, String currentConditionPrefix, LinkedList<String> tagList);

}
