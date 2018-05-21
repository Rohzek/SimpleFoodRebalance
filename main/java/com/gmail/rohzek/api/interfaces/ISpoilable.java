package com.gmail.rohzek.api.interfaces;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public interface ISpoilable 
{
	public boolean isCooked = false;
	public int timeBeforeMold = 0;
	
	public void spoil();
}
