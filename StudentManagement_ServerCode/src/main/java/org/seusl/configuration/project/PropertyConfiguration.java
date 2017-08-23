package org.seusl.configuration.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Gautam Kumar
 *
 */
public class PropertyConfiguration {

	private Logger log = Logger.getLogger(PropertyConfiguration.class);
		
	private final Properties prop = new Properties();

	public PropertyConfiguration() {
		try {
			InputStream is = new FileInputStream("src//main//resources//configuration.properties");
			prop.load(is);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
	public String getEmail() {
		return prop.getProperty("email");
	}
	
	public String getEmailUsername() {
		return prop.getProperty("username");
	}
	
	public String getEmailPassword(){
		return prop.getProperty("password");
	}
	
	public String getUrl() {
		return prop.getProperty("Url");
	}
}
