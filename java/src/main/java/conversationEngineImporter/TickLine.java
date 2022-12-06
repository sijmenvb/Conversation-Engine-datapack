package conversationEngineImporter;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class TickLine {

	public abstract String toCommand(LinkedList<String> condition, String con);

}
