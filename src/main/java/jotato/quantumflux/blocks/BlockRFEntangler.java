package jotato.quantumflux.blocks;

import jotato.quantumflux.tileentity.TileEntityRFEntangler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRFEntangler extends BlockBase implements ITileEntityProvider
{
    
    @SideOnly(Side.CLIENT)
    protected IIcon icon_front;
    @SideOnly(Side.CLIENT)
    protected IIcon icon_side;
    @SideOnly(Side.CLIENT)
    protected IIcon icon_top;

    protected BlockRFEntangler()
    {
        super(Material.iron,"rfEntangler",6,"pickaxe",1);
        setStepSound(soundTypeMetal);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister ir)
    {
        this.icon_front = ir.registerIcon(getTexture("rfEntangler_front"));
        this.icon_side = ir.registerIcon(getTexture("rfEntangler_side"));
        this.icon_top = ir.registerIcon(getTexture("rfEntangler_top"));
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        int frontSide = getOrientation(meta, false);
        if (side == frontSide)
        {
            return icon_front;
        }

        if (side == 1 || side == 0)
        {
            return this.icon_top;
        }

        return this.icon_side;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        int frontSide = determineOrientation(world, x, y, z, entity);
        world.setBlockMetadataWithNotify(x, y, z, frontSide, 2);
        
        if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)entity;
			if(world.getTileEntity(x, y, z) instanceof TileEntityRFEntangler){
				TileEntityRFEntangler te = (TileEntityRFEntangler) world.getTileEntity(x, y, z);
    			
    			//todo: allow the owner to unlock and prevent other from stealing the lock
    			if(te.owner==null){
    				te.owner = player.getGameProfile().getId();
    				te.registerWithField();
    			}
    		}
		}
        
    }
    
    
    @Override
    public TileEntity createNewTileEntity(World world, int p1) {
    	return new TileEntityRFEntangler();
    }

}
