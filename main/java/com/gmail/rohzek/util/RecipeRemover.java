package com.gmail.rohzek.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

//Can remove custom crafting recipes and smelting recipes from the game...
public class RecipeRemover 
{
	public static void removeCraftingRecipes(Item item) 
	{
		 ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>)ForgeRegistries.RECIPES;
		 ArrayList<IRecipe> recipes = Lists.newArrayList(recipeRegistry.getValues());

		 for (IRecipe r : recipes)
		 {
			 ItemStack output = r.getRecipeOutput();
			 if (output.getItem() == item)
			 {
				 recipeRegistry.remove(r.getRegistryName());
				 recipeRegistry.register(DummyRecipe.from(r));
			 }
		 }
	}
	
	public static void removeCraftingRecipes(Block block) 
	{
		 ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>)ForgeRegistries.RECIPES;
		 ArrayList<IRecipe> recipes = Lists.newArrayList(recipeRegistry.getValues());

		 for (IRecipe r : recipes)
		 {
			 ItemStack output = r.getRecipeOutput();
			 if (output.getItem() == Item.getItemFromBlock(block))
			 {
				 recipeRegistry.remove(r.getRegistryName());
				 recipeRegistry.register(DummyRecipe.from(r));
			 }
		 }
	}
	
	// You have to pass in a new ItemStack().. But this will work for Items or Blocks without extra work.
	public static void removeSmeltingRecipe(ItemStack itemstack)
	{
		int recipesRemoved = 0;
		
		Map<ItemStack, ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
		
		for (Iterator<Map.Entry<ItemStack, ItemStack>> remover = recipes.entrySet().iterator(); remover.hasNext(); ) 
		{
			Map.Entry<ItemStack, ItemStack> entry = remover.next();
			
			ItemStack result = entry.getValue();
			
			if (ItemStack.areItemStacksEqual(result, itemstack)) 
			{
				remover.remove();
				recipesRemoved++;
			}
		}
		
		LogHelper.debug("Removed smelting recipes for " + itemstack.getDisplayName());
	}
}

class DummyRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
	private final ItemStack output;

    public DummyRecipe(ItemStack output)
    {
        this.output = output;
    }

    public static IRecipe from(IRecipe other)
    {
        return new DummyRecipe(other.getRecipeOutput()).setRegistryName(other.getRegistryName());
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        return false;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return false;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return output;
    }
}
