package jotato.quantumflux.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import jotato.quantumflux.ConfigurationManager;
import jotato.quantumflux.QuantumFlux;
import jotato.quantumflux.core.IActive;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBase extends Block
{

    protected BlockBase(Material material, String name, float hardness, String harvestTool, int harvestLevel)
    {
        super(material);

        setBlockName(name);
        setCreativeTab(QuantumFlux.tab);
        setBlockTextureName(getTexture(name));
        setHardness(hardness);
        setHarvestLevel(harvestTool, harvestLevel);
        GameRegistry.registerBlock(this, name);

    }
    protected String getTexture(String name){
        return "quantumflux:"+name;
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
    {
    }

    protected int determineOrientation(World world, int x, int y, int z, EntityLivingBase entity)
    {
        if (MathHelper.abs((float) entity.posX - (float) x) < 2.0F && MathHelper.abs((float) entity.posZ - (float) z) < 2.0F)
        {
            double d0 = entity.posY + 1.82D - (double) entity.yOffset;

            if (d0 - (double) y > 2.0D)
            {
                return 1;
            }

            if ((double) y - d0 > 0.0D)
            {
                return 0;
            }
        }

        int l = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));

    }

    protected int getOrientation(int meta, boolean allowUpDown)
    {
        int value = meta & 7;

        if (!allowUpDown)
        {
            if (value == 0 || value == 1)
            {
                //todo:determine which direction the player was facing
                value = 3; 
            }
        }
        return value;
    }
    
}
