package conversationEngineImporter;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/** class that loads plugins using the {@link #loadClasses() loadClasses} function.
 * 
 * @author sijmen_v_b
 */
public class PluginLoader {
	/**tries to load every class in every jar file in the plugins folder.
	 *
	 * @param pluginFolder the Folder with the plugins (.jar files).
	 * @param classInterface the interface/abstract class that will be cast to.
	 * @param initargs the constructor arguments for the object.
	 * @return a new instance of each class that implements classInterface.
	 */
	public static <Classes> LinkedList<Classes> loadClasses(File pluginFolder, Class<Classes> classInterface,Object... initargs) {
		File[] listOfFiles = pluginFolder.listFiles();
		LinkedList<Classes> list = new LinkedList<Classes>();
		if(listOfFiles == null) { //return nothing if there are no files.
			return list;
		}
		for (File file : listOfFiles) {// go over all files
			if (file.isFile()) {// if it is a file load it
				LinkedList<String> classpaths = searchForClasses(file);
				for (String classpath : classpaths) {

					try {
						// get the class loader for the current file
						URLClassLoader classLoader = new URLClassLoader(new URL[] { file.toURI().toURL() });

						// look the class in package plugins with the same name as the file.
						Class<?> c = classLoader.loadClass(classpath);
						Classes el = classInterface.cast(c.getConstructor().newInstance(initargs));
						
						list.add(el);
						System.out.println(String.format("Successfully loaded %s from \"%s\"", classpath, file.getName()));
					} catch (Exception e) {
						// Fail silently
						// e.printStackTrace();
					}
				}
			}
		}
		return list;
	}

	/**
	 * examines a jar(zip) file and returns all the class paths for all the .class
	 * files.
	 */
	private static LinkedList<String> searchForClasses(File file) {
		LinkedList<String> classPaths = new LinkedList<String>();//list to store class paths
		
		if (!file.getName().endsWith(".jar")) {//if file is not a jar file return immediately.
			return classPaths;//empty list
		}
		try {
			ZipFile zipFile = new ZipFile(file);
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			ZipEntry entry;
			while (entries.hasMoreElements()) { //for all the files in the jar file.
				entry = entries.nextElement();
				if (entry.getName().endsWith(".class")) {//if it is a class
					classPaths.add(toClassPath(entry.getName()));// add the class path to the list.
				}
			}
			zipFile.close();
		} catch (Exception e) {
			System.err.println(file.getName() + " file could not be opened.");
			e.printStackTrace();
		}
		return classPaths;
	}

	/**
	 * takes a string as file path and converts it to a class name by removing the
	 * .class and replacing the / with .
	 */
	private static String toClassPath(String s) {
		return s.replaceAll(".class", "").replaceAll("/", ".");
	}
}
