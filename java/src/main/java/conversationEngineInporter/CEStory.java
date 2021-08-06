package conversationEngineInporter;

import java.util.LinkedList;

/**
 * the Conversation Engine Story class stores the different npc's and is called
 * to generate the datapack.
 * 
 * @author Sijmen_v_b
 * 
 */
public class CEStory {
	private LinkedList<NPCGroup> groups;

	public CEStory(LinkedList<NPCGroup> groups) {
		this.groups = groups;
	}

	public void generateDatapack() {
		loadEmptyDatapack();
		createGroupFolder();
		createMessagesFolder();
		createVillagerFolder();
	}
	
	// ---- load empty datapack ----
	
	private void loadEmptyDatapack() {

	}
	
	// ---- load empty datapack ----
	// ---- create group folder ----

	private void createGroupFolder() {
		createGroupFunction();
		//for every group create the open and close function.

	}
	
	private void createGroupFunction() {
		
	}
	
	// ---- create group folder ----
	// ---- create messages folder ----

	private void createMessagesFolder() {
		createTalkFunction();
		//for every villager create their named messages folder.
	}
	
	private void createTalkFunction() {
		
	}
	
	// ---- create messages folder ----
	
	private void createVillagerFolder() {
		createKillFolder();
		createSummonFolder();
	}
	
	private void createKillFolder() {
		createkillAllFunction();
		//for every villager create kill function
	}
	
	private void createkillAllFunction() {
		
	}
	
	private void createSummonFolder() {
		//for every villager create summon function
	}
	
	// ---- create messages folder ----

}
