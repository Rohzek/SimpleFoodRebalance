package com.gmail.rohzek.items;

import java.util.HashSet;
import java.util.Set;

import com.gmail.rohzek.lib.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Reference.MODID)
public class SFItems 
{
	public static void registerRenders() {}
	
	public static void registerOreDicts() {}
	
	public static void registerRender(Item item)
	{	
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static void registerRender(Item item, int meta, String addon)
	{	
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName() + addon, "inventory"));
	}
	
	public static void registerOreDict(String name, Item item) 
	{
		OreDictionary.registerOre(name, item);
	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler 
	{
		public static final Set<Item> ITEMS = new HashSet<Item>();
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) 
		{
			final Item[] items =
			{

			};
			
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			for (final Item item : items) 
			{
				registry.register(item);
				ITEMS.add(item);
			}
		}
	}
}
