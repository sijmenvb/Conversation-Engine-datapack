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

	public CEStory(LinkedList<NPCGroup> groups) {
		this.groups = groups;
	}

	public void generateDatapack() {
		String name = "exported datapack";
		deletePreviousDatapack(name);
		loadEmptyDatapack(name);
		createGroupFolder();
		createMessagesFolder();
		createVillagerFolder();
	}

	// ---- delete previous datapack ----
	private void deletePreviousDatapack(String name) {
		try {
			deleteDirectory(System.getProperty("user.dir") + "\\" + name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There was no previous datapack to delete.");
		}
	}

	// ---- delete previous datapack ----
	// ---- load empty datapack ----

	private void loadEmptyDatapack(String name) {
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
		createGroupFunction();
		// for every group create the open and close function.

	}

	private void createGroupFunction() {

	}

	// ---- create group folder ----
	// ---- create messages folder ----

	private void createMessagesFolder() {
		createTalkFunction();
		// for every villager create their named messages folder.
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
		// for every villager create kill function
	}

	private void createkillAllFunction() {

	}

	private void createSummonFolder() {
		// for every villager create summon function
	}

	// ---- create messages folder ----
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
