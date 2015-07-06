package jotato.quantumflux.blocks;

import java.util.Random;

import jotato.quantumflux.ConfigMan;
import jotato.quantumflux.tileentity.TileEntityZeroPointExtractor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockZPE extends BlockBase implements ITileEntityProvider
{
    @SideOnly(Side.CLIENT)
    protected IIcon icon_top;
    @SideOnly(Side.CLIENT)
    protected IIcon icon_side;
    @SideOnly(Side.CLIENT)
    protected IIcon icon_bottom;

    protected BlockZPE()
    {
        super(Material.iron, "zeroPointExtractor", 1, "pickaxe", 0);
        setStepSound(soundTypeMetal);
        setLightLevel(.3f);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister ir)
    {
        this.icon_top = ir.registerIcon(getTexture("zpe_top"));
        this.icon_side = ir.registerIcon(getTexture("zpe_side"));
        this.icon_bottom = ir.registerIcon(getTexture("zpe_bottom"));
    }

    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (side == 1)
            return icon_top;
        if (side == 0)
            return icon_bottom;

        return this.icon_side;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
        int frontSide = determineOrientation(world, x, y, z, entity);
        world.setBlockMetadataWithNotify(x, y, z, frontSide, 2);
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
    	if (world.getBlockPowerInput(x, y, z) > 0) return;

        if(!ConfigMan.zpe_particles) return;
        
        double mod = MathHelper.clamp_double(world.rand.nextDouble(), .24, .76);
        world.spawnParticle("portal", x + mod, y + mod, z + mod, 0.1F, 0.1F, 0.1F);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int p1)
    {
        return new TileEntityZeroPointExtractor();
    }

}
