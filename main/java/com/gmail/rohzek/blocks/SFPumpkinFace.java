package com.gmail.rohzek.blocks;

import javax.annotation.Nullable;

import com.gmail.rohzek.main.Main;
import com.google.common.base.Predicate;

import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.init.Blocks;

public class SFPumpkinFace extends BlockPumpkin
{
	private BlockPattern snowmanBasePattern;
    private BlockPattern snowmanPattern;
    private BlockPattern golemBasePattern;
    private BlockPattern golemPattern;
	private static final Predicate<IBlockState> IS_CUSTOM_PUMPKIN = new Predicate<IBlockState>()
    {
        public boolean apply(@Nullable IBlockState state)
        {
            return state != null && (state.getBlock() == SFBlocks.PUMPKIN_FACE);
        }
    };
    
	public SFPumpkinFace(String name)
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
	
	@Override
	protected BlockPattern getSnowmanBasePattern() 
	{
		if (this.snowmanBasePattern == null)
        {
            this.snowmanBasePattern = FactoryBlockPattern.start().aisle(new String[] {" ", "#", "#"}).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SNOW))).build();
        }

        return this.snowmanBasePattern;
	}
	
	@Override
	protected BlockPattern getSnowmanPattern() 
	{
		if (this.snowmanPattern == null)
        {
            this.snowmanPattern = FactoryBlockPattern.start().aisle(new String[] {"^", "#", "#"}).where('^', BlockWorldState.hasState(IS_CUSTOM_PUMPKIN)).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.SNOW))).build();
        }

        return this.snowmanPattern;
	}
	
	@Override
	protected BlockPattern getGolemBasePattern() 
	{
		if (this.golemBasePattern == null)
        {
            this.golemBasePattern = FactoryBlockPattern.start().aisle(new String[] {"~ ~", "###", "~#~"}).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
        }

        return this.golemBasePattern;
	}
	
	@Override
	protected BlockPattern getGolemPattern() 
	{
		 if (this.golemPattern == null)
	        {
	            this.golemPattern = FactoryBlockPattern.start().aisle(new String[] {"~^~", "###", "~#~"}).where('^', BlockWorldState.hasState(IS_CUSTOM_PUMPKIN)).where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR))).build();
	        }

	        return this.golemPattern;
	}
}
