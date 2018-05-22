package com.gmail.rohzek.api.classes;

import com.gmail.rohzek.util.ConfigurationManager;
import com.gmail.rohzek.util.LogHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SpoilageHelper 
{
	// We should check foods in hand way more often than on ground.
	public static int spoilClockHand = 150, spoilClockGround = 400;
	
	public static void spoil(ItemStack stack, long currentTime, boolean isHand)
	{
		int spoilTimer = isHand ? spoilClockHand : spoilClockGround;
		
		if(ConfigurationManager.foodSpoils)
		{
			long lastRan = (loadStoreTimeStamp(stack));
			
			/*
			 * This is an error case, which pretty much only happens when an item is spawned
			 * with creative mode.. Maybe when spawned from mob drops, too?
			 */
			if(lastRan == 0)
			{
				long decay = loadDecayFactor(stack);
				long ruin = loadLastingFactor(stack);
				
				decay++;
				
				LogHelper.debug("Decaying food " + (isHand ? "in inventory" : "on ground") + ": " + decay);
				
				LogHelper.debug("Will last for: " + (((ruin - decay) / 20) / 60) + " more minutes");
				
				saveStoreTimeStamp(stack, currentTime);
				
				// Half makes the item stale
				if(decay >= ruin / 2)
				{
					/*
					 * Previously saved the condition as an int in the NBT
					 * But let's just use the damage value in the future.
					 */
					//saveCurrentCondition(stack, 1);
					//getItemStackDisplayName(stack);
					stack.setItemDamage(1);
				}
				
				// Full makes the item moldy/ruined
				if(decay >= ruin)
				{
					//getItemStackDisplayName(stack);
					stack.setItemDamage(2);
				}
			}
			
			else if(lastRan != 0 && (currentTime - lastRan) >= spoilTimer)
			{
				long decay = loadDecayFactor(stack);
				long ruin = loadLastingFactor(stack);
				
				decay += (currentTime - lastRan);
				
				LogHelper.debug("Decaying food " + (isHand ? "in inventory" : "on ground") + ": " + decay);
				
				LogHelper.debug("Will last for: " + (((ruin - decay) / 20) / 60) + " more minutes");
				
				saveDecayFactor(stack, decay);
				saveStoreTimeStamp(stack, currentTime);
				
				// Half makes the item stale
				if(decay >= ruin / 2)
				{
					//getItemStackDisplayName(stack);
					stack.setItemDamage(1);
				}
				 
				// Full makes the item moldy/ruined
				if(decay >= ruin)
				{
					//getItemStackDisplayName(stack);
					stack.setItemDamage(2);
				}
			}
		}
	}
	
	public static boolean loadCookedStatus(ItemStack stack)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		boolean cooked = false;
		
		if(data.hasKey("cookedStatus"))
		{
			cooked = data.getBoolean("cookedStatus");
		}
		
		return cooked;
	}
	
	public static void saveCookedStatus(ItemStack stack, boolean status)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		if(data == null)
		{
			data = new NBTTagCompound();
		}
		
		data.setBoolean("cookedStatus", status);
	}
	
	public static long loadCreationTime(ItemStack stack)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		long time = 0;
		
		if(data.hasKey("creationTime"))
		{
			time = data.getLong("creationTime");
		}
		
		return time;
	}
	
	public static void saveCreationTime(ItemStack stack, long time)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		if(data == null)
		{
			data = new NBTTagCompound();
		}
		
		data.setLong("creationTime", time);
	}
	
	public static long loadDecayFactor(ItemStack stack)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		long decay = 0;
		
		if(data.hasKey("decayFactor"))
		{
			decay = data.getLong("decayFactor");
		}
		
		return decay;
	}
	
	public static void saveDecayFactor(ItemStack stack, long time)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		if(data == null)
		{
			data = new NBTTagCompound();
		}
		
		data.setLong("decayFactor", time);
	}
	
	public static long loadLastingFactor(ItemStack stack)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		FoodSpoilable item = (FoodSpoilable) stack.getItem();
		long last = item.spoil;
		
		if(data.hasKey("timeLast"))
		{
			last = data.getLong("timeLast");
		}
		
		return last;
	}
	
	public static void saveLastingFactor(ItemStack stack, long spoil)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		if(data == null)
		{
			data = new NBTTagCompound();
		}
		
		data.setLong("timeLast", spoil);
	}
	
	public static long loadStoreTimeStamp(ItemStack stack)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		long store = 0;
		
		if(data.hasKey("storeTimestamp"))
		{
			store = data.getLong("storeTimestamp");
		}
		
		return store;
	}
	
	public static void saveStoreTimeStamp(ItemStack stack, long time)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		if(data == null)
		{
			data = new NBTTagCompound();
		}
		
		data.setLong("storeTimestamp", time);
	}}
