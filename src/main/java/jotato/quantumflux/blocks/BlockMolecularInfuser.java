package jotato.quantumflux.blocks;

import jotato.quantumflux.Logger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockMolecularInfuser extends BlockBase
{

    @SideOnly(Side.CLIENT)
    private IIcon icon_top;
    @SideOnly(Side.CLIENT)
    private IIcon icon_side;
    @SideOnly(Side.CLIENT)
    private IIcon icon_front;

    protected BlockMolecularInfuser()
    {
        super(Material.iron, "molecularInfuser", 1.3f, "pickaxe", 1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.icon_top = iconRegister.registerIcon("quantumflux:molecularInfuser_top");
        this.icon_side = iconRegister.registerIcon("quantumflux:molecularInfuser_side");
        this.icon_front = iconRegister.registerIcon("quantumflux:molecularInfuser_front");
        this.blockIcon = this.icon_front;

    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (side == getOrientation(meta,false))
            return icon_front;
        return icon_side;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Item.getItemFromBlock(QFBlocks.molecularInfuser);
    }

    // @SideOnly(Side.CLIENT)
    // public void onBlockAdded(World world, int x, int y, int z) {
    // super.onBlockAdded(world, x, y, z);
    // this.setDirection(world, x, y, z, true);
    // }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack)
    {
        int l = determineOrientation(world, x, y, z, entity);
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

    }

}
