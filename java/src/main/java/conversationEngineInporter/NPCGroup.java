package conversationEngineInporter;

import java.util.LinkedList;

public class NPCGroup {

	private LinkedList<NPC> npcs;
	private int groupId;

	public NPCGroup(LinkedList<NPC> npcs, int groupId) {
		super();
		this.npcs = npcs;
		this.groupId = groupId;
	}

	public String createOpenFunction() { // the "open function is the one with just the number e.g. 000.mcfunction"
		String s = "# run by server\n\n# check wich npc is being talked to.\n";

		for (NPC npc : npcs) {
			s += String.format(
					"execute if score CE_mannager %s matches 1 as @p[scores={%s=1}] run function conversation_engine:messages/%s/tick\n",
					npc.getName(), npc.getName(), npc.getName());
		}

		return s;
	}

	public String createCloseFunction() {
		String s = "# run as the player\n\n# check if there are no players talking to anny of the npc's in the group. if so set group to 0\n\n# reset the bool\nscoreboard players set bool CE_suc 0\n\n# check all the NPC's\n";

		for (NPC npc : npcs) {
			s += String.format("execute if entity @a[scores={%s=1}] run scoreboard players set bool CE_suc 1\n",
					npc.getName());
		}

		s += "\n    # if no player were found close the connection\n    execute if score bool CE_suc matches 0 run scoreboard players set CE_mannager CE_group_00 0";
		return s;
	}

	public int getGroupId() {
		return groupId;
	}

	public LinkedList<NPC> getNpcs() {
		return npcs;
	}

}
