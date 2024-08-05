package conversationEngineImporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.regex.Pattern;

public class ReadConfig {
	String fileName;
	String datapackName;
	boolean saveAsZip;
	boolean support1_21Plus;

	public ReadConfig() {
		createRun();
		Properties prop = new Properties();
		Properties propBackup = new Properties();

		String propfile = "./config.properties";

		FileInputStream fileIn;
		try {
			try {
				fileIn = new FileInputStream(propfile);
			} catch (FileNotFoundException e) {
				// create a new config file if no cofig file exists.
				createConfigFile();
				fileIn = new FileInputStream(propfile);
				e.printStackTrace();
			}

			prop.load(fileIn);
			propBackup.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fileName = getStringFromConfig("file", prop, propBackup);
		datapackName = getStringFromConfig("name", prop, propBackup);
		saveAsZip = getBoolFromConfig("saveAsZip", prop, propBackup);
		support1_21Plus = getBoolFromConfig("support1_21Plus", prop, propBackup);
	}

	private String getStringFromConfig(String key, Properties prop, Properties propBackup) {

		String a = prop.getProperty(key);
		if (a == null) {
			a = propBackup.getProperty(key);
		}
		if (a == null) {
			System.err.println("unexpected error while loading from config.properties file");
		}

		return a;
	}

	private int getIntFromConfig(String key, Properties prop, Properties propBackup) {
		int a;
		try {
			a = Integer.parseInt(prop.getProperty(key));
		} catch (NumberFormatException e) {
			try {
				a = Integer.parseInt(propBackup.getProperty(key));
			} catch (NumberFormatException f) {
				a = 0;
				System.err.println("unexpected error while loading from config.properties file");
			}
		}

		return a;
	}

	private boolean getBoolFromConfig(String key, Properties prop, Properties propBackup) {

		boolean a;
		if (prop.getProperty(key) != null && prop.getProperty(key).toLowerCase().equals("true")) {
			a = true;
		} else if (prop.getProperty(key) != null && prop.getProperty(key).toLowerCase().equals("false")) {
			a = false;
		} else {
			if (propBackup.getProperty(key) != null && propBackup.getProperty(key).toLowerCase().equals("true")) {
				a = true;
			} else {
				a = false;
			}
		}

		return a;
	}

	private void createConfigFile() {
		try {
			InputStream source = getClass().getClassLoader().getResourceAsStream("config.properties");
			File target = new File("./config.properties");
			Files.copy(source, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createRun() {
		
		// check if the file already exist
		File tmpDir = new File("./run.bat");
		if (!tmpDir.exists()) {
			createPluginDirectory();
			try {
				InputStream source = getClass().getClassLoader().getResourceAsStream("run.bat");
				File target = new File("./run.bat");

				Files.copy(source, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void createPluginDirectory() {
		try {
			URI uri = getClass().getClassLoader().getResource("plugins").toURI();
			Map<String, String> env = new HashMap<>();
		    try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
		        //Path path = zipfs.getPath("/images/icons16");
		        for (Path path : zipfs.getRootDirectories()) {
		            Files.list(path.resolve("/plugins"))
		                    .forEach(p -> unpackFile(p,"plugins"));
		        }
		    }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void unpackFile(Path p,String folderName) {
		try {
			String path = p.toString().replaceFirst(".*(?=" + Pattern.quote(folderName) + ")", "");
			InputStream source = getClass().getClassLoader().getResourceAsStream(path);
			File target = new File("./"+path);

			Files.copy(source, target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}

	public String getDatapackName() {
		return datapackName;
	}

	public boolean isSaveAsZip() {
		return saveAsZip;
	}

	public boolean isSupport1_21Plus() {
		return support1_21Plus;
	}
}
