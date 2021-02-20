package com.freenow.Freenow.utils;

import java.io.FileInputStream;
import java.util.Properties;

import com.freenow.Freenow.utils.ConfigManager;

public class ConfigManager {
	
	private static ConfigManager manager;
	private static final Properties PROPS = new Properties();
	
	private ConfigManager() {
		try{
			FileInputStream config = new FileInputStream(System.getProperty("user.dir")+"/resource/config.properties");
			PROPS.load(config);
			} catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public static ConfigManager getInstance() {		
		if(manager == null) {
			synchronized(ConfigManager.class) {
				manager = new ConfigManager();
			}
		}
		return manager;
	}
	
	public String getString(String key) {
		return System.getProperty(key, PROPS.getProperty(key));
	}
}
