package com.gmail.rohzek.lib;

import java.io.File;

/**
 * Holds global variables to be called elsewhere
 * @author Rohzek
 *
 */
public class Reference 
{
	// Mod ID
	public static final String MODID = "simplefoodrebalance";
	
	// Just like MODID but with : attached to save time while assigning resource locations
	public static final String RESOURCEID = "simplefoodrebalance:";
	
	// The name that gets seen in brackets when we log to the console.
	public static final String LOG = "SIMPFOODREB";
	
	// Human readable title
	public static final String NAME = "Simple Food Rebalance";
	
	// Mods to load after
	public static final String DEPENDENCIES = "after:cookingforblockheads;";
	
	// Folder holding config data
	public static File LOCATION; 
	
	// We only have to change it here... MCMOD.info is gone and the ModData is hardcoded to check here
	public static final String VERSION = "0.2.0";
	
	static String address = "com.gmail.rohzek.proxy";
	
	public static final String CLIENTSIDEPROXY = "com.gmail.rohzek.proxy.ClientProxy";
	public static final String SERVERSIDEPROXY = "com.gmail.rohzek.proxy.CommonProxy";
}