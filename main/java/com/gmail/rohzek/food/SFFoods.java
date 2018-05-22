package com.gmail.rohzek.food;

import java.util.HashSet;
import java.util.Set;

import com.gmail.rohzek.api.classes.FoodSpoilable;
import com.gmail.rohzek.lib.Reference;
import com.gmail.rohzek.util.LogHelper;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Reference.MODID)
public class SFFoods
{
	public static int timeDay = 24000; // ticks for one whole day. Default: 24000
	public static int fresh = 0, stale = 1, moldy = 2;
	public static String conFresh = "fresh", conStale = "stale", conMoldy = "moldy";
	
	public static FoodSpoilable APPLE = new FoodSpoilable("apple", 1, 0.5f, false, 300, true);
	
	public static void registerOreDicts() {}
	
	public static void registerOreDict(String name, Item item) 
	{
		OreDictionary.registerOre(name, item);
	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler 
	{
		@SubscribeEvent
		public static void registerRenders(ModelRegistryEvent event)
		{
			registerRender(APPLE, fresh, conFresh);
			registerRender(APPLE, stale, conStale);
			registerRender(APPLE, moldy, conMoldy);
		}
		
		public static void registerRender(Item item)
		{	
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		
		public static void registerRender(Item item, int meta, String addon)
		{	
			System.out.println("I should be registering " + item.getRegistryName() + addon);
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName() + addon, "inventory"));
		}
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) 
		{
			final Item[] items =
			{
					APPLE,
			};
			
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			for (final Item item : items) 
			{
				registry.register(item);
			}
		}
	}
}
