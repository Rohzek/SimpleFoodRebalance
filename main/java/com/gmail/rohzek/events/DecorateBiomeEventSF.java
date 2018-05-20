package com.gmail.rohzek.events;

import java.util.Random;

import com.gmail.rohzek.util.LogHelper;
import com.gmail.rohzek.world.WorldGenPumpkinSF;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DecorateBiomeEventSF 
{
	@SubscribeEvent
	public static void onDecorateBiome(DecorateBiomeEvent.Decorate event)
	{
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		EventType type = event.getType();
		Random rand = event.getRand();
		
		if(type == EventType.PUMPKIN)
		{
			event.setResult(Result.DENY);
			
			LogHelper.debug("Spawning mod pumpkins");
			
			// Generate modded pumpkins
			if (rand.nextInt(32) == 0)
	        {
	            int posX = rand.nextInt(16) + 8;
	            int posZ = rand.nextInt(16) + 8;
	            int posY = world.getHeight(pos.add(posX, 0, posZ)).getY() * 2;

	            if (posY > 0)
	            {
	                int randY = rand.nextInt(posY);
	                (new WorldGenPumpkinSF()).generate(world, rand, pos.add(posX, randY, posZ));
	            }
	        }
		}
	}
}
