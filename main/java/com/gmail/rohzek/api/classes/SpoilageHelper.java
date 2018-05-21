package com.gmail.rohzek.api.classes;

import com.gmail.rohzek.util.ConfigurationManager;
import com.gmail.rohzek.util.LogHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class SpoilageHelper 
{
	public static int spoilClock = 150;
	
	public static void spoil(ItemStack stack, long currentTime)
	{
		if(ConfigurationManager.foodSpoils)
		{
			LogHelper.log("Spoiling food!");
		}
	}
	
	public static boolean loadCookedStatus(ItemStack stack)
	{
		NBTTagCompound data = stack.getTagCompound();
		return data.getBoolean("cookedStatus");
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
		return data.getLong("creationTime");
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
	
	public static int loadCurrentCondition(ItemStack stack)
	{
		NBTTagCompound data = stack.getTagCompound();
		return data.getInteger("currentCondition");
	}
	
	public static void saveCurrentCondition(ItemStack stack, int condition)
	{
		NBTTagCompound data = stack.getTagCompound();
		
		if(data == null)
		{
			data = new NBTTagCompound();
		}
		
		data.setInteger("currentCondition", condition);
	}
	
	public static long loadDecayFactor(ItemStack stack)
	{
		NBTTagCompound data = stack.getTagCompound();
		return data.getLong("decayFactor");
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
		return data.getLong("timeLast");
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
		return data.getLong("storeTimestamp");
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
