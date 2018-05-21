package com.gmail.rohzek.proxy;

import com.gmail.rohzek.blocks.SFBlocks;
import com.gmail.rohzek.food.SFFoods;
import com.gmail.rohzek.food.SFIngredients;
import com.gmail.rohzek.items.SFItems;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * For things that must render ONLY on client, such as the graphics for the blocks we're rendering here.
 * @author Rohzek
 *
 */
@Mod.EventBusSubscriber
public class ClientProxy extends CommonProxy
{
	@Override
	@SubscribeEvent
	public void registerRenders(ModelRegistryEvent event)
	{
		SFFoods.registerRenders();
		SFIngredients.registerRenders();
		SFBlocks.registerRenders();
		SFItems.registerRenders();
	}
}