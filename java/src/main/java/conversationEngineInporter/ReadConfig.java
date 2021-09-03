package conversationEngineInporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class ReadConfig {
	String fileName = "input.json";

	public ReadConfig() {
		Properties prop = new Properties();

		String propfile = "./config.properties";

		FileInputStream fileIn;
		try {
			try {
				fileIn = new FileInputStream(propfile);
			} catch (FileNotFoundException e) {	
				//create a new config file if no cofig file exists.
				createConfigFile();
				fileIn = new FileInputStream(propfile);
				e.printStackTrace();
			}

			prop.load(fileIn);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fileName = prop.getProperty("file");

		System.err.println(fileName);

	}
	
	private void createConfigFile() {
		try {
			InputStream source = getClass().getClassLoader().getResourceAsStream("config.properties");
			File target = new File("./config.properties");
			Files.copy(source,target.toPath() , StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFileName() {
		return fileName;
	}
	
	

}
