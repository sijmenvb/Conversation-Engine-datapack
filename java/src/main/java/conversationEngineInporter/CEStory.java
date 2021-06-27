package conversationEngineInporter;

import java.util.LinkedList;

/**the Conversation Engine Story class stores the different npc's and is called to generate the datapack.  
 * 
 * @author Sijmen_v_b
 * 
 */
public class CEStory {
	private LinkedList<NPCGroup> groups;

	public CEStory(LinkedList<NPCGroup> groups) {
		this.groups = groups;
	}

}
