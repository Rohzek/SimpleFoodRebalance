package com.gmail.rohzek.proxy;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Not a lot going on here, is for things that must be registered ONLY on the server
 * @author Rohzek
 *
 */
@Mod.EventBusSubscriber
public class CommonProxy 
{
	@SubscribeEvent
	public void registerRenders(ModelRegistryEvent event){}
}