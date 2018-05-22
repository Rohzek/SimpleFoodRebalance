package com.gmail.rohzek.food;

import com.gmail.rohzek.api.classes.Ingredient;
import com.gmail.rohzek.lib.Reference;

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
public class SFIngredients
{
	public static Ingredient SALT = new Ingredient("salt");
	
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
			registerRender(SALT);
		}
		
		public static void registerRender(Item item)
		{	
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
		
		@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) 
		{
			final Item[] items =
			{
					SALT,
			};
			
			final IForgeRegistry<Item> registry = event.getRegistry();
			
			for (final Item item : items) 
			{
				registry.register(item);
			}
		}
	}
}
