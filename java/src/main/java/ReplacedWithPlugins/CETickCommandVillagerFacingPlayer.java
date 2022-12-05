package conversationEngineImporter;

import conversationEngineImporterInterfaces.CEScheduledCommand;

public class CETickCommandVillagerFacingPlayer extends CEScheduledCommand{

	@Override
	public String toScheduledCommandWithComment() {
		String comment = "# make sure all NPC's face players when less than 7 blocks away.";
		String command = "execute at @a as @e[type=minecraft:villager,tag=CE_npc,distance=..7] at"
				+ " @s facing entity @a[limit=1,sort=nearest,distance=..7] feet run tp @s ~ ~ ~ ~ ~";
		return String.format("%s\n%s", comment, command);
	}

}
