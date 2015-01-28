package jotato.quantumflux.blocks;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockIncinerator extends BlockBase
{

    @SideOnly(Side.CLIENT)
    private IIcon icon_top;
    @SideOnly(Side.CLIENT)
    private IIcon icon_front;
    @SideOnly(Side.CLIENT)
    private IIcon icon_front_active;
    @SideOnly(Side.CLIENT)
    private IIcon icon_side;

    private boolean isActive;

    protected BlockIncinerator()
    {
        super(Material.iron, "incinerator", 1, "pickaxe", 1);
        setStepSound(soundTypeMetal);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister ir)
    {
        this.icon_top = ir.registerIcon(getTexture("incinerator_top"));
        this.icon_front = ir.registerIcon(getTexture("incinerator_front"));
        this.icon_front_active = ir.registerIcon(getTexture("incinerator_front_active"));
        this.icon_side = ir.registerIcon(getTexture("incinerator_side"));
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        int frontSide = getOrientation(meta, false);
        if (side == frontSide)
        {
            return this.isActive ? icon_front_active : icon_front;
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
}
