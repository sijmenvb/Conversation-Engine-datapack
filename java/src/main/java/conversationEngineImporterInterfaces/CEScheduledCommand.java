
package conversationEngineImporterInterfaces;

/**
 * This is an interface for the commands that are executed every tick.
 * The commands should contain comment of what the command does and the full command itself.
 * 
 * @author elanto-dev
 *
 */
public abstract class CEScheduledCommand {
	public abstract String toScheduledCommandWithComment();
	public Integer getPeriodInGameTicks() {
		return 1;
	}
}
