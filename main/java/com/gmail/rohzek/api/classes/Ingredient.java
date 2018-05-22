package com.gmail.rohzek.api.classes;

import com.gmail.rohzek.main.Main;

import net.minecraft.item.Item;

public class Ingredient extends Item 
{
	public Ingredient(String names)
	{
		setCreativeTab(Main.SF_TAB);
		setNames(names);
	}
	
	void setNames(String names) 
	{
		setRegistryName(names);
		setUnlocalizedName(names);
	}
}
