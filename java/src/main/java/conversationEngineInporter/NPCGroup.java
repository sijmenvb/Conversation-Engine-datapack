package conversationEngineInporter;

import java.util.LinkedList;

public class NPCGroup {
	
	private LinkedList<NPC> npcs = new LinkedList<NPC>();
	private int groupId;
	
	public NPCGroup(LinkedList<NPC> npcs, int groupId) {
		super();
		this.npcs = npcs;
		this.groupId = groupId;
	}
	
	

}
