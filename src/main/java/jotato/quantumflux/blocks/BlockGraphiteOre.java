package jotato.quantumflux.blocks;

import java.util.Random;

import jotato.quantumflux.registers.ItemRegister;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockGraphiteOre extends BlockBase {

	public BlockGraphiteOre() {
		super(Material.rock, "graphiteOre", null, 1.3f);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemRegister.graphiteDust;
	}

	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		return fortune == 0 ? 1 : random.nextInt(fortune) + 1;
	}

}
