package jotato.quantumflux.blocks;

import jotato.quantumflux.tileentity.TileEntityRFExciter;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRFExciter extends BlockBase implements ITileEntityProvider {

	protected BlockRFExciter() {
		super(Material.rock, "rfExciter", 2, "pickaxe", 1);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityRFExciter();
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entity, ItemStack itemStack) {
		super.onBlockPlacedBy(world, x, y, z, entity, itemStack);
		
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)entity;
			if(world.getTileEntity(x, y, z) instanceof TileEntityRFExciter){
    			TileEntityRFExciter te = (TileEntityRFExciter) world.getTileEntity(x, y, z);
    			
    			//todo: allow the owner to unlock and prevent other from stealing the lock
    			
    			if(te.owner==null){
    				te.owner = player.getGameProfile().getId();
    				te.registerWithField();
    			}
    		}
		}
	}
	

}
