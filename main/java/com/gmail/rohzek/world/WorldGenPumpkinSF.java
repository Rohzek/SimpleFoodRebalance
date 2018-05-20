package com.gmail.rohzek.world;

import java.util.Random;

import com.gmail.rohzek.blocks.SFBlocks;
import com.gmail.rohzek.blocks.SFPumpkin;

import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenPumpkin;

public class WorldGenPumpkinSF extends WorldGenPumpkin
{
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position)
    {
        for (int i = 0; i < 64; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

            if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS && SFBlocks.PUMPKIN.canPlaceBlockAt(worldIn, blockpos))
            {
                worldIn.setBlockState(blockpos, SFBlocks.PUMPKIN.getDefaultState().withProperty(SFPumpkin.FACING, EnumFacing.Plane.HORIZONTAL.random(rand)), 2);
            }
        }

        return true;
    }
}
