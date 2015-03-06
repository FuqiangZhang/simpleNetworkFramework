package edu.uta.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFactory {

	private static Properties prop = null;

	public static Properties getProperties() {
		if (null == prop) {
			prop = new Properties();
			try {
				prop.load(new FileReader(new File("config/config.properties")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}
}
