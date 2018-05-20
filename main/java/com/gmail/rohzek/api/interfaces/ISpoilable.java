package com.gmail.rohzek.api.interfaces;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public interface ISpoilable 
{
	public int delay = 150;
	public boolean isCooked = false;
	public int range = 24;
	public int timeBeforeMold = 0;
	
	public ItemStack createNBTTag();
	public List<PotionEffect> getEatingEffects(int condition);
	public boolean loadCookedStatus(ItemStack itemStack);
	public long loadCreationTime(ItemStack itemStack);
	public int loadCurrentCondition(ItemStack itemStack);
	public int loadDecayFactor(ItemStack itemStack);
	public int loadLastingFactor(ItemStack itemStack);
	public long loadStoreTimeStamp(ItemStack itemStack);
	public void saveCookedStatus(ItemStack itemStack, boolean status);
	public void saveCreationTime(ItemStack itemStack, long creationTime);
	public void saveCurrentCondition(ItemStack itemStack, int currentCondition);
	public void saveDecayFactor(ItemStack itemStack, int decayFactor);
	public void saveLastingFactor(ItemStack itemStack, int timeLast);
	public void saveStoreTimeStamp(ItemStack itemStack, long storeTimestamp);
	public void spoil();
	public void updateCondition(ItemStack itemStack, long currentTime);
	public void updateConditionEntityItem(ItemStack itemStack, long time);
}
