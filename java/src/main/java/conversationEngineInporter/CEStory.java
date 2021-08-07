package conversationEngineInporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Stream;
import static java.nio.file.StandardCopyOption.*;

/**
 * the Conversation Engine Story class stores the different npc's and is called
 * to generate the datapack.
 * 
 * @author Sijmen_v_b
 * 
 */
public class CEStory {
	private LinkedList<NPCGroup> groups;
	private static String name = "exported datapack";

	public CEStory(LinkedList<NPCGroup> groups) {
		this.groups = groups;
	}

	public void generateDatapack() {
		deletePreviousDatapack();
		loadEmptyDatapack();
		createGroupFolder();
		createMessagesFolder();// TODO
		createVillagerFolder();
	}

	// ---- delete previous datapack ----

	/**
	 * deletes previous datapack with same name if it exists.
	 * 
	 * @param name
	 */
	private void deletePreviousDatapack() {
		try {
			deleteDirectory(System.getProperty("user.dir") + "\\" + name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There was no previous datapack to delete.");
		}
	}

	// ---- delete previous datapack ----
	// ---- load empty datapack ----

	/**
	 * copys the empty datapack to the root directory
	 * 
	 * @param name
	 */
	private void loadEmptyDatapack() {
		try {
			copyDirectory(System.getProperty("user.dir") + "\\src\\main\\resources\\datapack empty",
					System.getProperty("user.dir") + "\\" + name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ---- load empty datapack ----
	// ---- create group folder ----

	private void createGroupFolder() {
		createDirectory(name + "\\data\\conversation_engine\\functions\\group");
		createGroupFunction();
		createOpenFunction();
		createCloseFunction();

	}

	private void createOpenFunction() {
		for (NPCGroup npcGroup : groups) {
			String s = npcGroup.createOpenFunction();

			// try to save the file.
			SaveAsFile(s, String.format("%s\\data\\conversation_engine\\functions\\group\\%03d.mcfunction", name,
					npcGroup.getGroupId()));

		}

	}

	private void createCloseFunction() {
		for (NPCGroup npcGroup : groups) {
			String s = npcGroup.createCloseFunction();

			// try to save the file.
			SaveAsFile(s, String.format("%s\\data\\conversation_engine\\functions\\group\\close_%03d.mcfunction", name,
					npcGroup.getGroupId()));

		}

	}

	private void createGroupFunction() {
		// start the function with some comments
		String s = "# run by server\n\n# this is for grouping of villagers so we don't have to check each villager each tick only each group.\n\n# check if there is a conversation in a group:\n";
		for (NPCGroup npcGroup : groups) {// for each group create the execute command with the correct group id.
			s += String.format(
					"execute if score CE_mannager CE_group_%02d matches 1 run function conversation_engine:group/%03d\n",
					npcGroup.getGroupId(), npcGroup.getGroupId());
		}

		// try to save the file.
		SaveAsFile(s, name + "\\data\\conversation_engine\\functions\\group\\group.mcfunction");

	}

	// ---- create group folder ----
	// ---- create messages folder ----

	private void createMessagesFolder() {
		createDirectory(name + "\\data\\conversation_engine\\functions\\messages");
		createTalkFunction();
		// for every villager create their named messages folder.
	}

	private void createTalkFunction() {

		String s = "# kill all npc's\nkill @e[type=villager,tag=CE_npc]";

		SaveAsFile(s, name + "\\data\\conversation_engine\\functions\\messages\\talk.mcfunction");

	}

	// ---- create messages folder ----
	// ---- create villager folder ----

	private void createVillagerFolder() {
		createDirectory(name + "\\data\\conversation_engine\\functions\\villager");
		createKillFolder();
		createSummonFolder();
	}

	private void createKillFolder() {
		createDirectory(name + "\\data\\conversation_engine\\functions\\villager\\kill");
		createkillAllFunction();
		// for every villager create kill function
		for (NPCGroup npcGroup : groups) {
			for (NPC npc : npcGroup.getNpcs()) {
				String s = String.format("# kill the labrat npc\nkill @e[type=villager,tag=CE_npc,tag=%s]",
						npc.getName());

				SaveAsFile(s, String.format("%s\\data\\conversation_engine\\functions\\villager\\kill\\%s.mcfunction",
						name, npc.getName()));

			}
		}
	}

	private void createkillAllFunction() {
		String s = "# kill all npc's\nkill @e[type=villager,tag=CE_npc]";
		SaveAsFile(s, name + "\\data\\conversation_engine\\functions\\villager\\kill\\all.mcfunction");
	}

	private void createSummonFolder() {
		createDirectory(name + "\\data\\conversation_engine\\functions\\villager\\summon");
		// for every villager create summon function
		for (NPCGroup npcGroup : groups) {
			for (NPC npc : npcGroup.getNpcs()) {
				String s = String.format(
						"# summon a villager with a name a tag equal to the name (space becomes _ ) and the CE_npc tag \nsummon villager ~ ~ ~ {Tags:[\"CE_npc\",\"%s\"],Invulnerable:1b,CustomNameVisible:1b,NoAI:1b,CanPickUpLoot:0b,CustomName:'{\"text\":\"%s\",\"color\":\"white\"}',VillagerData:{profession:\"minecraft:nitwit\"},Offers:{}}",
						npc.getName(), npc.getName().replace('_', ' '));

				SaveAsFile(s, String.format("%s\\data\\conversation_engine\\functions\\villager\\summon\\%s.mcfunction",
						name, npc.getName()));

			}
		}
	}

	// ---- create villager folder ----
	// ---- save string as file ----

	private void SaveAsFile(String s, String path) {
		try {
			PrintWriter out = new PrintWriter(path);
			out.write(s);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ---- save string as file ----
	// ---- create directory ----

	private void createDirectory(String dirpath) {
		File f = new File(dirpath);
		if (!f.exists()) {
			f.mkdir();
		}
	}

	// ---- create directory ----
	// ---- copy directory ---- source: https://www.baeldung.com/java-copy-directory

	public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation)
			throws IOException {
		Files.walk(Paths.get(sourceDirectoryLocation)).forEach(source -> {
			Path destination = Paths.get(destinationDirectoryLocation,
					source.toString().substring(sourceDirectoryLocation.length()));
			try {
				Files.copy(source, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	// ---- copy directory ---- source: https://www.baeldung.com/java-copy-directory
	// ---- delete directory ---- source:
	// https://softwarecave.org/2018/03/24/delete-directory-with-contents-in-java/

	private void deleteDirectory(String dirpath) throws IOException {
		File file = new File(dirpath);
		deleteDirectoryRecursionJava6(file);
	}

	private void deleteDirectoryRecursionJava6(File file) throws IOException {
		if (file.isDirectory()) {
			File[] entries = file.listFiles();
			if (entries != null) {
				for (File entry : entries) {
					deleteDirectoryRecursionJava6(entry);
				}
			}
		}
		if (!file.delete()) {
			throw new IOException("Failed to delete " + file);
		}
	}
	// ---- delete directory ---- source:
	// https://softwarecave.org/2018/03/24/delete-directory-with-contents-in-java/
}
