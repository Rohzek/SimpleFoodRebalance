package com.gmail.rohzek.api.classes;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.gmail.rohzek.main.Main;
import com.gmail.rohzek.util.LogHelper;
import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FoodSpoilable extends ItemFood
{
	List<PotionEffect> effects = null;
	long spoil = 0;
	boolean isCooked;
	
	public FoodSpoilable(String name, int healAmount, float saturation, boolean isWolfFood, long spoilTime, boolean cooked) 
	{
		super(healAmount, saturation, isWolfFood);
		
		setNames(name);
		
		setCreativeTab(Main.SF_TAB);
		setMaxStackSize(1);
		setHasSubtypes(true);
		
		this.spoil = spoilTime;
		this.isCooked = cooked;
	}
	
	private void setNames(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	// This fires while you're holding the item
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity player, int par4, boolean par5) 
	{
		if(!world.isRemote)
		{
			SpoilageHelper.spoil(itemStack, world.getTotalWorldTime(), true);
		}
	}
	
	@Override
	public boolean onEntityItemUpdate(EntityItem entity)
	{
		if(!entity.world.isRemote) 
		{
			SpoilageHelper.spoil(entity.getItem(), entity.world.getTotalWorldTime(), false);
		}
		
		// Returning true does some weird stuff.
		return false;
	}
	
	// changes display name?
	@Override
	public String getItemStackDisplayName(ItemStack stack) 
	{
		String name = super.getItemStackDisplayName(stack);
		String prefix = getDamage(stack) == 0 ? "Fresh " : getDamage(stack) == 1 ? "Stale " : "Moldy ";
		
		return prefix + name;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) 
	{
		int currentState = getDamage(stack);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
		{
			if(currentState == 0 && stack.getUnlocalizedName().toLowerCase().contains("apple") || 
			   currentState == 0 && stack.getUnlocalizedName().toLowerCase().contains("melon") ||
			   currentState == 0 && stack.getUnlocalizedName().toLowerCase().contains("sushi"))
			{
				tooltip.add(ChatFormatting.GREEN + "Fresh food is safe to eat.");
			}
			
			else if(currentState == 0 && isCooked)
			{
				tooltip.add(ChatFormatting.GREEN + "Fresh, cooked food is safe to eat.");
			}
			
			else if(currentState == 0 && !isCooked)
			{
				tooltip.add(ChatFormatting.RED + "Fresh, uncooked food is dangerous to eat.");
			}
					
			if(currentState == 1 && stack.getUnlocalizedName().toLowerCase().contains("apple") || 
			   currentState == 1 && stack.getUnlocalizedName().toLowerCase().contains("melon") ||
			   currentState == 1 && stack.getUnlocalizedName().toLowerCase().contains("sushi"))
			{
				tooltip.add(ChatFormatting.YELLOW + "Stale food is risky to eat.");
			}
			
			else if(currentState == 1 && isCooked)
			{
				tooltip.add(ChatFormatting.YELLOW + "Stale, cooked food is risky to eat.");
			}
			else if(currentState == 1 && !isCooked)
			{
				tooltip.add(ChatFormatting.YELLOW + "Stale, uncooked food is dangerous to eat.");
			}
			
			else if(currentState == 2)
			{
				tooltip.add(ChatFormatting.RED + "Moldy food is dangerous to eat.");
			}
		}
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack item)
    {
        return 20;
    }
	
	// We can get the time the item was crafted for decay
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) 
	{
		int condition = getDamage(itemStack);
		itemStack.setTagCompound(new NBTTagCompound());
		
		if(condition == 0) 
		{
			SpoilageHelper.saveDecayFactor(itemStack, 0);
		}
		
		else if(condition == 1) 
		{
			SpoilageHelper.saveDecayFactor(itemStack, (spoil / 2));
		}
		
		else if(condition == 2) 
		{
			SpoilageHelper.saveDecayFactor(itemStack, spoil);
		}
		
		else
		{
			LogHelper.log("Error on spoilable food creation: Condition not 0-2");
			return;
		}
		
		SpoilageHelper.saveCreationTime(itemStack, world.getTotalWorldTime());
		SpoilageHelper.saveLastingFactor(itemStack, spoil);
		setDamage(itemStack, condition);
		getItemStackDisplayName(itemStack);
	}
}
