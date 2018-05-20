package com.gmail.rohzek.blocks;

import com.gmail.rohzek.main.Main;

import net.minecraft.block.BlockPumpkin;

public class SFPumpkin extends BlockPumpkin
{
	public SFPumpkin(String name)
	{
		super();
		
		setCreativeTab(Main.SF_TAB);
		
		setHardness(1.0f);
		
		setNames(name);
	}
	
	public void setNames(String name)
	{
		setRegistryName(name);
		setUnlocalizedName(name);
	}
}
