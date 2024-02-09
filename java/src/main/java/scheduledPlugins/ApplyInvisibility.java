package scheduledPlugins;

import conversationEngineImporterInterfaces.CEScheduledCommand;

public class ApplyInvisibility extends CEScheduledCommand {

	public String toScheduledCommandWithComment() {
		String comment = "# make invisible villagers invisible";
		String command = "execute as @e[tag=CE_invisible] run data merge entity @s {NoGravity:0b,Silent:1b,active_effects:[{id:\"minecraft:invisibility\",amplifier:0b,duration:1000,show_particles:0b}]}";
		return String.format("%s\n%s", comment, command);
	}

	@Override
	public Integer getPeriodInGameTicks() {
		return 400;
	}

}