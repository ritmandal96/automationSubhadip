package com.ritm.configFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
	
	/**
	 * About to get data from Config file
	 * @param strKey
	 * @return
	 * @throws IOException
	 */
	public static String readPropertyFile(String strKey) throws IOException {
		Properties prop = new Properties();
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\ritm\\configfiles\\Config.properties");
		FileInputStream fileInputStream = new FileInputStream(file);
		prop.load(fileInputStream);
		return prop.getProperty(strKey);
	}

}
