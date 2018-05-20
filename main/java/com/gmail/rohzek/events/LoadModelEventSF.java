package com.gmail.rohzek.events;

import com.gmail.rohzek.blocks.SFBlocks;
import com.gmail.rohzek.food.SFFoods;
import com.gmail.rohzek.food.SFIngredients;
import com.gmail.rohzek.items.SFItems;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class LoadModelEventSF
{
	@SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event)
	{
		SFFoods.registerRenders();
		SFIngredients.registerRenders();
		SFBlocks.registerRenders();
		SFItems.registerRenders();
		//SFCraftingTools.registerRenders();
    }
}
