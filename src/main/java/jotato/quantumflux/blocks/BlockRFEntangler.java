package jotato.quantumflux.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockRFEntangler extends BlockBase
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
        
    }
    
    //when the block is placed save the player in the nbt and read that out later
    

}
