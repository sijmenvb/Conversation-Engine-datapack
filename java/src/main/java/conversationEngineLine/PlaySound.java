package conversationEngineLine;

import java.util.HashMap;
import java.util.LinkedList;

import conversationEngineImporter.CEStory;
import conversationEngineImporter.ConverzationNode;
import conversationEngineImporter.NPC;

public class PlaySound extends ConversationLine {

	String sound;

	public PlaySound(String sound, ConverzationNode node) {
		super(node);
		if(sound.charAt(0) == '/') {
			sound = sound.substring(1);//if the command starts with a / remove it.
		}
		this.sound = sound;
	}

	public String toCommand(HashMap<String, ConverzationNode> nodes, CEStory ceStory, NPC npc, LinkedList<String> condition, String con) {
		return String.format("%srun stopsound @s\n%sat @e[type= villager, tag = %s] run playsound %s voice @s\n",con, con, npc.getTagName(), sound);
	}

}
