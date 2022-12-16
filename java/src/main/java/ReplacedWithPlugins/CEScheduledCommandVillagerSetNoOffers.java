package conversationEngineImporter;

import conversationEngineImporterInterfaces.CEScheduledCommand;

public class CEScheduledCommandVillagerSetNoOffers extends CEScheduledCommand {

	public String toScheduledCommandWithComment() {
		String comment = "# villager is set to have no commands";
		String command = "execute as @e[type=minecraft:villager,tag=CE_npc] run data modify entity @s Offers set value {}";
		return String.format("%s\n%s", comment, command);
	}

	@Override
	public Integer getPeriodInGameTicks() {
		return 400;
	}

}
