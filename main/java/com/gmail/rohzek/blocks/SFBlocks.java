package com.gmail.rohzek.blocks;

import java.util.HashSet;
import java.util.Set;

import com.gmail.rohzek.lib.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(Reference.MODID)
public class SFBlocks 
{
	// Blocks
	public static final Block MELON = new SFMelon("melon");
	public static final Block PUMPKIN = new SFPumpkin("pumpkin");
	public static final Block PUMPKIN_FACE = new SFPumpkinFace("pumpkinFace");
	public static final Block PUMPKIN_FACE_LIT = new SFPumpkinFaceLit("pumpkinFaceLit");
	
	// ItemBlocks
	public static final ItemBlock MELON_ITEM = new ItemBlock(MELON);
	public static final ItemBlock PUMPKIN_ITEM = new ItemBlock(PUMPKIN);
	public static final ItemBlock PUMPKIN_FACE_ITEM = new ItemBlock(PUMPKIN_FACE);
	public static final ItemBlock PUMPKIN_FACE_LIT_ITEM = new ItemBlock(PUMPKIN_FACE_LIT);
	
	public static void registerRenders() {}
	
	public static void registerOreDicts() {}
	
	public static void registerTileEntities(){}
	
	public static void registerRender(Block block)
	{	
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	public static void registerOreDict(String name, Block block) 
	{
		OreDictionary.registerOre(name, block);
	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler 
	{
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<ItemBlock>();

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) 
		{
			final IForgeRegistry<Block> registry = event.getRegistry();

			final Block[] blocks = 
			{
					MELON,
					PUMPKIN,
					PUMPKIN_FACE,
					PUMPKIN_FACE_LIT,
			};

			registry.registerAll(blocks);
		}
		
		@SubscribeEvent
		public static void registerItemBlocks(RegistryEvent.Register<Item> event) 
		{
			final ItemBlock[] items = 
			{
					MELON_ITEM,
					PUMPKIN_ITEM,
					PUMPKIN_FACE_ITEM,
					PUMPKIN_FACE_LIT_ITEM,
					
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) 
			{
				registry.register(item.setRegistryName(item.getBlock().getRegistryName()));
				ITEM_BLOCKS.add(item);
			}
		}
	}

	private static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String name) 
	{
		GameRegistry.registerTileEntity(tileEntityClass, Reference.RESOURCEID + name);
	}
}
