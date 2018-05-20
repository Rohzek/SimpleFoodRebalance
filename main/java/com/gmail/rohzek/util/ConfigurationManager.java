package com.gmail.rohzek.util;

import java.io.File;

import com.gmail.rohzek.lib.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigurationManager
{
	public static File optionsLoc;
	
	public static boolean isDebug;
	
	public static String genCategory = "general";
	public static String debugCategory = "debug";
	public static String modCategory = "compatibility";
	public static String ovrCategory = "overrides";
	
	public ConfigurationManager(FMLPreInitializationEvent event)
	{
		optionsLoc = new File(Reference.LOCATION + "/options.cfg");
		
		Configuration optionsConfig = new Configuration(optionsLoc);
		options(optionsConfig);
	}
	

	private void options(Configuration config)
	{
		config.load();
		
		this.isDebug = config.get(debugCategory, "debugMode", false, "Enables more printouts to the chat. WARNING: Will spam the log file. Good for bug reports. Not recommended for regular play.").getBoolean(false);
		
		config.save();
	}
}