package com.gmail.rohzek.main;

import java.io.File;

import com.gmail.rohzek.creativetabs.SFTab;
import com.gmail.rohzek.lib.Reference;
import com.gmail.rohzek.proxy.CommonProxy;
import com.gmail.rohzek.smelting.SmeltingRecipes;
import com.gmail.rohzek.util.ConfigurationManager;
import com.gmail.rohzek.util.LoadModData;
import com.gmail.rohzek.util.LogHelper;
import com.gmail.rohzek.util.TimeOutput;

import net.minecraft.init.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class Main 
{
	@Instance(Reference.MODID)
	public static Main simplefoodrebalance;
	
	@SidedProxy(clientSide = Reference.CLIENTSIDEPROXY, serverSide = Reference.SERVERSIDEPROXY)
	public static CommonProxy proxy;
	
	public static final SFTab SF_TAB = new SFTab("foodsTab", Items.COOKED_BEEF);
	
	@EventHandler
	public static void PreLoad(FMLPreInitializationEvent preEvent)
	{
		LogHelper.log("Hello Minecraft, how are you? Did you know that Tony loves Amy? " + TimeOutput.getTimeTogether());
		
		LogHelper.debug("Beginning Pre-Initialization");
		Reference.LOCATION = new File(preEvent.getModConfigurationDirectory().getAbsolutePath() + "/" + Reference.MODID);
		
		LogHelper.debug("Loading MCMOD replacement info");
		LoadModData.load(preEvent);
		
		// Configuration file loader
		ConfigurationManager manager = new ConfigurationManager(preEvent);
		
		LogHelper.debug("Pre-Initialization Complete");
	}
	
	@EventHandler
	public static void load(FMLInitializationEvent event)
	{
		LogHelper.debug("Beginning Initialization");
		
		LogHelper.debug("Initialization Complete");
	}
	
	@EventHandler
	public static void PostLoad(FMLPostInitializationEvent postEvent)
	{
		LogHelper.log("Checking for compatibility modules");
		
		LogHelper.debug("Adding smelting recipes");
		SmeltingRecipes.mainRegistry();
	}
}
